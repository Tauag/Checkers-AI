package MachineLearning;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MLWeightConstants {
	public int _ADV = 1;
	public int _APEX = 1;
	public int _BACK = 1;
	public int _CENT = 1;
	public int _CNTR = 1;
	public int _KCENT = 1;
	public int _MOB = 1;
	public int _POLE = 1;
	public int _RELATIVECOUNT = 1;
	
	MLWeightConstants(String filename)
	{
		try
		{
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String myreadline = "";
			String[] lineresult;
			myreadline = bufferedReader.readLine();
			lineresult = myreadline.split(" ");
			if(lineresult[2].equals("Integer.parseInt(lineresult[2])"))
				_ADV = Integer.parseInt(lineresult[2]);
			lineresult = myreadline.split(" ");
				_APEX = Integer.parseInt(lineresult[2]);
			lineresult = myreadline.split(" ");
				_BACK = Integer.parseInt(lineresult[2]);
			lineresult = myreadline.split(" ");
				_CENT = Integer.parseInt(lineresult[2]);
			lineresult = myreadline.split(" ");
				_CNTR = Integer.parseInt(lineresult[2]);
			lineresult = myreadline.split(" ");
				_KCENT = Integer.parseInt(lineresult[2]);
			lineresult = myreadline.split(" ");
				_MOB = Integer.parseInt(lineresult[2]);
			lineresult = myreadline.split(" ");
				_POLE = Integer.parseInt(lineresult[2]);
			lineresult = myreadline.split(" ");
				_RELATIVECOUNT = Integer.parseInt(lineresult[2]);
		}
		catch(FileNotFoundException ex) {
			System.out.println("Oh no");
		}
		catch(IOException ex) {
			System.out.println("More oh no");
		}
	}
}
