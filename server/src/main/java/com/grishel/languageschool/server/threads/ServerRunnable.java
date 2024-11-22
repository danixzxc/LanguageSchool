package com.grishel.languageschool.server.threads;

import java.io.IOException;

import com.grishel.languageschool.server.parsers.AuthorizationRequestParser;
import com.grishel.languageschool.server.parsers.PingRequestParser;
import com.grishel.languageschool.server.parsers.RegisterRequestParser;
import com.grishel.languageschool.shared.connection.Closeable;
import com.grishel.languageschool.shared.connection.Connection;
import com.grishel.languageschool.shared.connection.RequestManager;


public class ServerRunnable implements Runnable, Closeable {

	private Connection connection;
	
	@Override
	public void run() {
		RequestManager requestReciever = new RequestManager(connection);

		requestReciever.addParser(new PingRequestParser());
		requestReciever.addParser(new AuthorizationRequestParser());
		requestReciever.addParser(new RegisterRequestParser());
		
		
		boolean continuing = true;
		while(continuing) {
			try {
				continuing = requestReciever.recieve();
			} catch (IOException e) {
				e.printStackTrace();
				continuing = false;
			}
		}
		try {
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ServerRunnable(Connection connection) {
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
