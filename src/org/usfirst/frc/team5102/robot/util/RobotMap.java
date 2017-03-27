package org.usfirst.frc.team5102.robot.util;

public interface RobotMap
{
	//====================MOTORS====================
	//Drive
	public static final int leftDriveMotor1 = 1;
	public static final int leftDriveMotor2 = 2;
	public static final int rightDriveMotor1 = 3;
	public static final int rightDriveMotor2 = 4;
	
	//Shooter
	public static final int shooterMotor = 13;
	public static final int shooterMotor2 = 7;
	public static final int intakeMotor = 8;
	
	//Climber
	public static final int climbMotor1 = 5;
	public static final int climbMotor2 = 6;
	
	//====================Pneumatics====================
	//Shifter
	public static final int shifterSolenoid = 0;
	
	//Goblet
	public static final int gobletSolenoid = 1;
	
	//Shooter
	public static final int shooterTriggerSolenoid = 3;
	
	//Climber
	public static final int climberExtend = 2;
	
	//====================I/O====================
	//Pneumatics
	public static final int workingPressureSensor = 0;
}
