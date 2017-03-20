package org.usfirst.frc.team5102.robot;

import org.usfirst.frc.team5102.robot.util.CustomTimer;
import org.usfirst.frc.team5102.robot.util.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

public class Shooter extends RobotElement
{
	CANTalon shooterMotor, shooterMotor2;
	Solenoid trigger;
	
	CustomTimer timer;
	
	Shooter()
	{
		super(1);
		
		shooterMotor = new CANTalon(RobotMap.shooterMotor);
		
		shooterMotor2 = new CANTalon(RobotMap.shooterMotor2);
		shooterMotor2.changeControlMode(TalonControlMode.Follower);
		shooterMotor2.set(shooterMotor.getDeviceID());
		
		trigger = new Solenoid(RobotMap.shooterTriggerSolenoid);
		
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
		
		timer = new CustomTimer();
	}
	
	
	
	public void teleop()
	{
		timer.update();
		
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
			if(!timer.isRunning())
			{
				toggleTrigger();
				
				timer.waitFor(.5);
			}
			
			
		}
		else
		{
			trigger.set(false);
		}
		
		//System.out.println(Vision.getTarget(Vision.Target.Balls, Vision.Axis.X));
	}
	
	public void toggleTrigger()
	{
		if(trigger.get())
		{
			trigger.set(false);
		}
		else
		{
			trigger.set(true);
		}
	}
	
	public void autonomous()
	{
		timer.update();
	}
}
