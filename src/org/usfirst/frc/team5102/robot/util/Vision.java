package org.usfirst.frc.team5102.robot.util;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Vision
{
	static NetworkTable targetGears, targetBalls;
	
	public static enum Target
	{
		Gears,
		Balls
	}
	
	public static enum Axis
	{
		X,
		Y
	}
	
	public static void init()
	{
		targetGears = NetworkTable.getTable("GRIP/targetGears");
		targetBalls = NetworkTable.getTable("GRIP/targetBalls");
	}
	
	public static double getTarget(Target target, Axis axis)
	{
		if(target == Target.Gears)
		{
			
		}
		else if(target == Target.Balls)
		{
			double[] targetsX = targetBalls.getNumberArray("centerX", new double[0]);
			double[] targetsY = targetBalls.getNumberArray("centerY", new double[0]);
			
			if(targetsX.length == 2)
			{
				if(targetsY[0] > targetsY[1])
				{
					if(axis == Axis.X)
					{
						return targetsX[0];
					}
					else if(axis == Axis.Y)
					{
						return targetsY[0];
					}
				}
				else
				{
					if(axis == Axis.X)
					{
						return targetsX[1];
					}
					else if(axis == Axis.Y)
					{
						return targetsY[1];
					}
				}
			}
		}
		return 0;
	}
	
	/*
	public static boolean targetFound()
	{
		double[] targets = grip.getNumberArray("centerX", new double[0]);
		
		if(targets.length > 0)
        {
			return true;
        }
		return false;
	}
	
	public static int getLargest()
	{
		double[] areas = grip.getNumberArray("area", new double[0]);
		
		int largestTarget = 0;
		
		if(areas.length > 1)
		{
			double largest = 0;
			
			for(int	c = 0; c < areas.length; c++)	
			{
				if(areas[c] > largest)
				{
					largest = areas[c];
					largestTarget = c;
				}
			}
		}
		return largestTarget;
	}
	*/
}
