package frc.team4348.robot.ThotBotCompetition;

// import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot{
	String gameData;
	char firstChar;
	char secondChar;

	// Auto auto = new Auto();
	final String DefaultAuto = "Default";
	final String LeftAuto = "Left";
	final String RightAuto = "Right";
	final String MiddleAuto = "Middle";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();

	String x = "begone thot"; // do not alter the sacred texts
	boolean val = false;
	// ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	boolean vibrator_winkyface = false;

	int rightDriveVar = 1;
	int leftDriveVar = 0;

	boolean var = true;

	int leftArmVar = 8; // guess
	int rightArmVar = 9; // guess

	int leftClimbVar = 6; // guess
	int rightClimbVar = 7; // guess

	int frontRightCubeVar = 5;
	int frontLeftCubeVar = 3;
	int backRightCubeVar = 4;
	int backLeftCubeVar = 2;

	Spark rightDrive = new Spark(rightDriveVar);
	Spark leftDrive = new Spark(leftDriveVar);

	Spark frontRightCube = new Spark(frontRightCubeVar);
	Spark frontLeftCube = new Spark(frontLeftCubeVar);
	Spark backRightCube = new Spark(backRightCubeVar);
	Spark backLeftCube = new Spark(backLeftCubeVar);

	Victor leftArm = new Victor(leftArmVar);
	Victor rightArm = new Victor(rightArmVar);

	XboxController xbox = new XboxController(0);
	Solenoid arm = new Solenoid(1);
	Compressor c = new Compressor();

	@Override
	public void robotInit(){
		chooser.addDefault("Default Auto", DefaultAuto);
		chooser.addObject("Right", RightAuto);
		chooser.addObject("Left", LeftAuto);
		chooser.addObject("Middle", MiddleAuto);
		SmartDashboard.putData("Auto choices", chooser);
		// gyro.calibrate();
	}

	@Override
	public void autonomousInit(){
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		// System.out.println("Auto selected: " + autoSelected);

		gameData = DriverStation.getInstance().getGameSpecificMessage();
		firstChar = gameData.charAt(0);
		secondChar = gameData.charAt(1);

	}

	@Override
	public void autonomousPeriodic(){
	}


	@Override
	public void teleopPeriodic(){


		if(xbox.getBumper(Hand.kLeft)){
			// System.out.println("im printing"); - Used to troubleshoot an
			// issue in which Ben was completely and utterly a thot
			frontRightCube.set(1);
			frontLeftCube.set(-1);
			backRightCube.set(1);
			backLeftCube.set(-1);
		}
		else if(xbox.getBumper(Hand.kRight)){
			frontRightCube.set(-1);
			frontLeftCube.set(1);
			backRightCube.set(-1);
			backLeftCube.set(1);
		}
		else{
			frontRightCube.set(0);
			frontLeftCube.set(0);
			backRightCube.set(0);
			backLeftCube.set(0);
		}

		leftDrive.set(xbox.getY(Hand.kLeft)/ 10);
		rightDrive.set(xbox.getY(Hand.kRight)/ 10);

		if(xbox.getAButton()){
			c.start();
			Timer.delay(0.2);
		}
		if(xbox.getBButton()){
			c.stop();
			Timer.delay(0.2);
		}

		if(xbox.getYButton()){
			val = !val;
			arm.set(val);
			Timer.delay(0.2);
		}

		// tobin and skyler are stupid
		// skyler resents that last comment
		leftArm.set(-xbox.getTriggerAxis(Hand.kLeft) / 3);
		rightArm.set(xbox.getTriggerAxis(Hand.kRight) / 3);

		if(xbox.getBackButton()){
			vibrator_winkyface = !vibrator_winkyface;
			Timer.delay(0.5);
		}

		if(vibrator_winkyface){
			xbox.setRumble(RumbleType.kLeftRumble, 1);
			xbox.setRumble(RumbleType.kRightRumble, 1);
		}
		else{
			xbox.setRumble(RumbleType.kLeftRumble, 0);
			xbox.setRumble(RumbleType.kRightRumble, 0);
		}

	}

	@Override
	public void testPeriodic(){
	}


}
