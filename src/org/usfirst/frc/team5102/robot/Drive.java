package org.usfirst.frc.team5102.robot;

import org.usfirst.frc.team5102.robot.util.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.RobotDrive;

public class Drive extends RobotElement
{
	public CANTalon leftDriveMotor1,leftDriveMotor2,rightDriveMotor1,rightDriveMotor2;
	
	public static RobotDrive robotDrive;
	
	Shifter shifter;
	
	Drive() 
		{
		super(0);
		
		leftDriveMotor1 = new CANTalon(RobotMap.leftDriveMotor1);
		leftDriveMotor2 = new CANTalon(RobotMap.leftDriveMotor2);
		rightDriveMotor1 = new CANTalon(RobotMap.rightDriveMotor1);
		rightDriveMotor2 = new CANTalon(RobotMap.rightDriveMotor2);
		
		leftDriveMotor2.changeControlMode(TalonControlMode.Follower);
		leftDriveMotor2.set(leftDriveMotor1.getDeviceID());
		rightDriveMotor2.changeControlMode(TalonControlMode.Follower);
		rightDriveMotor2.set(rightDriveMotor1.getDeviceID());
		
		robotDrive = new RobotDrive(leftDriveMotor1,rightDriveMotor1);
		
		shifter = new Shifter();
		}
	
	public void teleop()
	{
		robotDrive.arcadeDrive(controller.getLeftStickY(),-controller.getRightStickX());
		
		
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
				
				System.out.println(rightDriveMotor1.get());
		
	}
}
