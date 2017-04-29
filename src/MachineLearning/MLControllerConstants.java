package MachineLearning;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MLControllerConstants {
	public boolean _ADV = true;
	public boolean _APEX = true;
	public boolean _BACK = true;
	public boolean _CENT = true;
	public boolean _CNTR = true;
	public boolean _KCENT = true;
	public boolean _MOB = true;
	public boolean _POLE = true;
	public boolean _RELATIVECOUNT = true;	
	public boolean _RELATIVEKINGS = true;
	public int _MAXDEPTH = 3;
	
	MLControllerConstants(String filename)
	{
		try
		{
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String myreadline = "";
			String[] lineresult;
			myreadline = bufferedReader.readLine();
			lineresult = myreadline.split(" ");
			if(lineresult[2].equals("false"))
				_ADV = false;
			lineresult = myreadline.split(" ");
			if(lineresult[2].equals("false"))
				_APEX = false;
			lineresult = myreadline.split(" ");
			if(lineresult[2].equals("false"))
				_BACK = false;
			lineresult = myreadline.split(" ");
			if(lineresult[2].equals("false"))
				_CENT = false;
			lineresult = myreadline.split(" ");
			if(lineresult[2].equals("false"))
				_CNTR = false;
			lineresult = myreadline.split(" ");
			if(lineresult[2].equals("false"))
				_KCENT = false;
			lineresult = myreadline.split(" ");
			if(lineresult[2].equals("false"))
				_MOB = false;
			lineresult = myreadline.split(" ");
			if(lineresult[2].equals("false"))
				_POLE = false;
			lineresult = myreadline.split(" ");
			if(lineresult[2].equals("false"))
				_RELATIVECOUNT = false;
			lineresult = myreadline.split(" ");
			if(lineresult[2].equals("false"))
				_RELATIVEKINGS = false;
		}
		catch(FileNotFoundException ex) {
			System.out.println("Oh no");
		}
		catch(IOException ex) {
			System.out.println("More oh no");
		}
	}
	
	public void print()
	{
		
	}
}
