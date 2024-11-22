package com.grishel.languageschool.shared.connection;


public interface RequestParser {
	public void parse(Request.RequestType action, String data, Connection connection);
}
