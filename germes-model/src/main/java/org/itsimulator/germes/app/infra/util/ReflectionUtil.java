package org.itsimulator.germes.app.infra.util;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.itsimulator.germes.app.infra.exception.ConfigurationException;

public class ReflectionUtil {

    private ReflectionUtil() {

    }

    public static <T> T createInstance(Class<T> clz)
            throws ConfigurationException {
        try {
            return clz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ConfigurationException(e);
        }
    }

    public static List<String> findSimilarFields(Class<?> clz1, Class<?> clz2) throws ConfigurationException {
        try {
            Field[] declaredFields = clz1.getDeclaredFields();
            List<String> targetFields = Stream.of(clz2.getDeclaredFields())
                    .map(field -> field.getName())
                    .collect(Collectors.toList());
            return Stream.of(declaredFields)
                    .map(field -> field.getName())
                    .filter((name) -> targetFields.contains(name))
                    .collect(Collectors.toList());
        } catch (SecurityException e) {
            throw new ConfigurationException(e);

        }

    }

    public static void copyFields(Object src, Object dest, List<String> fields)
            throws ConfigurationException {
        Checks.checkParameter(src != null, "Source obj is not initialized");
        Checks.checkParameter(dest != null, "Destination obj is not initialized");
        try {
            for (String field : fields) {
                Field fld = src.getClass().getDeclaredField(field);

                if (fld != null) {
                    fld.setAccessible(true);
                    Object value = fld.get(src);
                    Field fildDestination = src.getClass().getDeclaredField(field);
                    if (fildDestination != null) {
                        fildDestination.setAccessible(true);
                        fildDestination.set(dest, value);
                    }
                }
            }

        } catch (SecurityException | IllegalArgumentException |
                NoSuchFieldException | IllegalAccessException exception) {
            throw new ConfigurationException(exception);
        }

    }


}
