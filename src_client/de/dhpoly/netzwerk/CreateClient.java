package de.dhpoly.netzwerk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CreateClient
{
	private BufferedReader in;
	private PrintWriter out;
	private String serverIp;

	public CreateClient(String serverIp){
		this.serverIp = serverIp;
	}
	
	public void senden(String content){
		out.println(content);
	}
	
	public String empfangen() throws IOException{
		return in.readLine();
	}
	
	public void connectToServer() throws IOException
	{
		Socket socket = new Socket(serverIp, 9898);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
	}
}