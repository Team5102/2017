package org.usfirst.frc.team5102.robot;

import org.usfirst.frc.team5102.robot.util.Xbox;

public class RobotElement
{	
	protected Xbox controller;
	//protected LaunchPad launchpad;
	
	RobotElement(int port)
	{
		controller = new Xbox(port);
		//launchpad = new LaunchPad();
	}
	
	enum Mode
	{
		teleop,
		auton,
		test,
		disabled
	}
	
	public void init(Mode mode)
	{
		
	}
	
	public void teleop()
	{
		
	}
	public void autonomous()
	{
		
	}
	public void test()
	{
		
	}
}

