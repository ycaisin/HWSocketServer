package main;

import java.io.IOException;

import server.DataServer;

public class Application {

	public static void main(String[] args) throws IOException, InterruptedException {
		DataServer server = new DataServer();
		server.start();
	}

}
