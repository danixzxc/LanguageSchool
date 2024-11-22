package com.grishel.languageschool.shared.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import com.grishel.languageschool.shared.pojo.User;

public class Connection implements Closeable{
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private User user;
	

	public Connection(Socket socket) throws IOException {
		this.socket = socket;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
		out = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8);
	}


	public Socket getSocket() {
		return socket;
	}
	
	@Override
	public boolean isClosed() {
		return socket.isClosed();
	}
	

	public void send(String str) {
		out.println(str);
	}
	
	public String recieve() throws IOException {
		return in.readLine();
	}
	
	@Override
	public void close() throws IOException {
		out.close();
		in.close();
		socket.close();
		String username = null;
		if (user != null && user.getLogin() != null) {
			username = user.getLogin();
		}
		else {
			username = "Unauthorized user";
		}
		System.out.println(username + " has disconnected.");
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
}
