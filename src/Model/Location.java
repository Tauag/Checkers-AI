package Model;

public class Location {
	public int _row;
	public int _column;
	
	public Location(int x, int y){
		_row = x;
		_column = y;
	}
	
	@Override
	public boolean equals(Object obj){
		return equals((Location) obj);
	}
	
	public boolean equals(Location obj){
		if(obj._row == this._row && obj._column == this._column)
			return true;
		else
			return false;
	}
	
	public String toString(){
		return "(" + Integer.toString(_row) + ", " + Integer.toString(_column) + ")";
	}
}
