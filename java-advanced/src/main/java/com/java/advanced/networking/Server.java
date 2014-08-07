package com.java.advanced.networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.java.advanced.exception.ErrorLogger;

public class Server {

	public static void main(String[] args) {

		try (ServerSocket serverSocket = new ServerSocket(9002);) {
			Socket socket;
			Executor executor = Executors.newFixedThreadPool(10);
			while (true) {
				socket = serverSocket.accept();
				executor.execute(new MyRunnable(socket));
			}
		} catch (IOException e) {
			ErrorLogger.INSTANCE.handleError(e);
		}

	}
}

class MyRunnable implements Runnable {
	Socket socket;

	public MyRunnable(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		String euro = null;
		try {
			// read the input from the client
			InputStream inputStream = socket.getInputStream();
			DataInputStream dataInputStream = new DataInputStream(inputStream);
			euro = dataInputStream.readUTF();
			System.out.println("euro received = " + euro);

			// write to client
			OutputStream outputStream = socket.getOutputStream();
			DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
			dataOutputStream.writeUTF(String.valueOf(Converter.euroToUSD(Double.valueOf(euro))));
		} catch (IOException e) {
			ErrorLogger.INSTANCE.handleError(e);
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					ErrorLogger.INSTANCE.handleError(e);
				}
			}
		}

	}
}
