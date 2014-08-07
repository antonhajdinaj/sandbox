package com.java.advanced.io.exercise8;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.java.advanced.exception.ErrorLogger;

public class FileCopier {

	public static void main(String[] args) {

		File fileToRead = new File("C:\\Users\\antha\\Desktop\\css_logo.png");
		File fileToWrite = new File("C:\\Users\\antha\\Desktop\\css_logoCopied.png");
		copyFile(fileToRead, fileToWrite);
	}

	private static byte[] copyFile(File fileToRead, File fileToWrite) {
		try (FileInputStream fileInputStream = new FileInputStream(fileToRead);
				FileOutputStream fileOutputStream = new FileOutputStream(fileToWrite);
				BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);) {

			int value = 0;
			while ((value = bufferedInputStream.read()) != -1) {
				bufferedOutputStream.write(value);
			}
		} catch (IOException e) {
			ErrorLogger.INSTANCE.handleError(e);
		}
		return null;
	}
}
