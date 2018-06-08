package com.codecool.library.controller.admin;

import com.codecool.library.Utils;
import com.codecool.library.controller.DBConnect;
import com.codecool.library.model.BaseModel;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/admin/add"})
public class Add extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Class<? extends BaseModel> classType = Utils.getModelClass(req.getParameter("type"));
        if (classType == null) return;

        try {
            Map<String, String> stringMap = Utils.getParameters(req.getParameterMap());
            Map<String, Object> parameterMap = getParameters(stringMap, classType);
            BaseModel newObject = getNewObject(classType, parameterMap);

            DBConnect.persist(newObject);
        } catch (NoSuchFieldException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/");
    }

    private Map<String, Object> getParameters(Map<String, String> stringMap, Class<? extends BaseModel> classType)
            throws NoSuchFieldException, IllegalAccessException, InvocationTargetException {

        Map<String, Object> parameterMap = new HashMap<>();
        for (Map.Entry<String, String> entry : stringMap.entrySet()) {
            Field field = classType.getDeclaredField(entry.getKey());
            Class<?> type = field.getType();

            // TODO implement the valueOf method in all extending classes of BaseModel!!!
            // TODO and also, there HAVE TO BE setters for every single field of every class
            Object param;
            if (type.getTypeName().equals(String.class.getTypeName())) {
                param = entry.getValue();
            } else {
                try {
                    param = type.getMethod("valueOf", String.class).invoke(null, entry.getValue());
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    continue;
                }
            }
            parameterMap.put(field.getName(), param);
        }
        return parameterMap;
    }

    private BaseModel getNewObject(Class<? extends BaseModel> classType, Map<String, Object> parameterMap) {
        BaseModel baseModel = null;
        try {
            baseModel = classType.getConstructor().newInstance();
            // Call each setter method for each passed parameter
            for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
                String setterName = "set" +
                        entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1);
                Method setter = baseModel.getClass().getMethod(setterName, entry.getValue().getClass());
                setter.invoke(baseModel, entry.getValue());
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return baseModel;
    }

}
