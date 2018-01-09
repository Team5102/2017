package org.usfirst.frc.team5102.robot.util;

import edu.wpi.first.wpilibj.DigitalInput;

public class TouchlessEncoder
{
	DigitalInput encPort;
	
	int ticks;
	
	boolean triggered;
	
	long time, timePerTick;
	
	public TouchlessEncoder(int port)
	{
		encPort = new DigitalInput(port);
		ticks = 0;
		
		triggered = encPort.get();
		
		time = System.currentTimeMillis();
		timePerTick = 0;
	}
	
	public void update()
	{
		if(encPort.get())
		{
			if(!triggered)
			{
				ticks++;
				triggered = true;
				
				timePerTick = System.currentTimeMillis()-time;
				time = System.currentTimeMillis();
				
				System.out.println("Time per tick:  " + timePerTick);
			}
		}
		else
		{
			if(triggered)
			{
				ticks++;
				triggered = false;
			}
			
		}
	}
	
	public int getTicks()
	{
		return ticks;
	}
	
	public void reset()
	{
		ticks = 0;
	}
	
	public int getTimePerTick()
	{
		return (int)timePerTick;
	}
}
