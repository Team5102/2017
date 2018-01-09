package org.usfirst.frc.team5102.robot;

import org.usfirst.frc.team5102.robot.util.CustomTimer;
import org.usfirst.frc.team5102.robot.util.LaunchPadPot;
import org.usfirst.frc.team5102.robot.util.RobotMap;
import org.usfirst.frc.team5102.robot.util.Toggle;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;

public class Shooter extends RobotElement
{
	WPI_TalonSRX shooterMotor, shooterMotor2, intakeMotor;
	Solenoid trigger;
	
	CustomTimer timer;
	
	Solenoid shooterLEDRing;
	
	Toggle intakeToggle;
	
	boolean pressed;
	
	Shooter()
	{
		super(1);
		
		shooterMotor = new WPI_TalonSRX(RobotMap.shooterMotor);
		
		shooterMotor2 = new WPI_TalonSRX(RobotMap.shooterMotor2);
		shooterMotor2.follow(shooterMotor);
		
		intakeMotor = new WPI_TalonSRX(RobotMap.intakeMotor);
		
		trigger = new Solenoid(RobotMap.shooterTriggerSolenoid);
		
		shooterLEDRing = new Solenoid(7);
		
		intakeToggle = new Toggle();
		
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
		
		pressed = false;
	}
	
	
	
	public void teleop()
	{
		timer.update();
		
		//shooterMotor.changeControlMode(TalonControlMode.Speed);
		//shooterMotor.set(4);
		//shooterMotor.enableControl();
		
		//shooterMotor.set(controller.getLeftStickY());
		
		/*
		if(controller.getButtonA())
		{
			if(!pressed)
			{
				if(shooterMotor.get() == 0)
				{
					shooterMotor.set(-7.5);
				}
				else
				{
					shooterMotor.set(0);
				}
				pressed = true;
			}
		}
		else
		{
			pressed = false;
		}
		*/
		
		if(controller.getLeftTriggerAxis() > .5 && !controller.getButtonY())
		{
			shooterMotor.set((-((LaunchPadPot.getScaledPot()/2)+6))/12);
		}
		else
		{
			shooterMotor.set(0);
		}
		
		//System.out.println(shooterMotor.getEncVelocity());
		
		if(controller.getRightTriggerAxis() > .5)
		{
			if(!timer.isRunning())
			{
				toggleTrigger();
				
				//timer.waitFor(.5);
			}
			
			
		}
		else
		{
			trigger.set(false);
		}
		
		if(intakeToggle.toggle(controller.getButtonX()))
		{
			intakeMotor.set(-1);
		}
		else
		{
			intakeMotor.set(0);
		}
		
		//System.out.println(Vision.getTarget(Vision.Target.Balls, Vision.Axis.X));
	}
	
	public void toggleTrigger()
	{
		if(trigger.get())
		{
			trigger.set(false);
			
			timer.waitFor(.8);
		}
		else
		{
			trigger.set(true);
			
			timer.waitFor(.2);
		}
	}
	
	public void reset()
	{
		pressed = false;
		shooterMotor.set(0);
	}
	
	public void autonomous()
	{
		timer.update();
	}
}
