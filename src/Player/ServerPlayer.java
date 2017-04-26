package Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import Model.Location;
import Model.Move;

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
	
	/*
	 * Takes row,column coordinates and converts them to samuel coordinates
	 */
	public int xyToSamuel(int x, int y){
		int base, samcoord;
		
		if(x > 5)
			base = 0;
		else if(x > 3)
			base = 9;
		else if(x > 1)
			base = 18;
		else
			base = 27;
		
		base += (1 - (y % 2)) * 4;
		samcoord = base + ((y / 2) % 4);
		return samcoord;
	}
	
	public String moveToCommand(Move move){
		String retString = "Move:" + getPlayer() + ":" + samuelToXY(move.old_coordinate);
		int compCoord = move.old_coordinate;
		int travel;
		
		for(int kill : move._kills){
			travel = (compCoord - kill) * 2;
			retString += (":" + samuelToXY(kill + travel)); 
			compCoord = kill + travel;
		}
		
		retString += (":" + samuelToXY(move.new_coordinate));
		return retString;
	}
	
	public void runPlayer(){
		String readMessage;
		
		try{
			readAndEcho(); // start message
    	    readAndEcho(); // ID query
    	    writeMessageAndEcho(_user); // user ID
    	    
    	    readAndEcho(); // password query 
    	    writeMessage(_password);  // password

    	    readAndEcho(); // opponent query
    	    writeMessageAndEcho(_opponent);  // opponent

    	    setGameID(readAndEcho().substring(5,10)); // game 
    	    setPlayer(readAndEcho().substring(6,11));  // color
    	    System.out.println("I am playing as "+getPlayer()+ " in game number "+getGameID());
		} catch(IOException error){
			System.out.println("Failed to authorize with server: " + error);
			System.exit(1);
		}

    	try{
    	    readMessage = readAndEcho();  
    	    // depends on color--a black move if i am white, Move:Black:i:j
    	    // otherwise a query to move, ?Move(time):
    	    if (getPlayer().equals("White")) {
    		readMessage = readAndEcho();  // move query
    		writeMessageAndEcho("(2:4):(3:5)");
    		readMessage = readAndEcho();  // white move
    		readMessage = readAndEcho();  // black move
    		readMessage = readAndEcho();  // move query
    		// here you would need to move again
    	    }
    	    else {
    		writeMessageAndEcho("(5:3):(4:4)");
    		readMessage = readAndEcho();  // black move
    		readMessage = readAndEcho();  // white move
    		readMessage = readAndEcho();  // move query
    		// here you would need to move again
    	    }
    	   
    	    getSocket().close();
    	} 
    	catch(IOException e){
    	    System.out.println("Failed in read/close");
    	    System.exit(1);
    	}
	}
    
    public static void main(String[] argv){
    	ServerPlayer sp = new ServerPlayer();
    	sp.setPlayer("Black");
    	Move mv = sp.findBestPlayerMove();
    	System.out.println(mv);
    	System.out.println(sp.moveToCommand(mv));
    }
}
