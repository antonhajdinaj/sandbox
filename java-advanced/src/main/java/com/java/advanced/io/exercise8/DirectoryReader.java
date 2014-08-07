package com.java.advanced.io.exercise8;

import java.io.File;

public class DirectoryReader {

	public static void main(String[] args) {
		File direcory = new File("C:\\Users\\antha\\Desktop");
		File[] listFiles = direcory.listFiles();
		for (File file : listFiles) {
			System.out.println(file.getName());
		}
	}

}
