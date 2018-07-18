package frc.team4348.robot.ThotBot;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Spark;

/**
 * This is the main class for the DriveTrain on ThotBot.
 * It handles how the Drive will function while the robot is running.
 * @author Lead Programmer of 4348
 * @since 2018 Build Season
 * @version v1.0
 */
public class ThotDrive{
    Spark left;
    Jaguar right;
    double throttle;

    /**
     * the constructor for the DriveTrain of ThotBot
     * @param left = the left motor PWM cable input
     * @param right = the right motor PWM cable input
     * @param throttle = the factor you want to slow down the robot. range of +1.0 to +6.0
     * @since v1.0
     * 
     */
    public ThotDrive(int left, int right, double throttle){
        this.left = new Spark(left);
        this.right = new Jaguar(right);
        this.throttle = throttle;
    }

    /**
     * runs the drive system
     * @param left = how fast you want to run the left side. ranges from -1 to +1
     * @param right = how fast you want to run the right side. ranges from -1 to +1
     * @since v1.0
     */
    public void drive(double left, double right){
        this.left.set(left / throttle);
        this.right.set(right / throttle);
    }
}