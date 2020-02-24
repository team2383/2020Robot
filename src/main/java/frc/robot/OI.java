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
import frc.robot.Field;

public class OI {

    Gamepad driver = new Gamepad(0);
    public Gamepad operator = new Gamepad(1);

    public OI(){
        
    //these are active listeners
    //make procedures and conditions

    // DRIVE
    Procedure drive = () ->{HAL.drive.arcade((driver.getRightX()*.6),(-driver.getLeftY()*.6));};

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
    }


    public void listener(){
    
        // CHAMBER (PRE-SHOOT)
        Procedure feed = () ->{HAL.feeder.feed();};
        Procedure stopFeed = () -> {HAL.feeder.off();};
        TC releasedRBump = () -> {return !(driver.getButtonStateRightBumper());};
        Procedure unfeed = () -> {HAL.feeder.unfeed();};
        TC releasedLBump = ()->{return !(driver.getButtonStateLeftBumper());};

        // CONVEYOR
        Procedure conveyor1 = () -> {HAL.conveyor.pull();};
        Procedure off = () -> {HAL.conveyor.off();};
        Procedure conveyor1out = () -> {HAL.conveyor.out();};
        TC releasedLBump2 = ()->{return !(driver.getButtonStateLeftBumper());};
        Grain conveyout = new Grain(conveyor1out, releasedLBump2,off);
       
        //TRIGGER
        Procedure trig = () -> {HAL.triggered.spinMedium();};
        Procedure trigno = () -> {HAL.triggered.off();};
        TC releasedX = () -> {return !(driver.getButtonStateX());};
        Grain tiger = new Grain(trig, releasedX, trigno);

        // SHOOTER
        Procedure shooter = () -> {HAL.shoot.Run();};
        Procedure stop = () -> {HAL.shoot.stop();};
        TC releasedA = ()->{return !(driver.getButtonStateA());};

        // HOOD
        Procedure hoodmanual = () -> {HAL.hood.slowMoveUP();};
        TC releasedDUP = () ->{return !driver.getDPadUp();};
        Procedure hoodoff = () -> {HAL.hood.off();};

        Procedure hoodmanualdown = () -> {HAL.hood.slowMoveDown();};
        TC releasedDDown = () ->{return !driver.getDPadDown();};

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
        // Procedure telego = () -> {HAL.telescope.TelescopeGo(3, 0.9);};
        TC releasedDUP2 = () -> {return !(operator.getDPadUp());};
        TC releasedDDOWN2 = () -> {return !(operator.getDPadDown());};
        // Procedure telestop = () -> {HAL.telescope.off();};
        // Procedure teledown = () -> {HAL.telescope.TelescopeGo(3, -0.9);};
        // Grain teleNow = new Grain (telego, releasedDUP2, telestop);
        // Grain Gteledown = new Grain(teledown, releasedDDOWN2, telestop);



        //button groups + initializing conditionals

        Grain shoot = new Grain(shooter, releasedA, stop);
        if(driver.getButtonStateA()){
            Robot.mill.addGrain(shoot);
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

        //Turns on and off the conveyor
        if(operator.getButtonStateRightBumper()){
            Field.operatorCool = false;
            
        }
    
        if(operator.getButtonStateLeftBumper()){
            Field.operatorCool = true;
        }

        Grain gFeed = new Grain(feed, releasedRBump, stopFeed);

        Grain gConvey = new Grain(conveyor1, releasedRBump, off);

        if(driver.getButtonStateRightBumper()){
            
            Robot.mill.addGrain(gConvey);
            Robot.mill.addGrain(gFeed);
            


        }
        if(driver.getButtonStateX()){
            Robot.mill.addGrain(tiger);
            // Robot.mill.addGrain(gConvey);
            // Robot.mill.addGrain(gFeed);
        }
  


        if(driver.getRawButton(Gamepad.BUTTON_START)){
            Robot.mill.addGrain(GlimelightHOn);
            Robot.mill.addGrain(GlimelightTOn); 
        }


        if((driver.getRawButton(Gamepad.BUTTON_BACK))){
            Robot.mill.addGrain(GlimelightHOff);
            Robot.mill.addGrain(GlimelightTOff); 
        }



        ///////////////////////////////////////
        //            OPERATOR               //
        ///////////////////////////////////////

        if(operator.getDPadUp()){
            // Robot.mill.addGrain(teleNow);
        } 

        if(operator.getDPadDown()){
            // Robot.mill.addGrain(Gteledown);
        } 
        
        if(operator.getButtonStateX()){
            Robot.mill.addGrain(buddy);
        }

        if(operator.getButtonStateY()){
            Robot.mill.addGrain(self);
        }

    
    }
}

