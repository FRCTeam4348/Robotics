package frc.team4348.robot.KierstynUpdated;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Timer;
public class Data{
    XboxController xbox;

    public Data(XboxController x){
        xbox = x;
    }

    public void getControllerData(){
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
    }
}