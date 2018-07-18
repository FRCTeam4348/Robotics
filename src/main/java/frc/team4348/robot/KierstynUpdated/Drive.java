package frc.team4348.robot.KierstynUpdated;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Timer;

public class Drive{

    Victor frontLeft, frontRight, backLeft, backRight;
    boolean flip;
    double throttle;
    
    public Drive(int frontleft, int frontright, int backleft, int backright,double throttlespeed){
        frontLeft = new Victor(frontleft);
        frontRight = new Victor(frontright);
        backLeft = new Victor(backleft);
        backRight = new Victor(backright);
        throttle = throttlespeed;
        flip = false;
    }



    public void drive(double left, double right, boolean reverse){
        if(reverse){
            flip = !flip;
        }

        if(!flip){
            frontLeft.set(-left / throttle);
            backLeft.set(left / throttle);
            frontRight.set(-right / throttle);
            backRight.set(right / throttle);
        }
        else{
            frontLeft.set(left / throttle);
            backLeft.set(-left / throttle);
            frontRight.set(right / throttle);
            backRight.set(-right / throttle);
        }
    }

    public void dance(){

        frontLeft.set(0.5);
        backLeft.set(-0.5);
        frontRight.set(0.5);
        backRight.set(-0.5);
        Timer.delay(1);
        frontLeft.set(0.5);
        backLeft.set(-0.5);
        frontRight.set(0.5);
        backRight.set(-0.5);
        Timer.delay(1);
        frontLeft.set(0.5);
        backLeft.set(-0.5);
        frontRight.set(0.5);
        backRight.set(-0.5);
    }

}