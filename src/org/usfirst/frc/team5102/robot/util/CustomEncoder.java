package org.usfirst.frc.team5102.robot.util;

public class CustomEncoder
{
	int encDegrees, zeroDegrees;
	double encVoltage;
	double maxVoltage = 5.0;
	
	public CustomEncoder(double encValue)
	{
		encVoltage = encValue;
		zeroDegrees = toDegrees(encValue);
		encDegrees = 0;
	}
	
	public double updateEnc(double encValue)
	{
		if(encValue > encVoltage)
		{
			encDegrees = toDegrees(encValue-encVoltage);
		}
		
		return 0;
	}
	
	int toDegrees(double voltage)
	{
		return (int) ((voltage/maxVoltage)*360);
	}
}
