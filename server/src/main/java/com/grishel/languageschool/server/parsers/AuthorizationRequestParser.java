package com.grishel.languageschool.server.parsers;

import com.grishel.languageschool.server.hibernate.HibernateUtil;
import com.grishel.languageschool.server.service.UserService;
import com.grishel.languageschool.shared.connection.Connection;
import com.grishel.languageschool.shared.connection.Request;
import com.grishel.languageschool.shared.connection.Request.RequestType;
import com.grishel.languageschool.shared.connection.RequestParser;
import com.grishel.languageschool.shared.connection.Response;
import com.grishel.languageschool.shared.connection.Response.RespondResult;
import com.grishel.languageschool.shared.pojo.User;

public class AuthorizationRequestParser implements RequestParser {



	@Override
	public void parse(RequestType action, String data, Connection connection) {
		if(action != RequestType.LOGIN) {return;}
		HibernateUtil.getCurrentSession().beginTransaction();
		Request<User> request = Request.fromString(data, User.class);
		UserService userService = new UserService();
		User user = userService.authorize(request.getArgument());
		Response<User> response;
		if (user == null) {
			response = new Response<User>(RespondResult.ERROR, null, "Wrong password.");
			connection.send(response.toString());
			return;
		}
		else {
			response = new Response<User>(RespondResult.OK, user);
			connection.setUser(user);
		}
		connection.send(response.toString());
		HibernateUtil.getCurrentSession().getTransaction().commit();
		return;
		
	}

}
