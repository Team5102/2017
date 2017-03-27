package org.usfirst.frc.team5102.robot.util;

public class Toggle
{
	boolean pressed, running;
	
	public Toggle()
	{
		pressed = false;
		running = false;
	}
	
	public boolean toggle(boolean buttonPressed)
	{
		if(buttonPressed)
		{
			if(!pressed)
			{
				running = !running;
				
				pressed = true;
			}
		}
		else
		{
			pressed = false;
		}
		
		return running;
	}
}
