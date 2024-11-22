package com.grishel.languageschool.server.threads;

import java.util.ArrayList;
import java.util.List;

public class ThreadManager {
	private List<ServerThread> threads = new ArrayList<ServerThread>();
	private List<ServerThread> threadsQueuedForDeletion = new ArrayList<ServerThread>();

	
	public void add(ServerThread thread) {
		threads.add(thread);
	}
	
	public void check() {
		for(ServerThread thread: threads) {
			if(thread.isClosed()) {
				thread.interrupt();
				threadsQueuedForDeletion.add(thread);
			}
		}
		for(ServerThread thread: threadsQueuedForDeletion) {
			threads.remove(thread);
		}
		threadsQueuedForDeletion.clear();
	}
	
}
