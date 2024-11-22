package com.grishel.languageschool.shared.connection;

import java.io.Reader;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Request<E> {
	private RequestType action;
	private E argument;
	
	public Request(RequestType action, E argument) {
		this.action = action;
		this.argument = argument;
	}
	
	public Request() {
	}

	public RequestType getAction() {
		return action;
	}

	public String getActionString() {
		return action.toString();
	}
	
	public E getArgument() {
		return argument;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
	public static <E> Request<E> fromString(String s, Class<E> clazz) {
		Request<E> temp;
		Type collectionType = TypeToken.getParameterized(Request.class, clazz).getType();
		temp = new Gson().fromJson(s, collectionType);
		return temp;
	}
	
	public static <E> Request<E> fromReader(Reader r, Class<E> clazz) {
		Request<E> temp;
		Type collectionType = TypeToken.getParameterized(Request.class, clazz).getType();
		temp = new Gson().fromJson(r, collectionType);
		return temp;
	}
	
	public enum RequestType{
		PING,
		LOGIN,
		REGISTER,
		SUBMIT_HOMEWORK,
		SHOW_LESSONS,
		CHANGE_USERINFO,
		ADD_LESSON,
		ADD_TOPIC
	}
	
}
