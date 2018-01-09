package org.usfirst.frc.team5102.robot.util;

import edu.wpi.first.wpilibj.Joystick;

public class DriverStation
{
	
	public static Joystick launchpad;
	
	public enum RobotMode
	{
		auton,
		teleop,
		disabled
	}
	
	public enum InfoStripMode
	{
		airPressure,
		info
	}
	
	boolean modeOverride;
		
	public DriverStation(int launchpadPort)
	{
		launchpad = new Joystick(launchpadPort);
		
		modeOverride = false;
	}
	
	public void setInfoStrip(double number, InfoStripMode mode)
	{
		if(mode == InfoStripMode.airPressure && !modeOverride)
		{
			launchpad.setOutput(6, false);
			
			if(number > 54)
	    	{
	    		number = 10;
	    	}
	    	else if(number > 48)
	    	{
	    		number = 9;
	    	}
	    	else if(number > 42)
	    	{
	    		number = 8;
	    	}
	    	else if(number > 36)
	    	{
	    		number = 7;
	    	}
	    	else if(number > 30)
	    	{
	    		number = 6;
	    	}
	    	else if(number > 24)
	    	{
	    		number = 5;
	    	}
	    	else if(number > 18)
	    	{
	    		number = 4;
	    	}
	    	else if(number > 12)
	    	{
	    		number = 3;
	    	}
	    	else if(number > 6)
	    	{
	    		number = 2;
	    	}
	    	else// if(PSI > 0)
	    	{
	    		number = 1;
	    	}
		}
		else if(mode == InfoStripMode.info)
		{
			launchpad.setOutput(6, true);
		}
	
		
		if(number == 10)
    	{
    		launchpad.setOutput(1, false);
    		launchpad.setOutput(2, true);
    		launchpad.setOutput(3, true);
    		launchpad.setOutput(4, false);
    	}
    	else if(number == 9)
    	{
    		launchpad.setOutput(1, true);
    		launchpad.setOutput(2, false);
    		launchpad.setOutput(3, false);
    		launchpad.setOutput(4, true);
    	}
    	else if(number == 8)
    	{
    		launchpad.setOutput(1, true);
    		launchpad.setOutput(2, false);
    		launchpad.setOutput(3, false);
    		launchpad.setOutput(4, false);
    	}
    	else if(number == 7)
    	{
    		launchpad.setOutput(1, true);
    		launchpad.setOutput(2, true);
    		launchpad.setOutput(3, false);
    		launchpad.setOutput(4, false);
    	}
    	else if(number == 6)
    	{
    		launchpad.setOutput(1, true);
    		launchpad.setOutput(2, true);
    		launchpad.setOutput(3, true);
    		launchpad.setOutput(4, false);
    	}
    	else if(number == 5)
    	{
    		launchpad.setOutput(1, true);
    		launchpad.setOutput(2, true);
    		launchpad.setOutput(3, true);
    		launchpad.setOutput(4, true);
    	}
    	else if(number == 4)
    	{
    		launchpad.setOutput(1, false);
    		launchpad.setOutput(2, true);
    		launchpad.setOutput(3, true);
    		launchpad.setOutput(4, true);
    	}
    	else if(number == 3)
    	{
    		launchpad.setOutput(1, false);
    		launchpad.setOutput(2, false);
    		launchpad.setOutput(3, true);
    		launchpad.setOutput(4, true);
    	}
    	else if(number == 2)
    	{
    		launchpad.setOutput(1, false);
    		launchpad.setOutput(2, false);
    		launchpad.setOutput(3, false);
    		launchpad.setOutput(4, true);
    	}
    	else if(number == 1)
    	{
    		launchpad.setOutput(1, false);
    		launchpad.setOutput(2, true);
    		launchpad.setOutput(3, false);
    		launchpad.setOutput(4, true);
    	}
    	else
    	{
    		launchpad.setOutput(1, false);
    		launchpad.setOutput(2, false);
    		launchpad.setOutput(3, false);
    		launchpad.setOutput(4, false);
    	}
	}
	
	public void setMode(RobotMode mode)
	{
		switch(mode)
		{
			case disabled:
				launchpad.setOutput(5, false);
				launchpad.setOutput(11, false);
				break;
			case auton:
				launchpad.setOutput(5, true);
				launchpad.setOutput(11, false);
				break;
			case teleop:
				launchpad.setOutput(5, false);
				launchpad.setOutput(11, true);
				break;
		}
	}
}
