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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;




public class OI {

    Gamepad driver = new Gamepad(0);
    Gamepad driver2 = new Gamepad(1);


    public OI(){
    //these are active listeners
    //make procedures and conditions

    // DRIVE
    //--old drive
    Procedure drive = () ->{HAL.drivetrain.arcade((driver.getRightX()*.6),(-driver.getLeftY()*.6));};
    //Dylan's race car drive he wanted to try
    //Procedure drive = () ->{HAL.drivetrain.arcade(driver.getLeftX()*.6,driver.getRightTrigger()-driver.getLeftTrigger()*.6);};

    TC noTC = ()->{return false;}; 
    Grain e = new Grain(drive,noTC,drive);
    Robot.mill.addGrain(e);

    // TURRET
    //--old turret
    Procedure turretmanual = () -> {HAL.turret.move((driver.getLeftTrigger())-driver.getRightTrigger());};
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
        // FEEDER
        Procedure feed = () ->{HAL.feeder.feed();};
        Procedure stopFeed = () -> {HAL.feeder.off();};
        TC releasedRBump = () -> {return !(driver.getButtonStateRightBumper());};
        Grain GFeed = new Grain(feed, releasedRBump, stopFeed);
    
        Procedure unfeed = () -> {HAL.feeder.unfeed();};
        TC releasedLBump = ()->{return !(driver.getButtonStateLeftBumper());};
        Grain GUnfeed = new Grain(unfeed, releasedLBump, stopFeed);

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

        // SHOOTER
        double shooterPower = SmartDashboard.getNumber("Shooter Power", -.7);
        Procedure shooter = () -> {HAL.shoot.out(shooterPower);};
        //Procedure shooter = () -> {HAL.shoot.out();};
        Procedure stop = () -> {HAL.shoot.stop();};
        TC releasedA = ()->{return !(driver.getButtonStateA());};
        Grain shoot = new Grain(shooter, releasedA,stop);

        // HOOD
        Procedure hoodmanual = () -> {HAL.hood.slowMoveUP();};
        TC releasedDUP = () ->{return !driver.getDPadUp();};
        Procedure hoodoff = () -> {HAL.hood.off();};
        Grain hoodUp = new Grain (hoodmanual, releasedDUP, hoodoff);

        Procedure hoodmanualdown = () -> {HAL.hood.slowMoveDown();};
        TC releasedDDown = () ->{return !driver.getDPadDown();};
        Grain hoodDown = new Grain (hoodmanualdown, releasedDDown, hoodoff);
        
        //LIMELIGHT TURRET
        Procedure limelightT = () -> {HAL.turret.limeT(6);};
        TC withinrange = () ->{return HAL.hood.isangled();};
        TC isTarget = () -> {return !(HAL.limelight.hasTargets());};
        Procedure limeoffT = () -> {HAL.turret.off();};
        Grain GlimelightT = new Grain (limelightT, isTarget, limeoffT);
        
        //LIMELIGHT HOOD
        Procedure limelightH = () -> {HAL.hood.limeH();};
        Procedure limeoffH = () -> {HAL.hood.off();};
        //TC withinrangeY = () ->{return HAL.hood.off();};
        TC releasedStart = () -> {return driver.getRawButton(8);};
        Grain GlimelightH = new Grain (limelightH, releasedStart, limeoffH);

        //SHIFTER
        Procedure armOut = () -> {HAL.arm.deployArm();};
        TC releasedYButton = () -> {return !driver2.getButtonStateA();};
        Procedure armcease = () -> {HAL.arm.stopArm();};
        Grain armNow = new Grain (armOut, releasedYButton, armcease);

        //WHEELIE
        // Procedure wheelon = () -> {HAL.wheelofFortune.wheelie();};
        // TC releasedBackButton = () -> {return driver.getRawButton(Gamepad.BUTTON_BACK);};
        // Procedure wheelno = () -> {HAL.wheelofFortune.off();};
        // Grain wheelNow = new Grain (wheelon, releasedBackButton, wheelno);


        //SELFCLIMB
        Procedure selfclimb = () -> {HAL.selfClimb.prepClimb();};
        TC releasedRightBumper2 = () -> {return !driver2.getButtonStateY();};
        Procedure selfcease = () -> {HAL.selfClimb.stopClimb();};
        Grain self = new Grain (selfclimb, releasedRightBumper2, selfcease);

        //BUDDYCLIMB
        Procedure buddyclimb = () -> {HAL.buddyClimb.prepClimb();};
        TC releasedLeftBumper2 = () -> {return !driver2.getButtonStateX();};
        Procedure buddycease = () -> {HAL.buddyClimb.stopClimb();};
        Grain buddy = new Grain (buddyclimb, releasedLeftBumper2, buddycease); 


        //TELESCOPE
        Procedure telego = () -> {HAL.telescope.TelescopeGo();};
        TC releasedA2 = () -> {return !(driver2.getButtonStateA());};
        Procedure telestop = () -> {HAL.telescope.off();
        };
        Grain teleNow = new Grain (telego, releasedA2, telestop);


        //button groups + initializing conditionals

        if(driver.getButtonStateA()){
            Robot.mill.addGrain(shoot);
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
         //   Robot.mill.addGrain(GlimelightH); 
        }
        
        // if((HAL.hood.isangled())){
        //     Robot.mill.addGrain(GlimelightT);
        // }

        if((driver.getRawButton(Gamepad.BUTTON_BACK))){
            Robot.mill.addGrain(GlimelightT);
        }

        if(driver2.getButtonStateA()){
            Robot.mill.addGrain(armNow);
        }
        // if(driver.getRawButton(Gamepad.BUTTON_BACK)){
        //     Robot.mill.addGrain(wheelNow);
        // }  

        if(driver2.getButtonStateA()){
            Robot.mill.addGrain(teleNow);
        } 
        
        if(driver2.getButtonStateX()){
            Robot.mill.addGrain(buddy);
        }

        if(driver2.getButtonStateY()){
            Robot.mill.addGrain(self);
        }
    }
}
