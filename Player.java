/*
 * @author Nicholas Evans & Nicholas Trembley
 */

package Server;

import java.net.*;
import java.io.*;

public class Player{
	
	private static Socket socket;
	
	private static OutputStream out;
	private static OutputStreamWriter outStream;
	private static BufferedWriter bw;
		
	
	public Player(Socket playerSocket){
		socket = playerSocket;

		try {
			out = socket.getOutputStream();
			outStream = new OutputStreamWriter(out);
			bw = new BufferedWriter(outStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}

	public void sendMessageToClient(String message)throws IOException{
        bw.write(message);
        bw.flush();
        System.out.println("Message sent");
	}

}
