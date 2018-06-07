package com.codecool.library;

import com.codecool.library.model.BaseModel;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {
    public static Class<? extends BaseModel> getModelClass(String className) {
        Reflections reflections = new Reflections("com.codecool.library");
        Set<Class<? extends BaseModel>> allClasses = reflections.getSubTypesOf(BaseModel.class);
        for (Class<? extends BaseModel> modelClass : allClasses) {
            if (modelClass.getSimpleName().equalsIgnoreCase(className)) {
                return modelClass;
            }
        }
        return null;
    }

    public static Map<String, String> getParameters(Map<String, String[]> map) {
        return map.entrySet().stream()
                .filter(set -> set.getValue() != null && !set.getKey().equalsIgnoreCase("type"))
                .collect(Collectors.toMap(
                        Map.Entry::getKey, t -> t.getValue()[0]
                        )
                );
    }
}
