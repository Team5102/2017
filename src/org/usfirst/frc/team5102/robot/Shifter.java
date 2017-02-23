package org.usfirst.frc.team5102.robot;

import java.text.DecimalFormat;

import org.usfirst.frc.team5102.robot.util.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.ControllerPower;
import edu.wpi.first.wpilibj.Solenoid;

public class Shifter extends RobotElement
{
	public static Solenoid shifter;

	AnalogInput workingPressureSensor;
	
	DecimalFormat df;
	
	enum Gear
	{
		low,
		high
	}
	
	public Shifter()
	{
		super(0);
		
		shifter = new Solenoid(RobotMap.shifterSolenoid);
		shifter.set(true);
		
		//storedPressureSensor = new AnalogInput(RobotMap.storedPressureSensor);
		workingPressureSensor = new AnalogInput(RobotMap.workingPressureSensor);
		
		df = new DecimalFormat("###.##");
	}
	
	
	/**
	 * 
	 * @param gear	Gear to be shifted.
	 */
	public void shiftGears(Gear gear)
	{
		if(gear == Gear.low)
		{
			shifter.set(true);
		}
		
		else if(gear == Gear.high)
		{
			shifter.set(false);
		}
	}
	
	/**
	 * @return	An enumerated type denoting the current gear state. 
	 */
	public Gear getCurrentGear()
	{
		if(shifter.get())
		{
			return Gear.low;
		}
		else if(!shifter.get())
		{
			return Gear.high;
		}
		return Gear.low;
	}
	
	public double getWorkingPSI()
	{
		return Double.parseDouble((df.format(250*(workingPressureSensor.getVoltage()/ControllerPower.getVoltage5V())-25)));	//4.51
	}
}
