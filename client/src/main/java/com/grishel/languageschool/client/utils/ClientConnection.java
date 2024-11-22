package com.grishel.languageschool.client.utils;

import java.io.IOException;
import java.net.Socket;

import com.grishel.languageschool.shared.connection.Connection;

public class ClientConnection {
	private static Connection connection;
	
	public static void createConnection(Socket socket) throws IOException {
		connection = new Connection(socket);
	}
	
	public static Connection getConnection() {return connection;}
	
	public static void closeConnection() throws IOException {connection.close();}
}
	