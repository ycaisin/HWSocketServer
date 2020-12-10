package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class DataServer {

	public void start() throws IOException, InterruptedException {
		System.out.println("Server started");
					
		ServerSocket serverSocket = new ServerSocket(8888, 0,
         InetAddress.getByName("localhost"));
		
		Socket clientSocket = serverSocket.accept();
		
		DataInputStream din = new DataInputStream(clientSocket.getInputStream());
		String message = din.readUTF();
		System.out.println("Server >> client sent:" + message);
		
		List<Integer> listValues = messageSplit(message);
		
		DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());
	
		dout.writeByte(findAVG(listValues));
		din.close();
		dout.flush();
		
		Thread.sleep(5000);
		
		System.out.println("Server ending");
		
		serverSocket.close();
	}
	
	public List<Integer> messageSplit(String message){
		List<Integer> listValues = new ArrayList<Integer>();
		String[]s = message.split(",");
		for(int i = 0; i<=s.length-1; i++ ) {
			listValues.add(Integer.valueOf(s[i]));
		}
		return listValues;
	}

	public byte findAVG(List<Integer> listValues) {
		byte avg = 0;
		for (Integer v : listValues) {
			avg += v; 
		}
		return avg = (byte) (avg / listValues.size());
	}
	
}
