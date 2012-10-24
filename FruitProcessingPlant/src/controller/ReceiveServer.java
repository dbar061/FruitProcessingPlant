package controller;

public class ReceiveServer {

	/**
	 * ServerQueue.java
	 * 
	 * @author			Devin Barry
	 * @date			25.10.2012
	 * @lastModified	25.10.2012
	 * 
	 * This is the main class for the various server
	 * implementations.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		ServerQueue<Boolean> bq = new ServerQueue<Boolean>();
		ServerQueue<Integer> iq = new ServerQueue<Integer>();
		//Start server
		System.out.println("Starting Main Server");
		
		//StringReceiveServer srs1 = new StringReceiveServer("55552");
		//Thread t1 = new Thread(srs1);
		
		//These call are not super elegant but they get the job done.
		ObjectReceiveServer<Boolean> ors1 = ObjectReceiveServer.createMyObject("55551", bq, Boolean.class);
		ObjectReceiveServer<Integer> ors2 = ObjectReceiveServer.createMyObject("55552", iq, Integer.class);
		
		Thread t2 = new Thread(ors1);
		Thread t3 = new Thread(ors2);
		//t1.start();
		t2.start();
		t3.start();
		
		for (int i = 0; i < 5; i++) {
			Boolean nextB = bq.get();
			System.out.println("We got a boolean of " + nextB);
			Integer nextI = iq.get();
			System.out.println("We got an Integer of " + nextI);
		}

	}

}
