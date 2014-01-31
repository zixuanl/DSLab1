package edu.cmu.ds.logger;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

import lab0.ds.TimeStampedMessage;

public class IncomingMPThread extends Thread {

	private Socket incomingMPSocket;
	private ConcurrentLinkedQueue<TimeStampedMessage> messagesList;
	
	public IncomingMPThread(Socket incomingMPSocket, ConcurrentLinkedQueue<TimeStampedMessage> messagesList) {
		this.incomingMPSocket = incomingMPSocket;
		this.messagesList = messagesList;
	}
	
	@Override
	public void run() {
		System.out.println("\nMessagePasser connected!");
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(incomingMPSocket.getInputStream());
			// Wait for incoming messages forever (until something happens)
			while (true) {
				TimeStampedMessage incomingMessage = (TimeStampedMessage) in.readObject();
				if (incomingMessage == null) {
					break;
				}
				messagesList.add(incomingMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}