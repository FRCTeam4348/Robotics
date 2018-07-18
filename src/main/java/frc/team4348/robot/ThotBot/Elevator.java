package frc.team4348.robot.ThotBot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Victor;

/**
 * This is the main class for the elevator of ThotBot.
 * It handles how the elevator manipulates on the robot.
 * @author Lead Programmer of 4348
 * @since 2018 Build Season
 * @version v1.0
 */
public class Elevator{
    Spark frontLeft, frontRight, backLeft, backRight;
    Victor leftWheel, rightWheel;

    /**
     * the constructor for the elevator of ThotBot
     * @param frontLeft = the front left motor PWM cable input  
     * @param frontRight = the front right motor PWM cable input  
     * @param backLeft = the back left motor PWM cable input  
     * @param backRight = the back right motor PWM cable input  
     *  
     * @param leftWheel = the left wheel motor PWM cable input  
     * @param rightWheel = the right wheel motor PWM cable input  
     * 
     * 
     */
    public Elevator(int frontLeft, int frontRight, int backLeft, int backRight, int leftWheel, int rightWheel){
        this.frontLeft = new Spark(frontLeft);        
        this.frontRight = new Spark(frontRight);
        this.backLeft = new Spark(backLeft);
        this.backRight = new Spark(backRight);

        this.leftWheel = new Victor(leftWheel);
        this.rightWheel = new Victor(rightWheel);
    }

    /**
     * this sets all elevator motors to zero to stop it from running.
     * @since v1.0
     */
    private void stopLift(){
        frontLeft.set(0);
        frontRight.set(0);
        backLeft.set(0);
        backRight.set(0);
    }

    /**
     * This rasises the elevator and runs the motors
     * @param up = runs the motors if true, else it'll run {@link #stopLift()}
     * @since v1.0
     */
    public void LiftUp(boolean up){
        if(up){
            //frontLeft.set(1);
            //frontRight.set(-1);
            //backLeft.set(1);
            //backRight.set(-1);
        }
        else{
            stopLift();
        }
    }

    /**
     * This lowers the elevator and runs the motors
     * @param down = runs the motors if true, else it'll run {@link #stopLift()}
     * @since v1.0
     */
    public void LiftDown(boolean down){
        if(down){
            //frontLeft.set(-1);
            //frontRight.set(1);
            //backLeft.set(-1);
            //backRight.set(1);
        }
        else{
            stopLift();
        }
    }

    /**
     * This runs a wheel that is extended on the arm part of the elevator
     * @param speed = how fast to run the wheels ranges from -1 to +1
     * @param side = which side to run either kLeft or kRight
     * @deprecated doesnt work as of v0.5
     * @since v0.5
     */
    @Deprecated
    public void runWheels(double speed, Side side){
        switch(side){
            case kLeft:
                leftWheel.set(speed);
            break;

            case kRight:
                rightWheel.set(speed);
            break;
        }
    }

    /**
     * This runs a wheel that is extended on the arm part of the elevator
     * @param speed = how fast to run the wheels ranges from -1 to +1
     * @param side = which side to run either kLeft or kRight
     * @param state = runs the motors if true, else it'll stop the motor {@link #stopLift()}
     * @since v1.0
     */
    public void runWheels(double speed, Side side, boolean state){
        if(state){
            switch(side){
                case kLeft:
                    leftWheel.set(speed);
                    state = false;
                break;

                case kRight:
                    rightWheel.set(speed);
                    state = false;
                break;
            }
        }
        else{
            switch(side){
                case kLeft:
                    leftWheel.set(0);
                break;

                case kRight:
                    rightWheel.set(0);
                break;
            }
        }
    }
}