package frc.team4348.robot.Kiersytn;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

@SuppressWarnings("unused")
public class Drive {

	private final int ZERO = 0;
	private final int ONE = 1;
	private final int TWO = 2;
	private final int THREE = 3;
	private final int FOUR = 4;
	private final int FIVE = 5;
	private final int SIX = 6;
	
	private final double THREE_QUARTERS = 0.75;
	private final double HALF = 0.5;
	private final double QUARTER = 0.25;
	private final double OPEN_TIMER = ONE; // How long to run open code
	private final double CLOSE_TIMER = ONE; // How long to run close code
	private final double OPEN_SPEED = HALF; // Open speed
	private final double CLOSE_SPEED = -HALF; // Close speed
	
	private final double DANCE_SPEED = HALF;
	private final double DANCE_TIMER = ONE;
	
	private final boolean OPEN = true;
	private final boolean CLOSED = false;

	private final String defaultAuto = "Drive Forward Auto";
	private final String gearDropAuto = "Gear Drop Auto";
	private final String ballDropAuto = "Ball Drop Auto";

	private boolean door = CLOSED;
	
	private int my_rightFrontPort = ZERO;
	private int my_rightRearPort = ONE;
	private int my_leftRearPort = TWO;
	private int my_leftFrontPort = THREE;
	private int my_controllerPort = ZERO;
	
	public Drive() {

	}

	/**
	 * 
	 * @param rightFront
	 *            The RoboRIO port of the front right motor
	 * @param rightRear
	 *            The RoboRIO port of the rear right motor
	 * @param leftRear
	 *            The RoboRIO port of the rear left motor
	 * @param leftFront
	 *            The RoboRIO port of the front left motor
	 */
	public Drive(int rightFront, int rightRear, int leftRear, int leftFront) {

		my_rightFrontPort = rightFront;
		my_rightRearPort = rightRear;
		my_leftRearPort = leftRear;
		my_leftFrontPort = leftFront;
		
	}
	
	/**
	 * 
	 * @param controllerPort The USB port of the XBox Controller
	 */
	public Drive(int controllerPort) {
		
		my_controllerPort = controllerPort;
		
	}
	
	/**
	 * 
	 * @param rightFront
	 *            The RoboRIO port of the front right motor
	 * @param rightRear
	 *            The RoboRIO port of the rear right motor
	 * @param leftRear
	 *            The RoboRIO port of the rear left motor
	 * @param leftFront
	 *            The RoboRIO port of the front left motor
	 * @param controllerPort
	 *			  The USB port of the XBox Controller           
	 */
	public Drive(int rightFront, int rightRear, int leftRear, int leftFront, int controllerPort) {

		my_rightFrontPort = rightFront;
		my_rightRearPort = rightRear;
		my_leftRearPort = leftRear;
		my_leftFrontPort = leftFront;
		my_controllerPort = controllerPort;
		
	}
	

	private final XboxController XBOX = new XboxController(my_controllerPort);

	private final Victor RIGHT_FRONT = new Victor(my_rightFrontPort);
	private final Victor RIGHT_REAR = new Victor(my_rightRearPort);
	private final Victor LEFT_REAR = new Victor(my_leftRearPort);
	private final Victor LEFT_FRONT = new Victor(my_leftFrontPort);

	private final Victor DOOR = new Victor(SIX);
	private final Victor BALL_INTAKE = new Victor(FIVE);
	private final Victor BALL_INTAKE_TWO = new Victor(FOUR);

	/**
	 * Sets all four drive motors to the forward/backward axis of the XBox
	 * controller
	 */
	public void tankDrive() {
		RIGHT_FRONT.set(XBOX.getY(Hand.kRight));
		RIGHT_REAR.set(XBOX.getY(Hand.kRight));
		LEFT_REAR.set(-XBOX.getY(Hand.kLeft));
		LEFT_FRONT.set(-XBOX.getY(Hand.kLeft));
	}

	/**
	 * Sets all four drive motors to a fraction of the forward/backward axis of
	 * the XBox controller
	 * 
	 * @param throttleAmount
	 *            The amount by which to slow the drive motors
	 */
	public void tankDrive(double throttleAmount) {
		RIGHT_FRONT.set(XBOX.getY(Hand.kRight) / throttleAmount);
		RIGHT_REAR.set(XBOX.getY(Hand.kRight) / throttleAmount);
		LEFT_REAR.set(-XBOX.getY(Hand.kLeft) / throttleAmount);
		LEFT_FRONT.set(-XBOX.getY(Hand.kLeft) / throttleAmount);
	}

	/**
	 * Sets all four drive motors to the forward/backward axis of the XBox
	 * controller, and sets the ball intake to the inverse of the left trigger,
	 * and the ball output to the right bumper.
	 */
	public void tankDriveBallSystem() {

		RIGHT_FRONT.set(XBOX.getY(Hand.kRight));
		RIGHT_REAR.set(XBOX.getY(Hand.kRight));
		LEFT_REAR.set(-XBOX.getY(Hand.kLeft));
		LEFT_FRONT.set(-XBOX.getY(Hand.kLeft));

		BALL_INTAKE.set(-XBOX.getTriggerAxis(Hand.kLeft));
		BALL_INTAKE_TWO.set(-XBOX.getTriggerAxis(Hand.kLeft));

		if (XBOX.getXButton() == true) {

			BALL_INTAKE.set(ONE);
			BALL_INTAKE_TWO.set(ONE);
			Timer.delay(ONE);
			BALL_INTAKE.set(ZERO);
			BALL_INTAKE_TWO.set(ZERO);

		}

		if (XBOX.getBumper(Hand.kRight) == true && door == CLOSED) {
			DOOR.set(OPEN_SPEED);
			Timer.delay(OPEN_TIMER);
			DOOR.set(ZERO);
			door = OPEN;
		}

		if (XBOX.getBumper(Hand.kLeft) == true && door == OPEN) {
			DOOR.set(CLOSE_SPEED);
			Timer.delay(CLOSE_TIMER);
			DOOR.set(ZERO);
			door = CLOSED;
		}

	}

	/**
	 * Sets all four drive motors to a fraction of the forward/backward axis of
	 * the XBox controller, and sets the ball intake to the inverse of the left
	 * trigger, and the ball output to the right bumper.
	 * 
	 * @param throttleAmount
	 *            The amount by which to slow the drive motors
	 */
	public void tankDriveBallSystem(double throttleAmount) {

		RIGHT_FRONT.set(XBOX.getY(Hand.kRight) / throttleAmount);
		RIGHT_REAR.set(XBOX.getY(Hand.kRight) / throttleAmount);
		LEFT_REAR.set(-XBOX.getY(Hand.kLeft) / throttleAmount);
		LEFT_FRONT.set(-XBOX.getY(Hand.kLeft) / throttleAmount);

		BALL_INTAKE.set(-XBOX.getTriggerAxis(Hand.kLeft));
		BALL_INTAKE_TWO.set(-XBOX.getTriggerAxis(Hand.kLeft));

		if (XBOX.getXButton() == true) {

			BALL_INTAKE.set(ONE);
			BALL_INTAKE_TWO.set(ONE);
			Timer.delay(ONE);
			BALL_INTAKE.set(ZERO);
			BALL_INTAKE_TWO.set(ZERO);

		}

		if (XBOX.getBumper(Hand.kRight) == true && door == CLOSED) {
			DOOR.set(OPEN_SPEED);
			Timer.delay(OPEN_TIMER);
			DOOR.set(ZERO);
			door = OPEN;
		}

		if (XBOX.getBumper(Hand.kLeft) == true && door == OPEN) {
			DOOR.set(CLOSE_SPEED);
			Timer.delay(CLOSE_TIMER);
			DOOR.set(ZERO);
			door = CLOSED;
		}

	}

	/**
	 * Reverse of Tank Drive (drives backward instead of forwards)
	 */
	public void reverseTankDriveBallSystem() {

		RIGHT_FRONT.set(-XBOX.getY(Hand.kLeft));
		RIGHT_REAR.set(-XBOX.getY(Hand.kLeft));
		LEFT_REAR.set(XBOX.getY(Hand.kRight));
		LEFT_FRONT.set(XBOX.getY(Hand.kRight));

		BALL_INTAKE.set(-XBOX.getTriggerAxis(Hand.kLeft));
		BALL_INTAKE_TWO.set(-XBOX.getTriggerAxis(Hand.kLeft));

		if (XBOX.getXButton() == true) {

			BALL_INTAKE.set(ONE);
			BALL_INTAKE_TWO.set(ONE);
			Timer.delay(ONE);
			BALL_INTAKE.set(ZERO);
			BALL_INTAKE_TWO.set(ZERO);

		}

		if (XBOX.getBumper(Hand.kRight) == true && door == CLOSED) {
			DOOR.set(OPEN_SPEED);
			Timer.delay(OPEN_TIMER);
			DOOR.set(ZERO);
			door = OPEN;
		}

		if (XBOX.getBumper(Hand.kLeft) == true && door == OPEN) {
			DOOR.set(CLOSE_SPEED);
			Timer.delay(CLOSE_TIMER);
			DOOR.set(ZERO);
			door = CLOSED;
		}

	}

	/**
	 * Reverse of Tank Drive (drives backward instead of forwards)
	 * 
	 * @param throttleAmount
	 *            The amount by which to slow the motors
	 */
	public void reverseTankDriveBallSystem(double throttleAmount) {

		RIGHT_FRONT.set(-XBOX.getY(Hand.kLeft) / throttleAmount);
		RIGHT_REAR.set(-XBOX.getY(Hand.kLeft) / throttleAmount);
		LEFT_REAR.set(XBOX.getY(Hand.kRight) / throttleAmount);
		LEFT_FRONT.set(XBOX.getY(Hand.kRight) / throttleAmount);

		BALL_INTAKE.set(-XBOX.getTriggerAxis(Hand.kLeft));
		BALL_INTAKE_TWO.set(-XBOX.getTriggerAxis(Hand.kLeft));

		if (XBOX.getXButton() == true) {

			BALL_INTAKE.set(ONE);
			BALL_INTAKE_TWO.set(ONE);
			Timer.delay(ONE);
			BALL_INTAKE.set(ZERO);
			BALL_INTAKE_TWO.set(ZERO);

		}

		if (XBOX.getBumper(Hand.kRight) == true && door == CLOSED) {
			DOOR.set(OPEN_SPEED);
			Timer.delay(OPEN_TIMER);
			DOOR.set(ZERO);
			door = OPEN;
		}

		if (XBOX.getBumper(Hand.kLeft) == true && door == OPEN) {
			DOOR.set(CLOSE_SPEED);
			Timer.delay(CLOSE_TIMER);
			DOOR.set(ZERO);
			door = CLOSED;
		}

	}

	/**
	 * DANCE!
	 */
	public void dance() {
		
			LEFT_REAR.set(DANCE_SPEED);
			LEFT_FRONT.set(DANCE_SPEED);
			RIGHT_REAR.set(DANCE_SPEED);
			RIGHT_FRONT.set(DANCE_SPEED);
			Timer.delay(DANCE_TIMER);
			LEFT_REAR.set(-DANCE_SPEED);
			LEFT_FRONT.set(-DANCE_SPEED);
			RIGHT_REAR.set(-DANCE_SPEED);
			RIGHT_FRONT.set(-DANCE_SPEED);
			Timer.delay(DANCE_TIMER);
			LEFT_REAR.set(ZERO);
			LEFT_FRONT.set(ZERO);
			RIGHT_REAR.set(ZERO);
			RIGHT_FRONT.set(ZERO);
		
	}
}
	/**
	 * Chooses which autonomous to run
	 * 
	 * @param autoSelected
	 *            The string which determines the switch case
	 */
	/*
	public void autoChooser(String autoSelected) {

		switch (autoSelected) {
		case gearDropAuto:
			// Drives backward at half speed for 3 seconds. Requires middle
			// position. 3ZERO points.
			LEFT_REAR.set(0.3);
			LEFT_FRONT.set(0.3);
			RIGHT_REAR.set(0.3);
			RIGHT_FRONT.set(0.3);
			Timer.delay(THREE);
			LEFT_REAR.set(ZERO);
			LEFT_FRONT.set(ZERO);
			RIGHT_REAR.set(ZERO);
			RIGHT_FRONT.set(ZERO);
			break;
		case ballDropAuto:
			// Turns left for 3 seconds, then dumps the balls and resets.
			// Requires boiler-side position. Up to 3 points.
			LEFT_FRONT.set(-QUARTER);
			LEFT_REAR.set(-QUARTER);
			RIGHT_FRONT.set(ONE);
			RIGHT_REAR.set(ONE);
			Timer.delay(THREE);
			LEFT_FRONT.set(ZERO);
			LEFT_REAR.set(ZERO);
			RIGHT_FRONT.set(ZERO);
			RIGHT_REAR.set(ZERO);
			DOOR.set(ONE);
			Timer.delay(THREE);
			DOOR.set(ZERO);
			break;
		case defaultAuto:
		default:
			// Drives forward at half speed for 3 seconds. 5 points.
			LEFT_REAR.set(0.3);
			LEFT_FRONT.set(0.3);
			RIGHT_REAR.set(-(0.3));
			RIGHT_FRONT.set(-(0.3));
			Timer.delay(THREE);
			LEFT_REAR.set(ZERO);
			LEFT_FRONT.set(ZERO);
			RIGHT_REAR.set(ZERO);
			RIGHT_FRONT.set(ZERO);
		}

	}*/
