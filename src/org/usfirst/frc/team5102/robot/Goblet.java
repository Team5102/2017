package org.usfirst.frc.team5102.robot;

import edu.wpi.first.wpilibj.Solenoid;

public class Goblet extends RobotElement
{
	Solenoid gobletSolenoid;
	
	Goblet()
	{
		super(1);
		
		gobletSolenoid = new Solenoid(1);
	}
	
	public void teleop()
	{
		if(controller.getRightTriggerButton())
		{
			gobletSolenoid.set(true);
		}
		else
		{
			gobletSolenoid.set(false);
		}
	}
}
