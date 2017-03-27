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
				System.out.println("running thread");
				
				Drive.gyro.reset();
				
				Drive.shifter.shiftGears(Gear.low);
				
				Drive.setPIDSpeed(.7);
				
				Drive.drivePID.enable();
				
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Drive.drivePID.disable();
				
				Goblet.gobletSolenoid.set(true);
				
				Drive.robotDrive.tankDrive(-.5, -.5);
				
				try {
					Thread.sleep(420);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Drive.robotDrive.tankDrive(0, 0);
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
				
				Drive.gyro.reset();
				
				Drive.shifter.shiftGears(Gear.low);
				
				Drive.setPIDSpeed(1);
				
				Drive.drivePID.enable();
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Drive.drivePID.disable();
			}
		}.run();
	}
	
	public void shootBalls()
	{
		Drive.aim = new Aim_PID();
		Drive.aim.start();
	}
}
