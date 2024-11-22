package com.grishel.languageschool.shared.connection;

import java.io.Reader;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class Response <E> {
	private RespondResult result;
	private String errorMessage;
	private E data;
	
	public Response() {
	}

	public Response(RespondResult result, E data) {
		this.result = result;
		this.data = data;
	}

	public Response(RespondResult result, E data, String errorMessage) {
		this.result = result;
		this.data = data;
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public RespondResult getResult() {
		return result;
	}

	public void setResult(RespondResult result) {
		this.result = result;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
	public static <E> Response<E> fromString(String s, Class<E> clazz) {
		Response<E> temp;
		Type collectionType = TypeToken.getParameterized(Response.class, clazz).getType();
		temp = new Gson().fromJson(s, collectionType);
		return temp;
	}
	
	public static <E> Response<E> fromReader(Reader r, Class<E> clazz) {
		Response<E> temp;
		Type collectionType = TypeToken.getParameterized(Response.class, clazz).getType();
		temp = new Gson().fromJson(r, collectionType);
		return temp;
	}
	
	public enum RespondResult{
		OK,
		DENIED,
		ERROR
	}  
	
}
