package frc.team4348.robot.BaseBot;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Jaguar;


public class Robot extends IterativeRobot {


    Jaguar frontLeft = new Jaguar(2);
    Jaguar frontRight= new Jaguar(0);
    Jaguar backLeft = new Jaguar(3);
    Jaguar backRight = new Jaguar(1);

    XboxController xbox = new XboxController(0);

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
    public void autonomousPeriodic() { }

    @Override
    public void teleopPeriodic() { 
        frontLeft.set(-xbox.getY(Hand.kLeft));
        backLeft.set(-xbox.getY(Hand.kLeft));
        frontRight.set(xbox.getY(Hand.kRight));
        backRight.set(xbox.getY(Hand.kRight));


        



    }

    @Override
    public void testPeriodic() { }


}