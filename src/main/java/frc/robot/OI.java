/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import frc.robot.Robot;
import frc.robot.Loop;
import frc.robot.Grain;
import frc.robot.Procedure;
import frc.robot.TC;
import frc.robot.Subsystems.Drivetrain;
import frc.robot.Subsystems.Trigger;
import frc.robot.ninjaLib.Gamepad;
import java.util.function.DoubleSupplier;
import frc.robot.Pathfinding;
import edu.wpi.first.vision.VisionRunner.Listener;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


public class OI {

    Gamepad driver = new Gamepad(0);
    Gamepad driver2 = new Gamepad(1);

    public OI(){
        //these are active listeners
        //make procedures and conditions
        TC noTC = ()->{return false;};
        Procedure drive = () ->{HAL.drivetrain.arcade((driver.getRightX()*.6),(-driver.getLeftY()*.6));};
        Procedure turretmanual = () -> {HAL.turret.move((driver.getLeftTrigger()*.5)-driver.getRightTrigger()*0.5);};

        Grain e = new Grain(drive,noTC,drive);
        Robot.mill.addGrain(e);

        Grain t = new Grain(turretmanual,noTC,turretmanual);
        Robot.mill.addGrain(t);
    }

    public void periodic(){
        
    }
    public void listener(){
        // FEED IN
        Procedure feed = () ->{HAL.feeder.feed();};
        Procedure stopFeed = () -> {HAL.feeder.off();};
        TC releasedRBump = () -> {return !(driver.getButtonStateRightBumper());};
        Grain GFeed = new Grain(feed, releasedRBump, stopFeed);
    
        // FEED OUT
        Procedure unfeed = () -> {HAL.feeder.unfeed();};
        TC releasedLBump = ()->{return !(driver.getButtonStateLeftBumper());};
        Grain GUnfeed = new Grain(unfeed, releasedLBump, stopFeed);

        // LAUNCH
        Procedure shooter = () -> {HAL.shoot.manualorlime();};
        Procedure stop = () -> {HAL.shoot.stop();};
        TC releasedA = ()->{return !(driver.getButtonStateA());};
        Grain launch = new Grain(shooter, releasedA,stop);

        // CONVEYOR
        Procedure conveyor1 = () -> {HAL.conveyor.pull();};
        Procedure off = () -> {HAL.conveyor.off();};
        TC releasedRBump2 = ()->{return !(driver.getButtonStateRightBumper());};
        Grain conveyin = new Grain(conveyor1, releasedRBump2,off);
        
        Procedure conveyor1out = () -> {HAL.conveyor.out();};
        TC releasedLBump2 = ()->{return !(driver.getButtonStateLeftBumper());};
        Grain conveyout = new Grain(conveyor1out, releasedLBump2,off);

        //TRIGGER
        Procedure trigger = () -> {HAL.triggered.spinHigh();};
        Procedure trigOff = () -> {HAL.triggered.off();};
        TC releasedBBump = () ->{return !(driver.getButtonStateB());};
        Grain triglow = new Grain (trigger, releasedBBump, trigOff);

        Procedure arctrig = () -> {HAL.triggered.spinOut();};
        TC releasedXBump = () ->{return !driver.getButtonStateX();};
        Grain trigout = new Grain (arctrig, releasedXBump, trigOff);

        // HOOD
        Procedure hoodmanual = () -> {HAL.hood.slowMoveUP();};
        TC releasedDUP = () ->{return !driver.getDPadUp();};
        Procedure hoodoff = () -> {HAL.hood.off();};
        Grain hoodUp = new Grain (hoodmanual, releasedDUP, hoodoff);

        Procedure hoodmanualdown = () -> {HAL.hood.slowMoveDown();};
        TC releasedDDown = () ->{return !driver.getDPadDown();};
        Grain hoodDown = new Grain (hoodmanualdown, releasedDDown, hoodoff);
        
        //SLIMELIGHT
        Procedure slimeon = () -> {HAL.turret.limeT(1);};
        TC releasedStartButton = () ->{return driver.getRawButton(Gamepad.BUTTON_START);};
        Procedure notmyslime = () -> {HAL.turret.off();};
        Grain Gslimelight = new Grain (slimeon, releasedStartButton, notmyslime);

        //Shifter
        Procedure shifton = () -> {HAL.selfClimb.prepClimb();};
        TC releasedYButton = () -> {return !driver.getButtonStateY();};
        Grain shiftNow = new Grain (shifton, releasedYButton, shifton);

        //Wheelie
        Procedure wheelon = () -> {HAL.wheelofFortune.wheelie();};
        TC releasedBackButton = () -> {return driver.getRawButton(Gamepad.BUTTON_BACK);};
        Procedure wheelno = () -> {HAL.wheelofFortune.off();};
        Grain wheelNow = new Grain (wheelon, releasedBackButton, wheelno);
        
        //button groups
        if(driver.getButtonStateA()){
            Robot.mill.addGrain(launch);
        }
        
        if(driver.getButtonStateB()){
           Robot.mill.addGrain(triglow);
        }

        if(driver.getButtonStateX()){
            Robot.mill.addGrain(trigout);
        }

        if(driver.getDPadUp()){
            Robot.mill.addGrain(hoodUp);
        }

        if(driver.getDPadDown()){
            Robot.mill.addGrain(hoodDown);
        }

        if(driver.getButtonStateLeftBumper()){
            Robot.mill.addGrain(GUnfeed);
            Robot.mill.addGrain(conveyout);
        }

        if(driver.getButtonStateRightBumper()){
            Robot.mill.addGrain(GFeed); 
            Robot.mill.addGrain(conveyin);
        }

        if(driver.getRawButton(Gamepad.BUTTON_START)){
            Robot.mill.addGrain(Gslimelight); 
        }

        if(driver.getButtonStateY()){
            Robot.mill.addGrain(shiftNow);
        }
        if(driver.getRawButton(Gamepad.BUTTON_BACK)){
            Robot.mill.addGrain(wheelNow);
        }
        
    }
   

}
