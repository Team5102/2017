package org.usfirst.frc.team5102.robot.util;

import edu.wpi.first.wpilibj.Joystick.AxisType;

public class LaunchPadPot
{
	
 static int currentNumber = 0;
	
	static int range = 10;
	
	public static double getPot()
	{
		return DriverStation.launchpad.getAxis(AxisType.kX);
	}
	
	public static int getScaledPot()
	{
		//System.out.println(ArduinoComm.launchpad.getRawAxis(0));
		
		double conversionNumber = 0.11811/range;
		
		double finalDouble = getPot()/conversionNumber;
		//int finalNumber = (int) Math.round(finalDouble);
		
		if(finalDouble >= currentNumber+1)
		{
			currentNumber++;
		}
		else if(finalDouble <= currentNumber-1)
		{
			currentNumber--;
		}
		
		return currentNumber;
	}
}
