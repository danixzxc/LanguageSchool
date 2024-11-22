package com.grishel.languageschool.server.parsers;

import com.grishel.languageschool.shared.connection.Connection;
import com.grishel.languageschool.shared.connection.Request;
import com.grishel.languageschool.shared.connection.RequestParser;
import com.grishel.languageschool.shared.connection.Response;
import com.grishel.languageschool.shared.connection.Response.RespondResult;
import com.grishel.languageschool.shared.connection.Request.RequestType;


public class PingRequestParser implements RequestParser {

	
	@Override
	public void parse(RequestType action, String data, Connection connection) {
		if(action != RequestType.PING) {return;}
		Request<String> request = Request.fromString(data, String.class);
		Response<String> repeatRequest = new Response<String>(RespondResult.OK, request.getArgument());
		connection.send(repeatRequest.toString());
		return;
		
	}
}
