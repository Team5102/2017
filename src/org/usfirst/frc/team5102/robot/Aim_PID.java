package org.usfirst.frc.team5102.robot;

import org.usfirst.frc.team5102.robot.util.Vision;
import org.usfirst.frc.team5102.robot.util.Vision.Axis;
import org.usfirst.frc.team5102.robot.util.Vision.Target;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
	TODO: Look at comment formatting and line lengths.
	Can this not just be wrapped in with {@link Aim.java}?
*/
public class Aim_PID extends Thread implements PIDSource, PIDOutput
{
	static int targetX = 335;
	static int targetY = 290;
	
	double MPM = -0.00;									//motor power multiplier
	
	enum AimState
	{
		notAiming,
		aimX,
		aimY,
		shoot
	}
	
	enum AimMode
	{
		fast,
		accurate
	}
	
	static AimState state = AimState.notAiming;
	
	// TODO: I think we may just want to implement PIDController? Or PidSubSystem?
	PIDController aimPID;
	
	public Aim_PID()
	{
		aimPID = new PIDController(0.005, 0.01, 0.01, this, this);
		aimPID.setSetpoint(targetX);
		aimPID.setOutputRange(-0.38, 0.38);
		aimPID.setAbsoluteTolerance(.5);
		
		aimPID.setInputRange(0, 700);
		
		LiveWindow.addActuator("Aim", "Aim PID", aimPID);
		
		aimPID.disable();
	}
	
	public void run()
	{
		System.out.println("running thread");
		//Drive.aiming = true;
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		state = AimState.aimX;
		boolean running = aimX(AimMode.fast);					//aims X-axis
		
		if(running)									//determines if thread was canceled (target lost)
		{
			running = aimY();						//drive forward/backward to aim Y-axis
			state = AimState.aimY;
		}
		
		if(running)									//determines if thread was canceled (target lost)
		{
			pause(300);
			
			running = aimX(AimMode.fast);						//re-aims X-axis
			state = AimState.aimX;
		}
		/*
		if(running)									//determines if thread was canceled (target lost)
		{
			pause(500);
			
			running = aimX(AimMode.accurate);						//double checks X-axis aim
		}
		*/
        	
        System.out.println("aiming false");
        //Drive.aiming = false;
        //aimPID.disable();
        
        if(running)									//determines if thread was canceled (target lost)
		{
        	state = AimState.shoot;
        	//Shooter.shooting = true;				//shoots ball
		}
        else
        {
        	state = AimState.notAiming;
        }
        
	}
	
	public boolean aimX(AimMode mode)
	{
		/*
		double currentX = Vision.getTargetX();		//read GRIP array
        
        if(Vision.targetFound())														//determines if target is in view
        {
        	int i = 0;
        	
        	aimPID.enable();
        	while(true)
        	{
        		if(Thread.currentThread().isInterrupted()) {return false;}
        	
        		if(!Vision.targetFound()) {return false;}
        		
        		if(aimPID.onTarget())
        		{
        			i++;
        		}
        		else
        		{
        			i = 0;
        		}
        		
        		if(i > 100)
        		{
        			System.out.println("Target Found: " + Vision.getTargetX());
        			
        			break;
        		}
        	}
        	aimPID.disable();
        	
        	Drive.robotDrive.arcadeDrive(0.0, 0.0);
        	
        	
        	
        	
        	
        	return true;
        }
        
		*/
		if(mode == AimMode.fast)
		{
			double currentX = Vision.getTargetBalls(Axis.X);		//read GRIP array
	        
	        if(Vision.targetFound(Target.Balls))														//determines if target is in view
	        {
	        	while(!(currentX > (targetX-10) && currentX < (targetX+10)))
	        	{
	        		if(Thread.currentThread().isInterrupted()) {return false;}
	        	
	        		if(!Vision.targetFound(Target.Balls))												//determines if target is in view
	        		{
	        			return false;													//cancels if no target is found
	        		}
	        		else
	        		{
	        			currentX = Vision.getTargetBalls(Axis.X);
	        			
	        			if(currentX < targetX)
	        			{
	        				Drive.robotDrive.tankDrive(-0.35-MPM, 0.36+MPM);
	        			}
	        			else if(currentX > targetX)
	        			{
	        				Drive.robotDrive.tankDrive(0.35+MPM, -0.38-MPM);
	        			}
	        		}
	        	}
	        }
		}
		else if(mode == AimMode.accurate)
		{
			double error = targetX - Vision.getTargetBalls(Axis.X);
			double degrees = error/10;
			
			aimPID.setSetpoint(-degrees);
			Drive.gyro.reset();
			
			aimPID.enable();
			
			int i = 0;
			
        	while(true)
        	{
        		if(Thread.currentThread().isInterrupted()) {return false;}
        	
        		if(!Vision.targetFound(Target.Balls)) {return false;}
        		
        		if(aimPID.onTarget())
        		{
        			i++;
        		}
        		else
        		{
        			i = 0;
        		}
        		
        		if(i > 100)
        		{
        			System.out.println("Target Found: " + Vision.getTargetBalls(Axis.X));
        			
        			break;
        		}
        	}
        	aimPID.disable();
		}
		
        return true;
	}
	
	public boolean aimY()
	{
		double currentY = Vision.getTargetBalls(Axis.Y);		//read GRIP array
        
        if(Vision.targetFound(Target.Balls))														//determines if target is in view
        {
        	while(!(currentY > (targetY-10) && currentY < (targetY+10)))
        	{
        		if(Thread.currentThread().isInterrupted()) {return false;}
        		
        		if(!Vision.targetFound(Target.Balls))												//determines if target is in view
        		{
        			return false;													//cancels if no target is found
        		}
        		else
        		{
        			currentY = Vision.getTargetBalls(Axis.Y);
        			
        			if(currentY < targetY)
        			{
        				Drive.robotDrive.tankDrive(0.35-MPM, 0.35-MPM);
        			}
        			else if(currentY > targetY)
        			{
        				Drive.robotDrive.tankDrive(-0.35+MPM, -0.35+MPM);
        			}
        		}
        	}
        	
        	Drive.robotDrive.arcadeDrive(0.0, 0.0);
        	
        	return true;
        }
        return false;
	}
	
	public boolean pause(int millis)
	{
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			return true;
		}
		return false;
	}

	@Override
	public void pidWrite(double output)
	{
		Drive.robotDrive.arcadeDrive(0, -output);
		
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PIDSourceType getPIDSourceType()
	{
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet()
	{
		/*
		double currentX = Vision.getTargetBalls(Axis.X);
		
		if(currentX == 0)
		{
			aimPID.disable();
		}
		return currentX;
		*/
		
		return Drive.gyro.getAngle();
	}
}
