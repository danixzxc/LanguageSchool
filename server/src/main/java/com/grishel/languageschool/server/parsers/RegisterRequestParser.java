package com.grishel.languageschool.server.parsers;

import org.hibernate.exception.ConstraintViolationException;

import com.grishel.languageschool.server.hibernate.HibernateUtil;
import com.grishel.languageschool.server.service.UserService;
import com.grishel.languageschool.shared.connection.Connection;
import com.grishel.languageschool.shared.connection.Request;
import com.grishel.languageschool.shared.connection.Response;
import com.grishel.languageschool.shared.connection.Request.RequestType;
import com.grishel.languageschool.shared.connection.RequestParser;
import com.grishel.languageschool.shared.connection.Response.RespondResult;
import com.grishel.languageschool.shared.pojo.User;


public class RegisterRequestParser implements RequestParser {
	@Override
	public void parse(RequestType action, String data, Connection connection) {
		if(action != RequestType.REGISTER) {return;}
		HibernateUtil.getCurrentSession().beginTransaction();
		Request<User> request = Request.fromString(data, User.class);
		UserService userService = new UserService();
		User user = request.getArgument();
		Response<User> response;
		try {
			userService.registerUser(user);
			response = new Response<User>(RespondResult.OK, user);
			HibernateUtil.getCurrentSession().getTransaction().commit();
		}
		catch (ConstraintViolationException e) {
			response = new Response<User>(RespondResult.ERROR, null, "User could not be created");
			HibernateUtil.getCurrentSession().getTransaction().rollback();
		}
		connection.setUser(user);
		connection.send(response.toString());
		
		return;
		
	}
}
