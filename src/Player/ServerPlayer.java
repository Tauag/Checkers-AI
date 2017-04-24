package Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import Model.Location;

public class ServerPlayer extends GeneralPlayer{
	private final static String _user = "1";
    private final static String _password = "109524";
    private final static String _opponent = "0";
    private final String _machine  = "icarus.engr.uconn.edu"; 
    private int _port = 3499;
    private Socket _socket = null;
    private PrintWriter _out = null;
    private BufferedReader _in = null;
    private String _gameID;
    private String _myColor;
  
    public ServerPlayer(){
    	super();
    	_socket = openSocket();
    }

    public Socket getSocket(){
    	return _socket;
    }

    public PrintWriter getOut(){
    	return _out;
    }

    public BufferedReader getIn(){
    	return _in;
    }
     
    public void setGameID(String id){
	_gameID = id;
    }
    
    public String getGameID() {
    	return _gameID;
    }

    public void setColor(String color){
    	_myColor = color;
    }
    
    public String getColor() {
    	return _myColor;
    }

    public String readAndEcho() throws IOException
    {
    	String readMessage = _in.readLine();
    	System.out.println("read: "+readMessage);
    	return readMessage;
    }

    public void writeMessage(String message) throws IOException
    {
    	_out.print(message+"\r\n");  
    	_out.flush();
    }
 
    public void writeMessageAndEcho(String message) throws IOException
    {
    	_out.print(message+"\r\n");  
    	_out.flush();
    	System.out.println("sent: "+ message);
    }
			       
    public  Socket openSocket(){
    	//Create socket connection, adapted from Sun example
    	try{
    		_socket = new Socket(_machine, _port);
    		_out = new PrintWriter(_socket.getOutputStream(), true);
    		_in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
    	} 
    	catch (UnknownHostException e) {
    		System.out.println("Unknown host: " + _machine);
    		System.exit(1);
    	} 
    	catch  (IOException e) {
    		System.out.println("No I/O");
    		System.exit(1);
    	}
    	return _socket;
    }
    
    /*
	 * Takes samuel coordinates and converts it to row,column format
	 * Returns a Location object
	 */
	public Location samuelToXY(int pos){
		int base, row, col;
		
		if(pos < 8){
			base = (7 - pos) * 2;
			row = base / 8;
			col = base % 8;
			if (row % 2 == 0) ++col;
			row += 6;
			col = 7 - col;
		}
		else if(pos < 17){
			base = (16 - pos) * 2;
			row = base / 8;
			col = base % 8;
			if (row % 2 == 0) ++col;
			row += 4;
			col = 7 - col;
		}
		else if(pos < 26){
			base = (25 - pos) * 2;
			row = base / 8;
			col = base % 8;
			if (row % 2 == 0) ++col;
			row += 2;
			col = 7 - col;
		}
		else{
			base = (34 - pos) * 2;
			row = base / 8;
			col = base % 8;
			if (row % 2 == 0) ++col;
			col = 7 - col;
		}
		
		return new Location(row, col);
	}
	
	public int xyToSamuel(int x, int y){
		//TODO
		return 0;
	}
    
    public static void main(String[] argv){
    	String readMessage;
    	ServerPlayer myClient = new ServerPlayer();

    	try{
    	    myClient.readAndEcho(); // start message
    	    myClient.readAndEcho(); // ID query
    	    myClient.writeMessageAndEcho(_user); // user ID
    	    
    	    myClient.readAndEcho(); // password query 
    	    myClient.writeMessage(_password);  // password

    	    myClient.readAndEcho(); // opponent query
    	    myClient.writeMessageAndEcho(_opponent);  // opponent

    	    myClient.setGameID(myClient.readAndEcho().substring(5,10)); // game 
    	    myClient.setColor(myClient.readAndEcho().substring(6,11));  // color
    	    System.out.println("I am playing as "+myClient.getColor()+ " in game number "+ myClient.getGameID());
    	    readMessage = myClient.readAndEcho();  
    	    // depends on color--a black move if i am white, Move:Black:i:j
    	    // otherwise a query to move, ?Move(time):
    	    if (myClient.getColor().equals("White")) {
    		readMessage = myClient.readAndEcho();  // move query
    		myClient.writeMessageAndEcho("(2:4):(3:5)");
    		readMessage = myClient.readAndEcho();  // white move
    		readMessage = myClient.readAndEcho();  // black move
    		readMessage = myClient.readAndEcho();  // move query
    		// here you would need to move again
    	    }
    	    else {
    		myClient.writeMessageAndEcho("(5:3):(4:4)");
    		readMessage = myClient.readAndEcho();  // black move
    		readMessage = myClient.readAndEcho();  // white move
    		readMessage = myClient.readAndEcho();  // move query
    		// here you would need to move again
    	    }
    	   
    	    myClient.getSocket().close();
    	} 
    	catch  (IOException e) {
    	    System.out.println("Failed in read/close");
    	    System.exit(1);
    	}
    }
}
