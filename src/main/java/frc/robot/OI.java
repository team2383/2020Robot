package frc.robot;

import frc.robot.Robot;
import frc.robot.Grain;
import frc.robot.Procedure;
import frc.robot.TC;
import edu.wpi.first.wpilibj.buttons.*;
import frc.robot.ninjaLib.Gamepad;
import frc.robot.ninjaLib.Gamepad2;
import frc.robot.Field;
import frc.robot.auto.autocommands.*;
import frc.robot.Subsystems.Feeder;

public class OI {

    Gamepad driver = new Gamepad(0);
    public Gamepad2 operator = new Gamepad2(1);

    public OI(){
        
    //these are active listeners
    //make procedures and conditions

    // Button intervalFeed = new JoystickButton(driver, Gamepad.BUTTON_B);
    // intervalFeed.whenPressed(new SetFeederInterval());

    // DRIVE
    Procedure drive = () ->{HAL.drive.arcade((driver.getRightX()*.8),(-driver.getLeftY()*.8));};
    TC noTC = ()->{return false;}; 
    Grain e = new Grain(drive,noTC,drive);
    Robot.mill.addGrain(e);


    // TURRET
    Procedure turretmanual = () -> {HAL.turret.move(.5*(driver.getLeftTrigger()-driver.getRightTrigger()));};
    Grain t = new Grain(turretmanual,noTC,turretmanual);
    Robot.mill.addGrain(t);


    // TURRET VISION
    Procedure zeroTurret = () -> {HAL.turret.zeroTurret();};
    Grain GzeroTurret = new Grain (zeroTurret, noTC, zeroTurret);
    Robot.mill.addGrain(GzeroTurret);


    // DEPLOYMENT
    Procedure deployment = () ->{HAL.deployment.setSpeed(operator.getRightY());};
    Grain gDeployment = new Grain(deployment, noTC, deployment);
    Robot.mill.addGrain(gDeployment);
    }

    

    public void listener(){
        // FEEDER
        Procedure feed = () ->{HAL.feeder.feed();};
        Procedure stopFeed = () -> {HAL.feeder.off();};
        TC releasedRBump = () -> {return !(driver.getButtonStateRightBumper());};
        Procedure unfeed = () -> {HAL.feeder.unfeed();};
        TC releasedLBump = ()->{return !(driver.getButtonStateLeftBumper());};
        Grain gFeed = new Grain(feed, releasedRBump, stopFeed);
        Grain gUnfeed = new Grain(unfeed, releasedLBump, stopFeed);

        Procedure intervalFeeder = () -> {HAL.feeder.interval_feed(0.5);};
        Procedure intervalFeeder2 = () -> {HAL.feeder.interval_feed_JA(0.5, .75);};
        TC releasedB = ()->{return !(driver.getButtonStateB());};
        Grain gIntervalFeeder = new Grain(intervalFeeder, releasedB, stopFeed);


        // CONVEYOR
        Procedure conveyor1 = () -> {HAL.conveyor.pull();};
        Procedure conveyorOff = () -> {HAL.conveyor.off();};
        Procedure conveyor1out = () -> {HAL.conveyor.out();};
        Procedure xConveyor = () -> {HAL.conveyor.fire();};
        TC releasedLBump2 = ()->{return !(driver.getButtonStateLeftBumper());};
        TC releasedX = () -> {return !(driver.getButtonStateX());};
        Grain gConvey = new Grain(conveyor1, releasedRBump, conveyorOff);
        Grain conveyout = new Grain(conveyor1out, releasedLBump2,conveyorOff);
        Grain xConveyorFire = new Grain(xConveyor, releasedX, conveyorOff);
       

        //TRIGGER
        Procedure trig = () -> {HAL.triggered.spinMedium();};
        Procedure trigno = () -> {HAL.triggered.off();};
        
        Grain xTriggerFire = new Grain(trig, releasedX, trigno);


        //FIRE
        Procedure xFeeder = () -> {HAL.feeder.fire();};
        Grain xFeederFire = new Grain(xFeeder, releasedX, stopFeed);


        // SHOOTER
        Procedure shooter = () -> {HAL.shoot.Run();};
        Procedure stop = () -> {HAL.shoot.stop();};
        TC releasedA = ()->{return !(driver.getButtonStateA());};
        Grain shoot = new Grain(shooter, releasedA, stop);


        // HOOD
        Procedure hoodmanual = () -> {HAL.hood.slowMoveUP();};
        TC releasedDUP = () ->{return !driver.getDPadUp();};
        Procedure hoodoff = () -> {HAL.hood.off();};

        Procedure hoodmanualdown = () -> {HAL.hood.slowMoveDown();};
        TC releasedDDown = () ->{return !driver.getDPadDown();};
        Grain hoodUp = new Grain (hoodmanual, releasedDUP, hoodoff);
        Grain hoodDown = new Grain (hoodmanualdown, releasedDDown, hoodoff);


        //LIMELIGHT TURRET
        Procedure limelightT = () -> {HAL.turret.limeTOn(.5*(driver.getLeftTrigger()-driver.getRightTrigger()));};
        TC releaseStart = () -> {return !driver.getRawButtonPressed(8);};
        TC releaseBack = () -> {return !driver.getRawButtonPressed(7);};
        Procedure limeoffT = () -> {HAL.turret.limeTOff(2);};
        Grain GlimelightTOn = new Grain (limelightT, releaseBack, limelightT);
        Grain GlimelightTOff = new Grain (limeoffT, releaseStart, limeoffT);
        

        //LIMELIGHT HOOD
        Procedure limelightH = () -> {HAL.hood.limeH();};
        Procedure limeoffH = () -> {HAL.hood.off();};
        Grain GlimelightHOn = new Grain (limelightH, releaseStart, limelightH);
        Grain GlimelightHOff = new Grain (limeoffH, releaseBack, limeoffH);


        //SHIFTER
        Procedure armed = () -> {HAL.arm.activate();};
        TC releasedB2 = () -> {return !operator.getButtonStateB();};
        Procedure disarm = () -> {HAL.arm.prepClimb();};
        Grain arming = new Grain (armed, releasedB2, disarm);


        //SELFCLIMB
        Procedure selfclimb = () -> {HAL.selfClimb.prepClimb();};
        TC releasedRightBumper2 = () -> {return (HAL.selfClimb.run);};
        Procedure selfcease = () -> {HAL.selfClimb.stopClimb();};
        Grain self = new Grain (selfclimb, releasedRightBumper2, selfcease);


        //BUDDYCLIMB
        Procedure buddyclimb = () -> {HAL.buddyClimb.prepClimb();};
        TC releasedLeftBumper2 = () -> {return (HAL.buddyClimb.run);};
        Procedure buddycease = () -> {HAL.buddyClimb.stopClimb();};
        Grain buddy = new Grain (buddyclimb, releasedLeftBumper2, buddycease); 
        
        Procedure limealign = () -> {HAL.drive.limeAlign();};
        Procedure driveoff = () -> {HAL.drive.arcade(0, 0);};
        Grain Glimealign = new Grain (limealign, releasedB, driveoff); 

        Procedure limeapproach = () -> {HAL.drive.limeApache();};
        TC releasedY = () -> {return (!driver.getButtonStateY());};
        Grain Glimeapproach = new Grain (limeapproach, releasedY, driveoff); 

        //button groups + initializing conditionals
        ///////////////////////////////////////
        //            DRIVER                 //
        ///////////////////////////////////////

        if(driver.getButtonStateA()){
            Robot.mill.addGrain(shoot);
        }

        if(driver.getButtonStateB()){
            Robot.mill.addGrain(Glimealign);
           // Robot.mill.addGrain(gIntervalFeeder);
           //Robot.mill.addGrain(gIntervalFeeder);
        }

        
        if(driver.getButtonStateX()){
            Robot.mill.addGrain(xFeederFire);
            Robot.mill.addGrain(xConveyorFire);
            Robot.mill.addGrain(xTriggerFire);
        }

        if(driver.getButtonStateY()){
            Robot.mill.addGrain(Glimeapproach);
        }

        
        if(driver.getDPadUp()){
            Robot.mill.addGrain(hoodUp);
        }

        
        if(driver.getDPadDown()){
            Robot.mill.addGrain(hoodDown);
        }

        
        if(driver.getButtonStateLeftBumper()){
            Robot.mill.addGrain(gUnfeed);
            Robot.mill.addGrain(conveyout);
        }


        if(driver.getButtonStateRightBumper()){
            Field.operatorCool = false;
            Robot.mill.addGrain(gConvey);
            Robot.mill.addGrain(gFeed);
        }
        
  
        if(driver.getRawButton(Gamepad.BUTTON_START)){
            Field.limelightOn = true;
            Robot.mill.addGrain(GlimelightHOn);
            Robot.mill.addGrain(GlimelightTOn); 
        }

        if((driver.getRawButton(Gamepad.BUTTON_BACK))){
            Field.limelightOn = false;
            Robot.mill.addGrain(GlimelightHOff);
        }



        ///////////////////////////////////////
        //            OPERATOR    
                   //
        ///////////////////////////////////////

        if(operator.getDPadUp()){
            
        } 

        if(operator.getDPadDown()){
           
        } 

        if(operator.getButtonStateLeftBumper()){
            Field.operatorCool = true;
        }

        if(operator.getButtonStateRightBumper()){
          //  Robot.mill.addGrain(gIntervalFeeder);
        }
        
        if(operator.getRawButtonPressed(Gamepad2.BUTTON_X)){
            Robot.mill.addGrain(buddy);
        }

        if(operator.getRawButtonPressed(Gamepad2.BUTTON_Y)){
            Robot.mill.addGrain(self);
        }

    
    }
}