package frc.team4348.robot.ThotBot;

import edu.wpi.first.wpilibj.Timer;

/**
 * This is the main class for the Autonomous Period of ThotBot.
 * It handles how the Auto will function while the robot is in {@link Robot.autonomousPeriodic()}
 * @author Lead Programmer of 4348
 * @since 2018 Build Season
 * @version v0.5
 * @deprecated this isnt tested and has never worked, so please dont use it
 */
@Deprecated
public final class Auto{
    Robot r;
    
    public Auto(Robot r){
        this.r = r;
    }
    
    /**
     * begins the autonomous period
     * place this in {@link Robot.autonomousPeriodic()}
     */
    public void start(){
        r.TD.drive(0.25,0.25);
        Timer.delay(10);
        r.TD.drive(0, 0);
        Timer.delay(5);
    }
}