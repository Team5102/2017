package org.usfirst.frc.team5102.robot;

import org.usfirst.frc.team5102.robot.util.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;

public class Goblet extends RobotElement
{
	Solenoid gobletSolenoid;
	
	Goblet()
	{
		super(1);
		
		gobletSolenoid = new Solenoid(RobotMap.gobletSolenoid);
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
