package com.grishel.languageschool.server.threads;

import java.net.ServerSocket;

import com.grishel.languageschool.server.connection.ConnectionAccepter;
import com.grishel.languageschool.shared.connection.Connection;


public class AcceptConnectionsThread {
	private ThreadManager threadManager = new ThreadManager();
	private ConnectionAccepter connectionAccepter;
	
	
	public void update() {
		threadManager.check();
		Connection connection = connectionAccepter.accept();
		ServerThread connectionThread = new ServerThread(connection);
		threadManager.add(connectionThread);
		connectionThread.start();
	}


	public AcceptConnectionsThread(ServerSocket serverSocket) {
		this.connectionAccepter = new ConnectionAccepter(serverSocket);
	}
	
	
}
