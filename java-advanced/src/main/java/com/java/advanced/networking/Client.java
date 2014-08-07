package com.java.advanced.networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.java.advanced.exception.ErrorLogger;

public class Client {

	public static void main(String[] args) {

		try (Socket socket = new Socket("localhost", 9002);) {
			// send data to the server
			OutputStream outputStream = socket.getOutputStream();
			DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
			dataOutputStream.writeUTF("50");

			// recieve
			InputStream inputStream = socket.getInputStream();
			DataInputStream dataInputStream = new DataInputStream(inputStream);

			System.out.println("Converted value = " + dataInputStream.readUTF());
		} catch (IOException e) {
			ErrorLogger.INSTANCE.handleError(e);
		}
	}

}
