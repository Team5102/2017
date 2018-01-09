package org.usfirst.frc.team5102.robot;

import org.usfirst.frc.team5102.robot.Shifter.Gear;
import org.usfirst.frc.team5102.robot.util.CustomTimer;

public class Autonomous
{
	
	CustomTimer autonTimer;
	
	Autonomous()
	{
		autonTimer = new CustomTimer();
	}
	
	public void placeGear()
	{
		new Thread()
		{
			public void run()
			{
				Goblet.gobletSolenoid.set(false);
				
				/*
				System.out.println("running thread");
				
				Drive.driveAuton(203, 20, -1);
				
				//waitFor(500);
				
				Goblet.gobletSolenoid.set(true);
				*/
				
				Drive.gyro.reset();
				
				Drive.drivePID.enable();
				
				Drive.driveAuton(100, 20, -1);
				
				Drive.turnTo(-30);
				
				Drive.driveAuton(100, 20, -1);
				
				Drive.turnTo(0);
				
				Drive.driveAuton(210, 25, -1);
				
				Drive.turnTo(-90);
				
				Drive.driveAuton(115, 20, -1);
				
				//waitFor(60000);
				
				Drive.drivePID.disable();
				
				Goblet.gobletSolenoid.set(true);
			}
		}.run();
	}
	
	public void driveForward()
	{
		new Thread()
		{
			public void run()
			{
				System.out.println("running thread");
				
				Drive.driveAuton(400, 20, 1);
				
				waitFor(500);
				
				Drive.driveAuton(400, 20, -1);
			}
		}.run();
	}
	
	public void shootBalls()
	{
		Drive.aim = new Aim_PID();
		Drive.aim.start();
	}
	
	void waitFor(int ms)
	{
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
