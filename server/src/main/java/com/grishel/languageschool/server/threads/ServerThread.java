package com.grishel.languageschool.server.threads;

import java.io.IOException;

import com.grishel.languageschool.shared.connection.Closeable;
import com.grishel.languageschool.shared.connection.Connection;


public class ServerThread extends Thread implements Closeable {
	private Connection connection;

	public ServerThread(Connection connection) {
		super(new ServerRunnable(connection));
		this.connection = connection;
	}

	@Override
	public boolean isClosed() {
		return connection.isClosed();
	}

	@Override
	public void close() throws IOException {
		connection.close();
	}

	
	
}
