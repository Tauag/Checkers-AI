package Model;
import java.util.LinkedList;
import java.util.List;

public class Move{
	int old_coordinate;
	int new_coordinate;
	boolean kinged;
	List<Integer> _kills;
	
	public Move(int oldinput){
		old_coordinate = oldinput;
		_kills = new LinkedList<Integer>();
	}
	public Move(int oldcoord, int newcoord, int kill)
	{
		old_coordinate = oldcoord;
		new_coordinate = newcoord;
		_kills = new LinkedList<Integer>();
		this.addToKills(kill);
	}
	
	public Move(int oldinput, int newcoord, List<Integer> moves){
		old_coordinate = oldinput;
		new_coordinate = newcoord;
		_kills = moves;
	}
	
	public Move(int oldinput, List<Integer> moves){
		old_coordinate = oldinput;
		_kills = moves;
	}
	
	public int getnewcoordinate()
	{
		return new_coordinate;
	}
	
	public void addToKills(int kill){
		_kills.add(kill);
	}
	
	@Override
	public String toString(){
		String ret = "Original location: " + Integer.toString(old_coordinate) + " New location: " + Integer.toString(new_coordinate) + " kills: ";
		for(int kill : _kills)
			ret += Integer.toString(kill) + " ";
		
		if(kinged)
			ret += "KINGED";
		return ret;
	}
}
