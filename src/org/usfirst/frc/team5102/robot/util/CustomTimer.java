package org.usfirst.frc.team5102.robot.util;

import edu.wpi.first.wpilibj.Timer;

public class CustomTimer
{
	Timer timer;
	boolean timerRunning;
	double timeToWait;
	
	public CustomTimer()
	{
		timer = new Timer();
		timerRunning = false;
		timeToWait = 0;
	}
	
	public void waitFor(double seconds)
	{
		timer.stop();
		timerRunning = true;
		timer.start();
		timeToWait = seconds;
	}
	
	public boolean isRunning()
	{
		return timerRunning;
	}
	
	public void update()
	{
		if(timer.get() >= timeToWait)
		{
			timer.stop();
			timer.reset();
			timeToWait = 0;
			timerRunning = false;
		}
	}
}
