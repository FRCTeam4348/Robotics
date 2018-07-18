package frc.team4348.robot.ThotBot;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class Robot extends IterativeRobot {
    //drive variables
    int leftDrive = 0;
    int rightDrive = 1;

    //elevator variables
    int cubeLiftFrontLeft = 3;
    int cubeLiftBackLeft = 2;
    int cubeLiftFrontRight = 5;
    int cubeLiftBackRight = 4;

    //elevator wheel intake variables
    int cubeWheelLeft = 8;
    int cubeWheelRight = 9;

    //solenoid variable
    int pistonArm = 0;

    //custome class objects
    Pneumatics p = new Pneumatics(pistonArm);
    ThotDrive TD = new ThotDrive(leftDrive, rightDrive, 2);
    Elevator lift = new Elevator(cubeLiftFrontLeft, cubeLiftFrontRight, cubeLiftBackLeft, cubeLiftBackRight, cubeWheelLeft, cubeWheelRight);
    XboxController xbox = new XboxController(0);
    //Auto auto = new Auto(this);

    @Override
    public void robotInit() { }

    @Override
    public void disabledInit() { }

    @Override
    public void autonomousInit() { }

    @Override
    public void teleopInit() { }

    @Override
    public void testInit() { }

    @Override
    public void disabledPeriodic() { }
    
    @Override
    public void autonomousPeriodic() { 
        //auto.start();
    }

    @Override
    public void teleopPeriodic() { 
        
        //lol this is so ugly dont do this pls
        //find a cleaner way of doing this, maybe do it in the Pneumatic class
        new Thread(new Runnable(){
            @Override
            public void run(){
                p.compressorSwitch(xbox.getAButton());
            }
        }).start();

        //lol this is so ugly dont do this pls
        //find a cleaner way of doing this, maybe do it in the Pneumatic class
        new Thread(new Runnable(){
            @Override
            public void run(){
                p.pistonSwitch(xbox.getYButton());
            }
        }).start();

        TD.drive(xbox.getY(Hand.kLeft), xbox.getY(Hand.kRight));
        lift.LiftUp(xbox.getBumper(Hand.kRight));
        lift.LiftDown(xbox.getBumper(Hand.kLeft));
        lift.runWheels(xbox.getTriggerAxis(Hand.kLeft), Side.kLeft, true);
        lift.runWheels(xbox.getTriggerAxis(Hand.kRight), Side.kRight, true);
    }

    @Override
    public void testPeriodic() { }
}