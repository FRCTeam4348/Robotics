package frc.team4348.robot.KierstynUpdated;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

public class RobotExtras{
    Victor door, ballIntakeTop, ballIntakeBottom;
    boolean doorState;


    public RobotExtras(int door, int ballIntakeTop, int ballIntakeBottom){
        this.door = new Victor(door);
        this.ballIntakeBottom = new Victor(ballIntakeBottom);
        this.ballIntakeTop = new Victor(ballIntakeTop);
        doorState = false;
    }
    

    public void doorTest(double speed,boolean swtch){
        if(swtch){
            door.set(speed);
        }
        else{
            door.set(-speed);
        }


    }




    public void openDoor(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                door.set(-0.5); 
                Timer.delay(1); 
                door.set(0);
            }
        }).start();

    }

    public void closeDoor(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                door.set(0.5);
                Timer.delay(1); 
                door.set(0);
            }
        }).start();

    }

    public void toggleDoor(boolean state){
        if(state){
            if(doorState){
                closeDoor();
            }
            else{
                openDoor();
            }
        }
    }

    public void suckBalls(double speed){
        ballIntakeBottom.set(speed);
        ballIntakeTop.set(-speed);
    }

    public void dispenseBalls(boolean state){
        new Thread(new Runnable(){
            @Override
            public void run(){
                if(state){
                    ballIntakeBottom.set(-1);
                    ballIntakeTop.set(1);
                    Timer.delay(1);
                    ballIntakeBottom.set(0);
                    ballIntakeTop.set(0);
                }
            }
        }).start();
    }
}