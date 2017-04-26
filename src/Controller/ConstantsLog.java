package Controller;

public class ConstantsLog {
	public String ConstantsUsed()
	{	
		String output = "";
		if(ControllerConstants._ADV == true)
			output = output + "_ADV:TRUE WITH CONSTANT " + WeightConstants._ADV + "\n";
		else
			output = output + "_ADV:FALSE WITH CONSTANT " + WeightConstants._ADV + "\n";
		if(ControllerConstants._APEX== true)
			output = output + "_APEX:TRUE WITH CONSTANT " + WeightConstants._APEX + "\n";
		else
			output = output + "_ADV:FALSE WITH CONSTANT " + WeightConstants._APEX + "\n";
		if(ControllerConstants._BACK== true)
			output = output + "_BACK:TRUE WITH CONSTANT " + WeightConstants._BACK + "\n";
		else
			output = output + "_BACK:FALSE WITH CONSTANT " + WeightConstants._BACK + "\n";
		if(ControllerConstants._CENT== true)
			output = output + "_CENT:TRUE WITH CONSTANT " + WeightConstants._CENT + "\n";
		else
			output = output + "_CENT:FALSE WITH CONSTANT " + WeightConstants._CENT + "\n";
		if(ControllerConstants._CNTR== true)
			output = output + "_CNTR:TRUE WITH CONSTANT " + WeightConstants._CNTR + "\n";
		else
			output = output + "_CNTR:FALSE WITH CONSTANT " + WeightConstants._CNTR + "\n";
		if(ControllerConstants._KCENT== true)
			output = output + "_KCENT:TRUE WITH CONSTANT " + WeightConstants._KCENT + "\n";
		else
			output = output + "_KCENT:FALSE WITH CONSTANT " + WeightConstants._KCENT + "\n";
		if(ControllerConstants._MOB== true)
			output = output + "_MOB:TRUE WITH CONSTANT " + WeightConstants._MOB + "\n";
		else
			output = output + "_MOB:FALSE WITH CONSTANT " + WeightConstants._MOB + "\n";
		if(ControllerConstants._POLE== true)
			output = output + "_POLE:TRUE WITH CONSTANT " + WeightConstants._POLE + "\n";
		else
			output = output + "_POLE:FALSE WITH CONSTANT " + WeightConstants._POLE + "\n";
		if(ControllerConstants._RELATIVECOUNT== true)
			output = output + "_RELATIVECOUNT:TRUE WITH CONSTANT " + WeightConstants._RELATIVECOUNT + "\n";
		else
			output = output + "_RELATIVECOUNT:FALSE WITH CONSTANT " + WeightConstants._RELATIVECOUNT + "\n";
		return output;
	}
}
