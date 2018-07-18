package frc.team4348.robot.Kiersytn;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class Robot extends IterativeRobot {
	final String defaultAuto = "Drive Forward Auto";
	final String gearDropAuto = "Gear Drop Auto";
	final String ballDropAuto = "Ball Drop Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();

    XboxController xbox = new XboxController(0);
    Drive drive = new Drive(0, 1, 2, 3, 0);
	UsbCamera cam0 = CameraServer.getInstance().startAutomaticCapture("cam0", 0);
	  
    final int ON = 1;
	final int OFF = 0;
	
    boolean reverseDrive = false;
    
	@Override
	public void robotInit() {
		chooser.addDefault("Drive Forward Auto", defaultAuto);
		chooser.addObject("Gear Drop Auto", gearDropAuto);
		chooser.addObject("Ball Drop Auto", ballDropAuto);
		SmartDashboard.putData("Auto choices", chooser);
		
	}

	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		System.out.println("Auto selected: " + autoSelected);
	}

	@Override
	public void autonomousPeriodic() {
		//drive.autoChooser(autoSelected);
		//Timer.delay(100);
	}

	@Override
	public void teleopPeriodic() {
		//Xbox Controller info on the Smart Dashboard
        SmartDashboard.putBoolean("A is Pressed: ", xbox.getAButton());
        SmartDashboard.putBoolean("B is Pressed: ", xbox.getBButton());
        SmartDashboard.putBoolean("X is Pressed: ", xbox.getXButton());
        SmartDashboard.putBoolean("Y is Pressed: ", xbox.getYButton());
        SmartDashboard.putBoolean("LB is Pressed: ", xbox.getBumper(Hand.kLeft));
        SmartDashboard.putBoolean("RB is Pressed: ", xbox.getBumper(Hand.kRight));
        SmartDashboard.putBoolean("Back is Pressed: ", xbox.getBackButton());
        SmartDashboard.putBoolean("Start is Pressed: ", xbox.getStartButton());
        SmartDashboard.putBoolean("LS is Pressed: ", xbox.getStickButton(Hand.kLeft));
        SmartDashboard.putBoolean("RS is Pressed: ", xbox.getStickButton(Hand.kRight));
        SmartDashboard.putNumber("Left Y Axis: ", xbox.getY(Hand.kLeft));
        SmartDashboard.putNumber("Left X Axis: ", xbox.getX(Hand.kLeft));
        SmartDashboard.putNumber("Right Y Axis: ", xbox.getY(Hand.kRight));
        SmartDashboard.putNumber("Right X Axis: ", xbox.getX(Hand.kRight));
        SmartDashboard.putNumber("Left Trigger: ", xbox.getTriggerAxis(Hand.kLeft));
        SmartDashboard.putNumber("Right Trigger: ", xbox.getTriggerAxis(Hand.kRight));
        SmartDashboard.putNumber("Match Timer: ", Timer.getMatchTime());
    	SmartDashboard.putBoolean("Reverse Drive: ", reverseDrive);
		
        if(xbox.getBackButton() == true) {
        	reverseDrive = !reverseDrive;
		}
		
    	//Tank Drive
    	if(reverseDrive == false) {
    		drive.tankDriveBallSystem(1.3);
    	} else {
    		drive.reverseTankDriveBallSystem(1.3);
    	}
		Timer.delay(0.02);
		
	}

	@Override
	public void testPeriodic() {
	}	
}