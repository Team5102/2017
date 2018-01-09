
package org.usfirst.frc.team5102.robot;


import org.usfirst.frc.team5102.robot.util.DriverStation;
import org.usfirst.frc.team5102.robot.util.DriverStation.InfoStripMode;
import org.usfirst.frc.team5102.robot.util.DriverStation.RobotMode;
import org.usfirst.frc.team5102.robot.util.Vision;
import org.usfirst.frc.team5102.robot.util.Vision.Axis;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{
    
    String autoSelected;
    SendableChooser chooser;
    
    
    public Drive drive;
    public Shooter shooter;
    public Climber climber;
    public Goblet goblet;
    public Autonomous auton;
    
    public static DriverStation driverStation;
    
    //private ArduinoComm arduinoComm;
    
    //DigitalOutput encOut1,encOut2;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("No Auton", "No Auton");
        chooser.addObject("Place Gear", "Place Gear");
        chooser.addObject("Drive Forward", "Drive Forward");
        chooser.addObject("Shoot Balls", "Shoot Balls");
        SmartDashboard.putData("Autonomous Selector", chooser);
        
        //SmartDashboard.putNumber("Test", 12345);
        
        drive = new Drive();
        shooter = new Shooter();
        climber = new Climber();
        goblet = new Goblet();
        auton = new Autonomous();
        
        Vision.init();
        
        driverStation = new DriverStation(2);
        
        //arduinoComm = new ArduinoComm(2);
        
        UsbCamera gobletCam = CameraServer.getInstance().startAutomaticCapture(0);
        gobletCam.setFPS(10);
        
        UsbCamera climberCam = CameraServer.getInstance().startAutomaticCapture(1);
        climberCam.setFPS(10);
        
        /*
        encOut1 = new DigitalOutput(6);
        encOut2 = new DigitalOutput(7);
        
        new Thread(() ->
        {
        	while(true)
        	{
        		encOut1.set(ArduinoComm.launchpad.getRawButton(8));
            	encOut2.set(ArduinoComm.launchpad.getRawButton(9));
            	//System.out.println("thread run");
        	}
        	
        }).start();
        */
    }
    
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
		
		driverStation.setMode(RobotMode.auton);
		
		shooter.shooterLEDRing.set(true);
		
		switch(autoSelected)
		{
    		case "No Auton":
    			break;
    		case "Place Gear":
    			auton.placeGear();
    			break;
    		case "Drive Forward":
    			auton.driveForward();
    			break;
    		case "Shoot Balls":
    			auton.shootBalls();
    			break;
    	}
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic()
    {
    	updateAirMeter();
    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopInit()
    {
    	driverStation.setMode(RobotMode.teleop);
    	
    	drive.gyro.reset();
    	
    	shooter.shooterLEDRing.set(true);
    }
    
    public void teleopPeriodic()
    {
        drive.teleop();
        shooter.teleop();
        climber.teleop();
        goblet.teleop();
        
        updateAirMeter();
                
        //System.out.println(Vision.getTargetBalls(Axis.X) + "  -  " + Vision.getTargetBalls(Axis.Y));
    }
    
    /**
     * This function is called periodically during test mode
     */
    
    public void disabledInit()
    {
    	driverStation.setMode(RobotMode.disabled);
    	
    	shooter.reset();
    	
    	shooter.shooterLEDRing.set(false);
    	
    	if(Drive.aim != null)
    	{
    		Drive.aim.interrupt();
    	}
    	
    }
    
    public void disabledPeriodic()
    {
    	
    	
    	//System.out.println(encOut1.get() + "   " + encOut2.get());
    	
    	/*
    	if(drive.controller.getButtonBACK())
    	{
    		arduinoComm.stripMode = InfoStripMode.info;
    	}
    	else
    	{
    		arduinoComm.stripMode = InfoStripMode.airPressure;
    	}
    	*/
    	
    	
    	//arduinoComm.getPot();
    	
    	//System.out.println(climber.controller.getPOV());
    	
    	updateAirMeter();
    	
    	//Drive.driveEncoder.update();
    	//System.out.println(Drive.driveEncoder.getTicks());
    }
    
    public void testPeriodic() {
    
    }
    
    public void updateAirMeter()
    {
    	driverStation.setInfoStrip(drive.shifter.getWorkingPSI(), InfoStripMode.airPressure);
    }
    
}
