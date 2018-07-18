package frc.team4348.robot.KierstynUpdated;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class Robot extends IterativeRobot {
	XboxController xbox = new XboxController(0);
	Data data = new Data(xbox);
	Drive drive = new Drive(0,1,2,3,1.5);
	RobotExtras re = new RobotExtras(4,5,6);

	final String defaultAuto = "Drive Forward Auto";
	final String gearDropAuto = "Gear Drop Auto";
	final String ballDropAuto = "Ball Drop Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
    UsbCamera cam0 = CameraServer.getInstance().startAutomaticCapture("cam0", 0);  
    
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
		//function
		//thing
	}

	@Override
	public void teleopPeriodic() {
		data.getControllerData();
		drive.drive(xbox.getY(Hand.kLeft), xbox.getY(Hand.kRight), xbox.getBackButton());
		re.toggleDoor(xbox.getBumper(Hand.kRight));
		re.suckBalls(xbox.getTriggerAxis(Hand.kRight));
		re.dispenseBalls(xbox.getXButton());	
		re.doorTest(xbox.getTriggerAxis(Hand.kLeft), xbox.getBumper(Hand.kLeft));
		
	}

	@Override
	public void testPeriodic() {
	}	
}
