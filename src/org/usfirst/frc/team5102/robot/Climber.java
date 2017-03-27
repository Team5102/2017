package org.usfirst.frc.team5102.robot;

import org.usfirst.frc.team5102.robot.util.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Solenoid;

public class Climber extends RobotElement
{
	CANTalon climbMotor1, climbMotor2;
	
	Solenoid climberExtend;
	
	Climber()
	{
		super(1);
		
		climbMotor1 = new CANTalon(RobotMap.climbMotor1);
		climbMotor2 = new CANTalon(RobotMap.climbMotor2);
		
		climbMotor1.setSafetyEnabled(false);
		climbMotor2.setSafetyEnabled(false);
		
		climberExtend = new Solenoid(RobotMap.climberExtend);
	}
	
	public void teleop()
	{
		if(controller.getButtonY())
		{
			climberExtend.set(true);
			
			climbMotor1.set(controller.getLeftTriggerAxis());
			climbMotor2.set(controller.getLeftTriggerAxis());
		}
		else
		{
			climberExtend.set(false);
			
			climbMotor1.set(0);
			climbMotor2.set(0);
		}
		
		/*
		if(controller.getButtonY() && controller.getPOV() == 0)
		{
			climbMotor1.set(1);
			climbMotor2.set(1);
		}
		else
		{
			climbMotor1.set(0);
			climbMotor2.set(0);
		}
		*/
	}
}
