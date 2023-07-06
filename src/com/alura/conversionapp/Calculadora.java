package com.alura.conversionapp;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Calculadora {

    public static double convertirUnidades(String className, String methodName, Object... parameters)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // Obtener la clase por su nombre
        Class<?> clazz = Class.forName("com.alura.conversionapp." + className);

        // Obtener el constructor sin argumentos y crear una instancia de la clase
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        Object instance = constructor.newInstance();

        // Obtener el método por su nombre y tipos de parámetros
        Class<?>[] parameterTypes = new Class<?>[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            parameterTypes[i] = parameters[i].getClass();
        }
        Method method = clazz.getDeclaredMethod(methodName, parameterTypes);

        // Invocar el método en la instancia creada, pasando los parámetros
        Object result = method.invoke(instance, parameters);

        // Imprimir el resultado
        return (double) result;
    }
}
