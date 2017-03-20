package org.usfirst.frc.team5102.robot;

import com.ctre.CANTalon;

public class Climber extends RobotElement
{
	CANTalon climbMotor1, climbMotor2;
	
	Climber()
	{
		super(1);
		
		climbMotor1 = new CANTalon(5);
		climbMotor2 = new CANTalon(6);
	}
	
	public void teleop()
	{
		if(controller.getButtonY())
		{
			climbMotor1.set(1);
			climbMotor2.set(1);
		}
		/*
		else if(controller.getButtonX())
		{
			climbMotor1.set(-1);
			climbMotor2.set(-1);
		}
		*/
		else
		{
			climbMotor1.set(0);
			climbMotor2.set(0);
		}
	}
}
