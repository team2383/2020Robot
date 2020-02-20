/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import frc.robot.Robot;
import frc.robot.Grain;
import frc.robot.Procedure;
import frc.robot.TC;
import frc.robot.ninjaLib.Gamepad;
import frc.robot.Subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {

    Gamepad driver = new Gamepad(0);
    public Gamepad operator = new Gamepad(1);


    public OI(){
    //these are active listeners
    //make procedures and conditions

    // DRIVE
    //--old drive
    Procedure drive = () ->{HAL.drive.arcade((driver.getRightX()*.6),(-driver.getLeftY()*.6));};
    //Dylan's race car drive he wanted to try
    //Procedure drive = () ->{HAL.drivetrain.arcade(driver.getLeftX()*.6,driver.getRightTrigger()-driver.getLeftTrigger()*.6);};

    TC noTC = ()->{return false;}; 
    Grain e = new Grain(drive,noTC,drive);
    Robot.mill.addGrain(e);

    // TURRET
    //--old turret
    Procedure turretmanual = () -> {HAL.turret.move((0.5*driver.getLeftTrigger())-0.5*driver.getRightTrigger());};
    //Dylan's race car drive he wanted to try
    //Procedure turretmanual = () -> {HAL.turret.move(driver.getRightX());};
    //Procedure resetTurret = () -> {HAL.turret.zeroTurret();};
    //Procedure positionTurret = () -> {HAL.turret.angular();};
    TC hasTarget = () -> {return HAL.limelight.hasTargets();};
    //Grain turretReseter = new Grain(resetTurret,noTC,resetTurret);
    //Grain turretSetter = new Grain(positionTurret,hasTarget,positionTurret);
    Grain t = new Grain(turretmanual,noTC,turretmanual);
    Robot.mill.addGrain(t);
    //Robot.mill.addGrain(turretReseter);
    //Robot.mill.addGrain(turretSetter);

    // TURRET VISION
    Procedure zeroTurret = () -> {HAL.turret.zeroTurret();};
    Grain GzeroTurret = new Grain (zeroTurret, noTC, zeroTurret);
    Robot.mill.addGrain(GzeroTurret);
    }

    // HOOD
    
    // public void periodic(){  
    //     SmartDashboard.putNumber("Hood Value", HAL.hood.getHoodPosition());  
    // }

    public void listener(){
    
        // CHAMBER (PRE-SHOOT)
        Procedure feed = () ->{HAL.feeder.feed();};
        Procedure stopFeed = () -> {HAL.feeder.off();};
        TC releasedRBump = () -> {return !(driver.getButtonStateRightBumper());};
        //Grain GFeed = new Grain(feed, releasedRBump, stopFeed);
    
        Procedure unfeed = () -> {HAL.feeder.unfeed();};
        TC releasedLBump = ()->{return !(driver.getButtonStateLeftBumper());};
        //Grain GUnfeed = new Grain(unfeed, releasedLBump, stopFeed);

        //Procedure interval_feed = () ->{HAL.feeder.interval_feed(0.5);};
        //Grain GIntervalFeed = new Grain(interval_feed, releasedRBump, stopFeed);

        // CONVEYOR
        Procedure conveyor1 = () -> {HAL.conveyor.pull();};
        Procedure off = () -> {HAL.conveyor.off();};
        TC releasedRBump2 = ()->{return !(driver.getButtonStateRightBumper());};
        Grain conveyin = new Grain(conveyor1, releasedRBump2,off);
        
        Procedure conveyor1out = () -> {HAL.conveyor.out();};
        TC releasedLBump2 = ()->{return !(driver.getButtonStateLeftBumper());};
        Grain conveyout = new Grain(conveyor1out, releasedLBump2,off);
        
        //Procedure interval_conveyor = () -> {HAL.conveyor.interval_conveyor(0.5);};
        //Grain GIntervalConveyor = new Grain(interval_conveyor, releasedRBump2, off);
       //CHAMBER(LAUNCH)
       Procedure triggerX = () -> {HAL.triggered.spin(0.5);};
       Procedure trigOffX = () -> {HAL.triggered.off();};
       TC releasedX = () ->{return !(driver.getButtonStateX());};
        //TRIGGER
        // Procedure trigger = () -> {HAL.triggered.spinHigh();};
        // Procedure trigOff = () -> {HAL.triggered.off();};
        // TC releasedBBump = () ->{return !(driver.getButtonStateB());};
        // Grain triglow = new Grain (trigger, releasedBBump, trigOff);

        // Procedure arctrig = () -> {HAL.triggered.spinOut();};
        // TC releasedXBump = () ->{return !driver.getButtonStateX();};
        // Grain trigout = new Grain (arctrig, releasedXBump, trigOff);
        Procedure conveyX = () -> {HAL.conveyor.toggle(true);};
        Procedure conveyXoff = () -> {HAL.conveyor.toggle(false);};
        Procedure conveyoff = () -> {HAL.conveyor.off();};

        Procedure feedX = () -> {HAL.feeder.spin(0.5);};
        Procedure feedoffX = () -> {HAL.feeder.off();};   

        Grain GtriggerX = new Grain (triggerX, releasedX, trigOffX);
        Grain GconveyX = new Grain (conveyX, releasedX, conveyoff);
        Grain GconveyXoff = new Grain (conveyXoff, releasedX, conveyoff);
        Grain GfeedX = new Grain (feedX, releasedX, feedoffX);
           

        // SHOOTER
        Procedure shooter = () -> {HAL.shoot.Run();};
        Procedure stop = () -> {HAL.shoot.stop();};
        TC releasedA = ()->{return !(driver.getButtonStateA());};
        //Grain shoot = new Grain(shooter, releasedA,stop);

        // HOOD
        Procedure hoodmanual = () -> {HAL.hood.slowMoveUP();};
        TC releasedDUP = () ->{return !driver.getDPadUp();};
        Procedure hoodoff = () -> {HAL.hood.off();};
        //Grain hoodUp = new Grain (hoodmanual, releasedDUP, hoodoff);

        Procedure hoodmanualdown = () -> {HAL.hood.slowMoveDown();};
        TC releasedDDown = () ->{return !driver.getDPadDown();};
       // Grain hoodDown = new Grain (hoodmanualdown, releasedDDown, hoodoff);
        
        //LIMELIGHT TURRET
        Procedure limelightT = () -> {HAL.turret.limeTOn(1);};
        TC releaseStart = () -> {return !driver.getRawButton(8);};
        TC releaseBack = () -> {return !driver.getRawButton(7);};
        Procedure limeoffT = () -> {HAL.turret.limeTOff(2);};
        Grain GlimelightTOn = new Grain (limelightT, releaseStart, limelightT);
        Grain GlimelightTOff = new Grain (limeoffT, releaseBack, limeoffT);
        
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

        //WHEELIE
        // Procedure wheelon = () -> {HAL.wheelofFortune.wheelie();};
        // TC releasedBackButton = () -> {return driver.getRawButton(Gamepad.BUTTON_BACK);};
        // Procedure wheelno = () -> {HAL.wheelofFortune.off();};
        // Grain wheelNow = new Grain (wheelon, releasedBackButton, wheelno);


        //SELFCLIMB
        Procedure selfclimb = () -> {HAL.selfClimb.prepClimb();};
        TC releasedRightBumper2 = () -> {return !operator.getButtonStateY();};
        Procedure selfcease = () -> {HAL.selfClimb.stopClimb();};
        Grain self = new Grain (selfclimb, releasedRightBumper2, selfcease);

        //BUDDYCLIMB
        Procedure buddyclimb = () -> {HAL.buddyClimb.prepClimb();};
        TC releasedLeftBumper2 = () -> {return (HAL.buddyClimb.run);};
        Procedure buddycease = () -> {HAL.buddyClimb.stopClimb();};
        Grain buddy = new Grain (buddyclimb, releasedLeftBumper2, buddycease); 


        //TELESCOPE
        Procedure telego = () -> {HAL.telescope.TelescopeGo(3, 0.9);};
        TC releasedDUP2 = () -> {return !(operator.getDPadUp());};
        TC releasedDDOWN2 = () -> {return !(operator.getDPadDown());};
        Procedure telestop = () -> {HAL.telescope.off();};
        Procedure teledown = () -> {HAL.telescope.TelescopeGo(3, -0.9);};
        Grain teleNow = new Grain (telego, releasedDUP2, telestop);
        Grain Gteledown = new Grain(teledown, releasedDDOWN2, telestop);

        
        // Procedure toggleconvey = () -> {HAL.conveyor.toggle();;};
        // Procedure togglefeed = () -> {HAL.feeder.toggle();;};
        // TC pressedB = () -> {return (operator.getButtonStateB());};
        // TC pressedY = () -> {return (operator.getButtonStateY());};
        // Grain Gtoggleconvey = new Grain(toggleconvey, pressedB, toggleconvey);
        // Grain Gtogglefeed = new Grain(togglefeed, pressedY, togglefeed);

        


        //button groups + initializing conditionals

        Grain shoot = new Grain(shooter, releasedA,stop);
        if(driver.getButtonStateA()){
            Robot.mill.addGrain(shoot);
        }
        
        // Grain triglow = new Grain (trigger, releasedBBump, trigOff);
        // if(driver.getButtonStateB()){
        //    Robot.mill.addGrain(triglow);
        // }


        if(driver.getButtonStateX()){
            if(operator.getRawButton(Gamepad.BUTTON_A)){
                Robot.mill.addGrain(GtriggerX);
                Robot.mill.addGrain(GconveyX);
                Robot.mill.addGrain(GfeedX);
            }
            
            
            if(operator.getRawButton(Gamepad.BUTTON_B)){
                Robot.mill.addGrain(GtriggerX);
                //Robot.mill.addGrain(GconveyXoff);
                Robot.mill.addGrain(GfeedX);
            }
            
        }

        if(driver.getButtonStateY()){
        }

        Grain hoodUp = new Grain (hoodmanual, releasedDUP, hoodoff);
        if(driver.getDPadUp()){
            Robot.mill.addGrain(hoodUp);
        }

        Grain hoodDown = new Grain (hoodmanualdown, releasedDDown, hoodoff);
        if(driver.getDPadDown()){
            Robot.mill.addGrain(hoodDown);
        }

        
        Grain gUnfeed = new Grain(unfeed, releasedLBump, stopFeed);
        if(driver.getButtonStateLeftBumper()){
            Robot.mill.addGrain(gUnfeed);
            Robot.mill.addGrain(conveyout);
        }

        Grain gFeed = new Grain(feed, releasedRBump, stopFeed);
        if(driver.getButtonStateRightBumper()){
            Robot.mill.addGrain(gFeed); 

        //  if(driver.getButtonStateRightBumper()){
        //      Robot.mill.addGrain(GFeed); 
        //      Robot.mill.addGrain(conveyin);
        //  }
        }

        // New Operator/Driver Duo Conveyer/Feeder
        // if(driver.getButtonStateRightBumper() ){
        //     if(operator.getRawButton(Gamepad.BUTTON_A)){
        //         Robot.mill.addGrain(GtriggerX);
        //         Robot.mill.addGrain(GconveyX);
        //         Robot.mill.addGrain(GfeedX);
        //     }
            
            
        //     if(operator.getRawButton(Gamepad.BUTTON_B)){
        //         Robot.mill.addGrain(GtriggerX);
        //         Robot.mill.addGrain(GconveyXoff);
        //         Robot.mill.addGrain(GfeedX);
        //     }
        // }


    


       /* if(driver.getButtonStateRightBumper()){
            Robot.mill.addGrain(GIntervalFeed);
            Robot.mill.addGrain(GIntervalConveyor);
        }*/

        if(driver.getRawButton(Gamepad.BUTTON_START)){
            Robot.mill.addGrain(GlimelightHOn);
            Robot.mill.addGrain(GlimelightTOn); 
        }

        // if((HAL.hood.isangled())){
        //     Robot.mill.addGrain(GlimelightT);
        // }

        if((driver.getRawButton(Gamepad.BUTTON_BACK))){
            Robot.mill.addGrain(GlimelightHOff);
            Robot.mill.addGrain(GlimelightTOff); 
        }

        /*if(operator.getButtonStateA()){
            Robot.mill.addGrain(shifting);
        }*/
        // if(driver.getRawButton(Gamepad.BUTTON_BACK)){
        //     Robot.mill.addGrain(wheelNow);
        // }  


        ///////////////////////////////////////
        //            OPERATOR               //
        ///////////////////////////////////////

        if(operator.getDPadUp()){
            Robot.mill.addGrain(teleNow);
        } 

        if(operator.getDPadDown()){
            Robot.mill.addGrain(Gteledown);
        } 
        
        if(operator.getButtonStateX()){
            Robot.mill.addGrain(buddy);
        }

        if(operator.getButtonStateY()){
            Robot.mill.addGrain(self);
        }

        // if(operator.getButtonStateB()){
        //     Robot.mill.addGrain(arming);
        // }
    
    }
}
