package com.java.advanced.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.java.advanced.exception.ErrorLogger;

public class TestRunner {

	public static void main(String[] args) {
		try {

			Class<?> clazz = Class.forName("com.realdolmen.java021.reflection.BookTestCase");

			Method[] methods = clazz.getDeclaredMethods();
			Object instance = clazz.newInstance();

			for (Method method : methods) {
				// if the @Test annotation is present, call the method
				if (Modifier.isPublic(method.getModifiers()) && method.isAnnotationPresent(Test.class)) {
					method.invoke(instance);
				}
			}

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			ErrorLogger.INSTANCE.handleError(e);
		}

	}
}
