package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/* *************************************
 * 	Cs2043 - BattleShipServer
 * 	@Author: Nicholas Evans 
 * 	@Author: Nick Tremblay 3509303
 * 	@Date: 	 March 1 2017
 * 
 *  @Class:	 TestClient
 *  
 *  @Description: A simple client for executing test cases.
 * 
 * *************************************/

public class TestClient {

	private static String host = "localhost";
	private static int port = 2043;

	private static Socket socket;
	
	private static OutputStream out;
	private static OutputStreamWriter outStream;
	private static BufferedWriter bw;
	
	private static InputStream in;
	private static InputStreamReader inStream;
	private static BufferedReader reader;

	private static boolean running = false;

	public static void main(String[] args){
		
		initializeClient();
		run();


	}

	public static void run(){
		while(running){
			System.out.println("Running...");
			recieve();
			send();
		}

	}//end run

	private static void initializeClient(){
		System.out.println("Attempting to connect...");			
		try {
			socket = new Socket(host, port);
			
			out = socket.getOutputStream();
			outStream = new OutputStreamWriter(out);
			bw = new BufferedWriter(outStream);	
			
			System.out.println("Connected...");
		} catch(IOException e) {
			e.printStackTrace();
		}

		
		running = true;

	}

	private void stopClient(){
		running = false;
	}

	public static void recieve(){
		try {
			System.out.println("waiting for server...");
			in = socket.getInputStream();
			inStream = new InputStreamReader(in);
			reader = new BufferedReader(inStream);	
			System.out.println(reader.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void send(){
		System.out.println("Sending message...");
	
	}

}
