package frc.robot;

import frc.robot.Robot;
import frc.robot.Grain;
import frc.robot.Procedure;
import frc.robot.TC;
import edu.wpi.first.wpilibj.buttons.*;
import frc.robot.ninjaLib.FollowTrajectory2;
import frc.robot.ninjaLib.Gamepad;
import frc.robot.ninjaLib.Gamepad2;
import frc.robot.ninjaLib.HelperCommand;
import frc.robot.ninjaLib.DelayFeed;
import frc.robot.Field;
import frc.robot.auto.autocommands.*;

public class OI {

    Gamepad driver = new Gamepad(0);
    public Gamepad2 operator = new Gamepad2(1);
    public Gamepad test = new Gamepad(2);
    // HAL.limelight.setPipeline(3);

    
    public OI(){
        
    //ACTIVE LISTENERS + SENDABLECOMMANDS

    Field.operatorCool = true;
    Field.limelightHeadache = false;    

    // DRIVE & OPERATOR DRIVE
    // if (Field.handJob){
    //     Procedure drive = () ->{HAL.drive.arcade(operator.getLeftY() * .9, operator.getRightY() *.9);};
    //     TC noTC = ()->{return false;};
    //     Grain e = new Grain(drive,noTC,drive);
    //     Robot.mill.addGrain(e);
    // }
    // else{
    //     Procedure drive = () ->{HAL.drive.arcade((driver.getRightX()*.90),(-driver.getLeftY()*.90));};
    //     TC noTC = ()->{return false;};
    //     Grain e = new Grain(drive,noTC,drive);
    //     Robot.mill.addGrain(e);
    // }

    Procedure drive = () ->{HAL.drive.drive(driver, operator);};
    TC noTC = ()->{return false;};
    Grain e = new Grain(drive,noTC,drive);
    Robot.mill.addGrain(e);

    TC handJobz = () -> {return Field.handJob;};
    // Grain e = new Grain(drive,noTC,drive);
    // Grain e2 = new Grain(operatorTank,noTC,operatorTank);
    // if (Field.handJob){
    //     Robot.mill.addGrain(e2);
    // }
    // else{
    //     Robot.mill.addGrain(e);
    // }
    // Robot.mill.addGrain(e);
    
    
    // SPOOL
    Procedure spoolL = () -> {HAL.spool.setL(operator.getLeftY()*-1);};
    Procedure spoolR = () -> {HAL.spool.setR(operator.getRightY()*-1);};

    Grain gSpoolL = new Grain(spoolL, noTC, spoolL);
    Grain gSpoolR = new Grain(spoolR, noTC, spoolR);
    Robot.mill.addGrain(gSpoolL);
    Robot.mill.addGrain(gSpoolR);

    // LIMELIGHT
    Procedure poopBrainetc = () ->{HAL.limelight.limelightOnOff();};
    Grain donepoop = new Grain(poopBrainetc, noTC, poopBrainetc);
    Robot.mill.addGrain(donepoop);

    //1 FOOT
    Button trajectory = new JoystickButton(operator, Gamepad2.BUTTON_A);
    trajectory.whenPressed(new HelperCommand(false));
    
    // DELAYED CHAMBER
    // Button delayfeed = new JoystickButton(driver, Gamepad.BUTTON_X);
    // delayfeed.whenPressed(new DelayFeed(0.5));


    // DEPLOYMENT
    // Procedure deployment = () ->{HAL.deployment.setSpeed(operator.getRightY(), operator.getLeftY());};
    // Procedure deploymentL = () ->{HAL.deployment.setSpeedL(operator.getLeftY());};
    // Procedure deploymentR = () ->{HAL.deployment.setSpeedR(operator.getRightY());};
    //Grain gDeploymentL = new Grain(deploymentL, noTC, deploymentL);
    // Grain gDeploymentR = new Grain(deploymentR, noTC, deploymentR);
    // Grain gDeployment = new Grain(deploymentL, noTC, deploymentL);
    // Robot.mill.addGrain(gDeployment);
    // Robot.mill.addGrain(gDeploymentR);
    // Robot.mill.addGrain(gDeploymentL);
    
    }


    public void listener(){
        TC releasedX = () -> {return !(driver.getButtonStateX());};
        TC releasedB = ()->{return !(driver.getButtonStateB());};
        TC releasedRBump = () -> {return !(driver.getButtonStateRightBumper());};
        TC releasedLBump = ()->{return !(driver.getButtonStateLeftBumper());};
        TC releasedLBump2 = ()->{return !(driver.getButtonStateLeftBumper());};
        TC releasedY = () -> {return (!driver.getButtonStateY());};
        TC releasedA = ()->{return !(driver.getButtonStateA());};

        TC releasedDUP = () ->{return !operator.getDPadUp();};
        TC releaseStart = () -> {return !driver.getRawButtonPressed(8);};
        TC releaseBack = () -> {return !driver.getRawButtonPressed(7);};
        TC releasedminus = () -> {return !operator.getRawButton(9);};
        TC releasedhome = () -> {return !operator.getRawButton(13);};
        TC dRightRelease = () -> {return !operator.getDPadRight();};
        TC releasedplus = () -> {return !operator.getRawButton(10);};

        // FEEDER
        Procedure feed = () ->{HAL.feeder.feed();};
        Procedure stopFeed = () -> {HAL.feeder.off();};
        Procedure unfeed = () -> {HAL.feeder.unfeed();};
        
        Grain gFeed = new Grain(feed, releasedRBump, stopFeed);
        Grain gUnfeed = new Grain(unfeed, releasedLBump, stopFeed);

        // Procedure intervalFeeder = () -> {HAL.feeder.interval_feed(0.5);};
        //Procedure delayFeeder = () -> {HAL.feeder.delay_feed(1.5);};
        // Grain gIntervalFeeder = new Grain(intervalFeeder, releasedRBump, stopFeed);
        //Grain gDelayFeeder = new Grain(delayFeeder, releasedX, stopFeed);


        // CONVEYOR
        Procedure conveyor1 = () -> {HAL.conveyor.pull();};
        Procedure conveyorOff = () -> {HAL.conveyor.off();};
        Procedure conveyor1out = () -> {HAL.conveyor.out();};
        Procedure xConveyor = () -> {HAL.conveyor.fire();};

        Grain gConvey = new Grain(conveyor1, releasedRBump, conveyorOff);
        Grain gconveyout = new Grain(conveyor1out, releasedLBump2,conveyorOff);
        Grain xConveyorFire = new Grain(xConveyor, releasedX, conveyorOff);
       

        //TRIGGER
        Procedure trig = () -> {HAL.triggered.spinMedium();};
        Procedure trigno = () -> {HAL.triggered.off();};
        Procedure triggerOut = () -> {HAL.triggered.out();};
        Procedure triggerOutSlow = () -> {HAL.triggered.outSlow();};
        Procedure intervalTrigger = () -> {HAL.triggered.interval_trigger(0.5);};
        
        Grain xTriggerFire = new Grain(trig, releasedX, trigno);        
        Grain gIntervalTrigger = new Grain(intervalTrigger, releasedY, trigno);
        Grain gTriggerOut = new Grain(triggerOut, releasedLBump, trigno);
        Grain gTriggerOutSlow = new Grain (triggerOutSlow, releasedRBump, trigno);


        //FIRE
        Procedure xFeeder = () -> {HAL.feeder.fire();};
        Grain xFeederFire = new Grain(xFeeder, releasedX, stopFeed);


        // SHOOTER
        Procedure shooter = () -> {HAL.shoot.Run();};
        Procedure stop = () -> {HAL.shoot.stop();};
        Procedure shooterClose = () -> {HAL.shoot.shooterFire(Field.shooterClose);};
        
        Grain shoot = new Grain(shooter, releasedA, stop);


        // HOOD
        Procedure hoodmanual = () -> {HAL.hood.slowMoveUP();};
        Procedure hoodoff = () -> {HAL.hood.off();};

        Procedure hoodmanualdown = () -> {HAL.hood.slowMoveDown();};
        TC releasedDDown = () ->{return !operator.getDPadDown();};
        Grain hoodUp = new Grain (hoodmanual, releasedDUP, hoodoff);
        Grain hoodDown = new Grain (hoodmanualdown, releasedDDown, hoodoff);


        //LIMELIGHT TURRET
        // Procedure limelightT = () -> {HAL.turret.limeTOn(.5*(driver.getLeftTrigger()-driver.getRightTrigger()));};
        
        // Procedure limeoffT = () -> {HAL.turret.limeTOff(2);};
        // Grain GlimelightTOn = new Grain (limelightT, releaseBack, limelightT);
        // Grain GlimelightTOff = new Grain (limeoffT, releaseStart, limeoffT);
        

        //LIMELIGHT HOOD
        // Procedure limelightH = () -> {HAL.hood.limeH();};
        // Procedure limeoffH = () -> {HAL.hood.off();};
        // Grain GlimelightHOn = new Grain (limelightH, releaseStart, limelightH);
        // Grain GlimelightHOff = new Grain (limeoffH, releaseBack, limeoffH);


        //ARM
        // Procedure armed = () -> {HAL.arm.activate();};
        // TC releasedB2 = () -> {return !operator.getButtonStateB();};
        // Procedure disarm = () -> {HAL.arm.prepClimb();};
        // Grain arming = new Grain (armed, releasedB2, disarm);


        //SELFCLIMB
        //Procedure selfclimb = () -> {HAL.selfClimb.prepClimb();};
        //TC releasedRightBumper2 = () -> {return (HAL.selfClimb.run);};
        //Procedure selfcease = () -> {HAL.selfClimb.stopClimb();};
        //Grain self = new Grain (selfclimb, releasedRightBumper2, selfcease);
        Procedure selfClimb = () -> {HAL.selfClimb.startClimb();};
        TC done = () -> {return (HAL.selfClimb.run);};
        Procedure endClimb = () -> {HAL.selfClimb.endClimb();};
        Procedure retract = () -> {HAL.selfClimb.retract();};
        Grain engage = new Grain (selfClimb, done, endClimb);
        Grain disengage = new Grain (retract, done, endClimb);

        
        
        // Procedure spooltogg = () -> {HAL.spool.toggle();};
        // TC RTOperator = () -> {return (HAL.selfClimb.run);};
        // Procedure selfcease = () -> {HAL.selfClimb.stopClimb();};
        // Grain self = new Grain (selfclimb, releasedRightBumper2, selfcease);
        
        // Procedure spoolleft = () -> {HAL.spool.setL(0.5);};
        
        // Procedure spoolLoff = () -> {HAL.spool.setL(0);};
        // Grain GspoolL = new Grain (spoolleft, releasedminus, spoolLoff);
        
        // Procedure spoolright = () -> {HAL.spool.setR(0.5);};
        
        // Procedure spoolRoff = () -> {HAL.spool.setR(0);};
        // Grain GspoolR = new Grain (spoolright, releasedplus, spoolRoff);

        // Procedure spoolleftB = () -> {HAL.spool.setL(-0.5);};
        TC releasedcircle = () -> {return !operator.getRawButton(14);};
        // Grain GspoolLB = new Grain (spoolleftB, releasedcircle, spoolLoff);
        
        // Procedure spoolrightB = () -> {HAL.spool.setR(-0.5);};
       
        // Grain GspoolRB = new Grain (spoolrightB, releasedhome, spoolRoff);

        //DEPLOYMENY
        Procedure  deploymentL = () -> {HAL.deployment.setSpeedL(-1);};
        Procedure  deploymentR = () -> {HAL.deployment.setSpeedR(-1);};
        Procedure  deploymentLBack = () -> {HAL.deployment.setSpeedL(1);};
        Procedure  deploymentRBack = () -> {HAL.deployment.setSpeedR(1);};
        Procedure deploymentStopL = () ->{HAL.deployment.setSpeedL(0);};
        Procedure deploymentStopR = () ->{HAL.deployment.setSpeedR(0);};

        Grain gDeploymentL = new Grain(deploymentL, releasedminus, deploymentStopL);
        Grain gDeploymentR = new Grain(deploymentR, releasedplus, deploymentStopR);
        Grain gDeploymentLBack = new Grain(deploymentLBack, releasedcircle, deploymentStopL);
        Grain gDeploymentRBack = new Grain(deploymentRBack, releasedhome, deploymentStopR);
        
        //BUDDYCLIMB
        // Procedure buddyclimb = () -> {HAL.buddyClimb.prepClimb();};
        // TC releasedLeftBumper2 = () -> {return (HAL.buddyClimb.run);};
        // Procedure buddycease = () -> {HAL.buddyClimb.stopClimb();};
        // Grain buddy = new Grain (buddyclimb, releasedLeftBumper2, buddycease); 

        //WHEEL LIFTER
        // Procedure lift = () -> {HAL.lifter.prepLift();};
        // TC runLift = () -> {return (HAL.lifter.run);};
        // Procedure retract = () -> {HAL.lifter.stopLift();};
        // Grain gLift = new Grain (lift, runLift, retract); 

        Procedure wheelLift = () -> {HAL.lift2.wheelUp();};
        Procedure wheelDown = () -> {HAL.lift2.wheelDown();};
        TC releasedRightDPad = () -> {return !operator.getDPadRight();};
        Grain wheelFinal = new Grain (wheelLift, releasedRightDPad, wheelDown);
        
        
        Procedure limealign = () -> {HAL.drive.limeAlign();};
        Procedure driveoff = () -> {HAL.drive.drivelimeoff();};
        Grain Glimealign = new Grain (limealign, releasedB, driveoff); 

        Procedure limeapproach = () -> {HAL.drive.limeApache();};
        Grain Glimeapproach = new Grain (limeapproach, releasedY, driveoff); 

        Procedure limeback = () -> {HAL.drive.arcade(0, -0.5);};
        TC distanced = () -> {return HAL.limelight.getDistanceFromTarget() >= 100;};
        Grain Glimeback = new Grain (limeback, distanced, driveoff); 

        //Test Grain
        Procedure teleAuto = () -> {HAL.drive.test();};
        TC releasedA2 = () -> {return !operator.getButtonStateA();};
        Procedure end = () -> {HAL.drive.endTest();};
        Grain gTest = new Grain (teleAuto, releasedA2,end);

        //Color Wheel
        Procedure wheelGo = () -> {HAL.wheelofFortune.wheelie();};
        
        Procedure endWheel = () -> {HAL.wheelofFortune.off();};
        Grain wheelMove = new Grain (wheelGo, dRightRelease,endWheel);

        //Hood Move w A
        Procedure hoodCheck = () -> {HAL.hood.johnIsJohn();};
        // // Procedur
        Grain hoodie = new Grain(hoodCheck, releasedA,hoodoff);



        //button groups + initializing conditionals
        ///////////////////////////////////////
        //            DRIVER                 //
        ///////////////////////////////////////

        if(driver.getButtonStateA()){
            Field.limelightHeadache = true;
            Robot.mill.addGrain(shoot);
            //Robot.mill.addGrain(hoodie);

        }

        if(driver.getButtonStateB()){
            // HAL.limelight.setPipeline(1);
            Field.limelightHeadache = true;
            Robot.mill.addGrain(Glimealign);
        }

        
        if(driver.getButtonStateX()){
            Robot.mill.addGrain(xFeederFire);
            //double startTimerDelay3 = Timer.getFPGATimestamp();
            Robot.mill.addGrain(xConveyorFire);
            Robot.mill.addGrain(xTriggerFire);
            //Robot.mill.addGrain(gDelayFeeder);
        }

        if(driver.getButtonStateY()){
            // HAL.limelight.setPipeline(1);
            Field.limelightHeadache = true;
            Robot.mill.addGrain(Glimeapproach);
            // Robot.mill.addGrain(gIntervalTrigger);
        }


        if(driver.getDPadUp()){
            // Robot.mill.addGrain(hoodUp);
        }

        
        if(driver.getDPadDown()){
            // Robot.mill.addGrain(hoodDown);
        }


        if(driver.getButtonStateLeftBumper()){
            Robot.mill.addGrain(gUnfeed);
            Robot.mill.addGrain(gconveyout);
            Robot.mill.addGrain(gTriggerOut);
            //Robot.mill.addGrain(Glimeback);
        }


        if(driver.getButtonStateRightBumper()){
            Robot.mill.addGrain(gConvey);
            Robot.mill.addGrain(gFeed);
            Robot.mill.addGrain(gTriggerOutSlow);
            //  Robot.mill.addGrain(gIntervalFeeder);
            //Robot.mill.addGrain(gDelayFeeder);
        }
        
  
        if(driver.getRawButton(Gamepad.BUTTON_START)){
            // HAL.limelight.setPipeline(1);
            Field.limelightHeadache = true;
            //Robot.mill.addGrain(GlimelightHOn);
            // Robot.mill.addGrain(GlimelightTOn); 
        }

        if((driver.getRawButton(Gamepad.BUTTON_BACK))){
            
        }


        ///////////////////////////////////////
        //            OPERATOR    
                   //
        ///////////////////////////////////////

        if(operator.getDPadUp()){
            Robot.mill.addGrain(hoodUp);
        } 

        if(operator.getDPadDown()){
            Robot.mill.addGrain(hoodDown);
        } 
        if(operator.getDPadRight()){
            Robot.mill.addGrain(wheelMove);
        }

        if(operator.getButtonStateLeftBumper()){
            Field.operatorCool = false;
        }

        if(operator.getButtonStateRightBumper()){
          
          Field.operatorCool = true;
        }

        if(operator.getRawButton(7)){
            Field.spooltoggled = false;
        }

        if(operator.getRawButton(8)){
          
          Field.spooltoggled = true;
        }
        
        if(operator.getRawButton(9)){
          
            //Robot.mill.addGrain(GspoolL);
            Robot.mill.addGrain(gDeploymentL);
          }
          
        if(operator.getRawButton(10)){
          
            Robot.mill.addGrain(gDeploymentR);
          }

          if(operator.getRawButton(14)){
          
            Robot.mill.addGrain(gDeploymentLBack);
          }
          
        if(operator.getRawButton(13)){
          
            Robot.mill.addGrain(gDeploymentRBack);
          }

        if(operator.getButtonStateA()){
           Robot.mill.addGrain(gTest);
        }
        
        if(operator.getRawButtonPressed(Gamepad2.BUTTON_X)){
            
            
            Robot.mill.addGrain(disengage);
            
        }

        // if(operator.getRawButtonPressed(9)){
        //     Robot.mill.addGrain(buddy);
        // }

        if(operator.getRawButtonPressed(Gamepad2.BUTTON_Y)){
            //Field.handJob = true;
            HAL.drive.handOff();
            Robot.mill.addGrain(engage);
            
        }

        if(operator.getRawButtonPressed(Gamepad2.BUTTON_B)){
            Robot.mill.addGrain(wheelFinal);
        }
    }
}