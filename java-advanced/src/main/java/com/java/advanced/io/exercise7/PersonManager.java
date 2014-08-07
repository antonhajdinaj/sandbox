package com.java.advanced.io.exercise7;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.GregorianCalendar;

import com.java.advanced.domain.Person;
import com.java.advanced.exception.ErrorLogger;

public class PersonManager {

	public static void main(String[] args) {

		Person personToWrite = new Person("Anton", "Hajdinaj", new GregorianCalendar(1989, 10, 28));

		File file = new File("C:\\Users\\antha\\Desktop\\someFile.txt");

		// write an object to file
		writeObjectInFile(personToWrite, file);

		// read the written object
		Person readPerson = readPersonFromFile(file);

		// output the read object to the console
		System.out.println(readPerson);

	}

	private static Person readPersonFromFile(File file) {
		try (FileInputStream fileInputStream = new FileInputStream(file);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

			return (Person) objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			ErrorLogger.INSTANCE.handleError(e);
		}
		return null;
	}

	private static void writeObjectInFile(Person personToWrite, File file) {
		try (FileOutputStream fileOutputStream = new FileOutputStream(file);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
			objectOutputStream.writeObject(personToWrite);
		} catch (IOException e) {
			ErrorLogger.INSTANCE.handleError(e);
		}
	}
}
