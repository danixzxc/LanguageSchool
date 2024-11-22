package com.grishel.languageschool.server;

import java.io.IOException;
import java.net.ServerSocket;

import com.grishel.languageschool.server.threads.AcceptConnectionsThread;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ServerSocket serverSocket;
    	try {
			serverSocket = new ServerSocket(16969);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

    	AcceptConnectionsThread mainThread = new AcceptConnectionsThread(serverSocket);
    	while(true) {
    		mainThread.update();
    	}
    }
}
