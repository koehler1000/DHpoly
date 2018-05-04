package de.dhpoly.netzwerk.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.netzwerk.NetzwerkClient;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

/**
 * A simple Swing-based client for the chat server.  Graphically
 * it is a frame with a text field for entering messages and a
 * textarea to see the whole dialog.
 *
 * The client follows the Chat Protocol which is as follows.
 * When the server sends "SUBMITNAME" the client replies with the
 * desired screen name.  The server will keep sending "SUBMITNAME"
 * requests as long as the client submits screen names that are
 * already in use.  When the server sends a line beginning
 * with "NAMEACCEPTED" the client is now allowed to start
 * sending the server arbitrary strings to be broadcast to all
 * chatters connected to the server.  When the server sends a
 * line beginning with "MESSAGE " then all characters following
 * this string should be displayed in its message area.
 */
public class NetzwerkClientImpl implements NetzwerkClient{

    BufferedReader in;
    PrintWriter out;

    /**
     * Constructs the client by laying out the GUI and registering a
     * listener with the textfield so that pressing Return in the
     * listener sends the textfield contents to the server.  Note
     * however that the textfield is initially NOT editable, and
     * only becomes editable AFTER the client receives the NAMEACCEPTED
     * message from the server.
     */
    public NetzwerkClientImpl(String ip) {
    }
    
    public void sendeAnServer(String text) {
    	out.println(text);
    }

    /**
     * Prompt for and return the address of the server.
     */
    private String getServerAddress() {
        return "127.0.0.1"; //todo
    }

    /**
     * Prompt for and return the desired screen name.
     */
    private String getName() {
        return "name"; //todo
    }

    /**
     * Connects to the server then enters the processing loop.
     */
    private void run() throws IOException {

        // Make connection and initialize streams
        String serverAddress = getServerAddress();
        Socket socket = new Socket(serverAddress, 9001);
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        // Process all messages from server, according to the protocol.
        while (true) {
            String line = in.readLine();
            if (line.startsWith("SUBMITNAME")) {
                out.println(getName());}
             else if (line.startsWith("MESSAGE")) {
                System.out.println(line);
            }
        }
    }

    /**
     * Runs the client as an application with a closeable frame.
     */
    public static void main(String[] args) throws Exception {
        NetzwerkClientImpl client = new NetzwerkClientImpl("127.0.0.1");
        client.run();
    }

	@Override
	public void sendeAnServer(Datenobjekt objekt) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAnsicht(SpielfeldAnsicht ansicht) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void verbindungAufbauen() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void verbindungAbbauen() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void empfange(Datenobjekt obj) {
		// TODO Auto-generated method stub
		
	}
}