package org.usfirst.frc.team5102.robot.util;

import edu.wpi.first.wpilibj.Joystick;

public class ArduinoComm
{
	Joystick launchpad;
	
	public enum RobotMode
	{
		auton,
		teleop,
		disabled
	}
	
	public ArduinoComm(int port)
	{
		launchpad = new Joystick(port);
	}
	
	public void updateAirMeter(double PSI)
    {
    	if(PSI > 54)
    	{
    		launchpad.setOutput(1, false);
    		launchpad.setOutput(2, true);
    		launchpad.setOutput(3, true);
    		launchpad.setOutput(4, false);
    	}
    	else if(PSI > 48)
    	{
    		launchpad.setOutput(1, true);
    		launchpad.setOutput(2, false);
    		launchpad.setOutput(3, false);
    		launchpad.setOutput(4, true);
    	}
    	else if(PSI > 42)
    	{
    		launchpad.setOutput(1, true);
    		launchpad.setOutput(2, false);
    		launchpad.setOutput(3, false);
    		launchpad.setOutput(4, false);
    	}
    	else if(PSI > 36)
    	{
    		launchpad.setOutput(1, true);
    		launchpad.setOutput(2, true);
    		launchpad.setOutput(3, false);
    		launchpad.setOutput(4, false);
    	}
    	else if(PSI > 30)
    	{
    		launchpad.setOutput(1, true);
    		launchpad.setOutput(2, true);
    		launchpad.setOutput(3, true);
    		launchpad.setOutput(4, false);
    	}
    	else if(PSI > 24)
    	{
    		launchpad.setOutput(1, true);
    		launchpad.setOutput(2, true);
    		launchpad.setOutput(3, true);
    		launchpad.setOutput(4, true);
    	}
    	else if(PSI > 18)
    	{
    		launchpad.setOutput(1, false);
    		launchpad.setOutput(2, true);
    		launchpad.setOutput(3, true);
    		launchpad.setOutput(4, true);
    	}
    	else if(PSI > 12)
    	{
    		launchpad.setOutput(1, false);
    		launchpad.setOutput(2, false);
    		launchpad.setOutput(3, true);
    		launchpad.setOutput(4, true);
    	}
    	else if(PSI > 6)
    	{
    		launchpad.setOutput(1, false);
    		launchpad.setOutput(2, false);
    		launchpad.setOutput(3, false);
    		launchpad.setOutput(4, true);
    	}
    	else// if(PSI > 0)
    	{
    		launchpad.setOutput(1, false);
    		launchpad.setOutput(2, true);
    		launchpad.setOutput(3, false);
    		launchpad.setOutput(4, true);
    	}
    	/*else
    	{
    		launchpad.setOutput(1, false);
    		launchpad.setOutput(2, false);
    		launchpad.setOutput(3, false);
    		launchpad.setOutput(4, false);
    	}
    	*/
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
