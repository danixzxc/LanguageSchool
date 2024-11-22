package com.grishel.languageschool.shared.connection;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class RequestManager {
	private Connection connection;
	private Gson gson = new Gson();
	
	private List<RequestParser> parsers = new ArrayList<RequestParser>();
	
	public boolean recieve() throws IOException {
		String data = connection.recieve();
		if(data == null) {
			return false;
		}
		Request<?> request = gson.fromJson(data, Request.class);
		for(RequestParser parser: parsers) {
			parser.parse(request.getAction(), data, connection);
		}
		return true;
	}

	public RequestManager(Connection connection) {
		this.connection = connection;
	}
	
	public void addParser(RequestParser parser) {
		parsers.add(parser);
	}
}
