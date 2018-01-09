package org.usfirst.frc.team5102.robot;

import org.usfirst.frc.team5102.robot.Shifter.Gear;
import org.usfirst.frc.team5102.robot.util.RobotMap;
import org.usfirst.frc.team5102.robot.util.Toggle;
import org.usfirst.frc.team5102.robot.util.TouchlessEncoder;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends RobotElement implements PIDOutput, PIDSource
{
	public WPI_TalonSRX leftDriveMotor1,leftDriveMotor2,rightDriveMotor1,rightDriveMotor2;
	
	public static RobotDrive robotDrive;
	
	public static Shifter shifter;
	
	static double pidSpeed;
	
	static AHRS gyro;
	
	public static PIDController drivePID;
	
	public static Thread aim;
	
	public static TouchlessEncoder driveEncoder;
		
	Toggle orientationToggle;
	
	Drive() 
		{
		super(0);
		
		leftDriveMotor1 = new WPI_TalonSRX(RobotMap.leftDriveMotor1);
		leftDriveMotor2 = new WPI_TalonSRX(RobotMap.leftDriveMotor2);
		rightDriveMotor1 = new WPI_TalonSRX(RobotMap.rightDriveMotor1);
		rightDriveMotor2 = new WPI_TalonSRX(RobotMap.rightDriveMotor2);
		
		leftDriveMotor2.follow(leftDriveMotor1);
		rightDriveMotor2.follow(rightDriveMotor1);
		
		robotDrive = new RobotDrive(leftDriveMotor1,rightDriveMotor1);
		
		shifter = new Shifter();
		
		orientationToggle = new Toggle();
		
		pidSpeed = .5;
		
		
		gyro = new AHRS(SPI.Port.kMXP);
		
		driveEncoder = new TouchlessEncoder(0);
		driveEncoder.reset();
		
		drivePID = new PIDController(0.05, 0.005, 0.3, this, this);
		drivePID.setSetpoint(0);
		drivePID.setOutputRange(-0.5, 0.5);
		drivePID.setAbsoluteTolerance(1);
		
		LiveWindow.addActuator("Drive", "Drive PID", drivePID);
		
		aim = new Thread();
		}
	
	public void aim()
	{
		if(controller.getPOV() == 90)
		{
			robotDrive.arcadeDrive(0, (-.5));
		}
		else if(controller.getPOV() == 270)
		{
			robotDrive.arcadeDrive(0, (0.5));
		}
		else if(controller.getPOV() == 0)
		{
			if(orientationToggle.toggle(controller.getButtonBACK()))
			{
				robotDrive.arcadeDrive(.5,0);
			}
			else
			{
				robotDrive.arcadeDrive(-.5,0);
			}
		}
		else if(controller.getPOV() == 180)
		{
			if(orientationToggle.toggle(controller.getButtonBACK()))
			{
				robotDrive.arcadeDrive(-.5,0);
			}
			else
			{
				robotDrive.arcadeDrive(.5,0);
			}
		}
		else
		{
			robotDrive.arcadeDrive(0, 0);
		}
	}
	
	public static void driveAuton(int targetTicks, int msPerTick, int direction)
	{
		//Drive.gyro.reset();
		
		Drive.shifter.shiftGears(Gear.low);
		/*
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		drivePID.setPID(0.05, 0.005, 0.3);
		drivePID.setOutputRange(-0.5, 0.5);
		
		if(direction > 0)
		{
			Drive.setPIDSpeed(-.40);
		}
		else if(direction < 0)
		{
			Drive.setPIDSpeed(.40);
		}
		
		//Drive.drivePID.enable();
		Drive.driveEncoder.reset();
		
		//int targetTicks = 200;
		//int msPerTick = 30;
		
		while(driveEncoder.getTicks() < targetTicks)
		{
			//System.out.println(Drive.driveEncoder.getTicks());
			
			if(targetTicks - driveEncoder.getTicks() < 100-msPerTick)
			{
				msPerTick = 70;
			}
			
			if(driveEncoder.getTimePerTick() < msPerTick)
			{
				if(pidSpeed < 0)
				{
					pidSpeed += 0.001;
				}
				else
				{
					pidSpeed -= 0.001;
				}
				
			}
			else if(driveEncoder.getTimePerTick() > msPerTick)
			{
				if(pidSpeed < 0)
				{
					pidSpeed -= 0.001;
				}
				else
				{
					pidSpeed += 0.001;
				}
			}
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			driveEncoder.update();
		}
		//drivePID.disable();
	}
	
	public static void turnTo(double angle)
	{
		Drive.drivePID.setPID(0.05, 0.02, 0.2);
		
		Drive.drivePID.setOutputRange(-.4, .4);
		
		Drive.setPIDSpeed(0);
		Drive.drivePID.setSetpoint(angle);
		
		while(!drivePID.onTarget()){}
		//Drive.drivePID.enable();
	}
	
	public void teleop()
	{
		//System.out.println(enc.get());
		
		if(controller.getPOV() > -1)
		{
			aim();
		}
		else
		{
			if(orientationToggle.toggle(controller.getButtonBACK()))
			{
				robotDrive.arcadeDrive(-controller.getLeftStickY(),-controller.getRightStickX());
				
				SmartDashboard.putString("Drive Mode", "Gear");
			}
			else
			{
				robotDrive.arcadeDrive(controller.getLeftStickY(),-controller.getRightStickX());
				
				SmartDashboard.putString("Drive Mode", "Shooter");
			}
		}
		
		
		
		//robotDrive.arcadeDrive(controller.getLeftStickY(),-controller.getRightStickX());
		
		
		//==========Shifter==========
		
				if(controller.getLeftTriggerButton())
				{ 
					shifter.shiftGears(Shifter.Gear.low);
					System.out.println("low gear");
				}
				
				if(controller.getRightTriggerButton())
				{ 
					shifter.shiftGears(Shifter.Gear.high);
					System.out.println("high gear");
				}
				
				//System.out.println(gyro.getAngle());
		
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
		return Drive.gyro.getAngle();
	}

	@Override
	public void pidWrite(double output)
	{
		System.out.println("pidWrite - " + output + " Gyro: " + gyro.getAngle());
		
		robotDrive.arcadeDrive(pidSpeed,-output);
	}
	
	public static void setPIDSpeed(double speed)
	{
		pidSpeed = speed;
	}
	
	public static double getPIDSpeed()
	{
		return pidSpeed;
	}
}
