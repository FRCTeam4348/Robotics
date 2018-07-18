package frc.team4348.robot.ThotBot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

/**
 * This is the main class for all pneumatics on ThotBot.
 * It handles how the pneumatics will function while the robot is running.
 * @author Lead Programmer of 4348
 * @since 2018 Build Season
 * @version v1.0
 */
public class Pneumatics{
    Compressor c;
    Solenoid arm;
    boolean cState, armState;

    /**
     * The contructor of all pneumatic componets
     * @param port = the port of the solenoid is plugged in
     */
    public Pneumatics(int port){
        c = new Compressor();
        arm = new Solenoid(port);
        c.stop();
        arm.set(false);
        cState = false;
        armState = false;
    }

    /**
     * This enables the compressor, which starts the compressor.
     */
    public void compressorOn(){
        c.start();
        cState = true;
    }

    /**
     * this disables the compressor, which stops the compressor.
     */
    public void compressorOff(){
        c.stop();
        cState = false;
    }

    /**
     * this enables the piston, which switches on the solenoid.
     */
    public void pistonOn(){
        arm.set(true);
        armState = true;
    }

    /**
     * this disables the piston, which switches off the solenoid.
     */
    public void pistonOff(){
        arm.set(false);
        armState = false;
    }

    /**
     * this toggles the state of the piston.
     * NOTE: 
     * this has a delay and needs to be put in its own thread
     * @param pressed = toggles state if true, else does nothing
     * @since v1.0
     */
    public void pistonSwitch(boolean pressed){
        if(pressed){
            arm.set(!arm.get());
            armState = !armState;
            Timer.delay(0.02);
        }
    }

    /**
     * this toggles the state of the compressor.
     * NOTE: 
     * this has a delay and needs to be put in its own thread
     * @param pressed = toggles state if true, else does nothing
     * @since v1.0
     */
    public void compressorSwitch(boolean pressed){
        if(pressed){
            if(c.enabled()){
                c.stop();
                cState = false;
            }
            else{
                c.start();
                cState = true;
            }
            Timer.delay(0.02);
        }
    }
}