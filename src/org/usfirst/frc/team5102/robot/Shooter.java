package org.usfirst.frc.team5102.robot;

import org.usfirst.frc.team5102.robot.util.RobotMap;
import org.usfirst.frc.team5102.robot.util.Vision;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Solenoid;

public class Shooter extends RobotElement
{
	CANTalon shooterMotor;
	Solenoid trigger;
	
	Shooter()
	{
		super(1);
		
		shooterMotor = new CANTalon(RobotMap.shooterMotor);
		
		trigger = new Solenoid(3);
		
		/*
		shooterMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		shooterMotor.reverseSensor(false);
		//shooterMotor.setPID(1, 0, 0, 0.5,  0, .1, 0);
		
		shooterMotor.configNominalOutputVoltage(+0.0f, -0.0f);
		shooterMotor.configPeakOutputVoltage(+12.0f, -12.0f);

		
		shooterMotor.setProfile(0);
		shooterMotor.setP(1);
		shooterMotor.setI(0);
		shooterMotor.setD(0);
		shooterMotor.setF(50);
		//shooterMotor.setMotionMagicAcceleration(2);
		//shooterMotor.setMotionMagicCruiseVelocity(10000);
		//shooterMotor.setSetpoint(4);
		 */
		shooterMotor.setSafetyEnabled(false);
	}
	
	
	
	public void teleop()
	{
		//shooterMotor.changeControlMode(TalonControlMode.Speed);
		//shooterMotor.set(4);
		//shooterMotor.enableControl();
		
		//shooterMotor.set(controller.getLeftStickY());
		
		if(controller.getButtonA())
		{
			shooterMotor.set(-1);
		}
		else
		{
			shooterMotor.set(0);
		}
		
		//System.out.println(shooterMotor.getEncVelocity());
		
		if(controller.getButtonB())
		{
			trigger.set(true);
		}
		else
		{
			trigger.set(false);
		}
		
		System.out.println(Vision.getTarget(Vision.Target.Balls, Vision.Axis.X));
	}
}
