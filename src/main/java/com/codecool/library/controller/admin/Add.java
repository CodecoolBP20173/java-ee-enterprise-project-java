package com.codecool.library.controller.admin;

import com.codecool.library.controller.DBConnect;
import com.codecool.library.model.BaseModel;
import org.reflections.Reflections;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/admin/add"})
public class Add extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/");
        Map<String, String> parameters = getParameters(req.getParameterMap());

        addModelToDB(req, parameters);
    }

    private void addModelToDB(HttpServletRequest req, Map<String, String> parameters) {
        Class<? extends BaseModel> classType = getModelClass(req.getParameter("type"));
        if (classType == null) return;

        BaseModel newObject = null;
        List<Object> paramValues = new ArrayList<>();
        ArrayList<Class> paramClasses = new ArrayList<>();
        try {
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                Field field = classType.getDeclaredField(entry.getKey());
                Class<?> type = field.getType();
                paramClasses.add(type);

                if (type == String.class) {
                    paramValues.add(entry.getValue());
                } else if (type.equals(int.class) || (type.equals(Integer.class))) {
                    paramValues.add(Integer.valueOf(entry.getValue()));
                } else if (type.equals(Boolean.class)) {
                    paramValues.add(Boolean.valueOf(entry.getValue()));
                } else {
                    // TODO
                    System.out.println("INCOMPLETE!!!");
                }
            }

            Class[] paramClassArray = new Class[paramClasses.size()];
            paramClasses.toArray(paramClassArray);

            Object[] paramValueArray = new Object[paramValues.size()];
            paramValues.toArray(paramValueArray);

            newObject = classType
                    .getConstructor(paramClassArray)
                    .newInstance(paramValueArray);

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        DBConnect.persist(newObject);
    }

    private Map<String, String> getParameters(Map<String, String[]> map) {
        return map.entrySet().stream()
                .filter(set -> set.getValue() != null && !set.getKey().equalsIgnoreCase("type"))
                .collect(Collectors.toMap(
                        Map.Entry::getKey, t -> t.getValue()[0])
                );
    }

    private Class<? extends BaseModel> getModelClass(String className) {
        Reflections reflections = new Reflections("com.codecool.library");
        Set<Class<? extends BaseModel>> allClasses = reflections.getSubTypesOf(BaseModel.class);
        for (Class<? extends BaseModel> modelClass : allClasses) {
            if (modelClass.getSimpleName().equalsIgnoreCase(className)) {
                return modelClass;
            }
        }
        return null;
    }
}
