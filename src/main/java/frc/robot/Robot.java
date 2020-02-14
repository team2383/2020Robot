package frc.robot;

//import edu.wpi.first.hal.HAL;
import edu.wpi.first.hal.sim.DriverStationSim;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.HAL;
import frc.robot.OI;
import static frc.robot.Subsystems.Hood.hoodc;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import edu.wpi.first.wpilibj.TimedRobot;
//import frc.robot.OI.driver2;
import frc.robot.ninjaLib.Gamepad;
import frc.robot.auto.*;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DriverStation.MatchType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot{

      public static Loop mill = new Loop();
      OI oi = new OI();

  Command autoCommand;
  SendableChooser<Command> autoChooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    @SuppressWarnings("unused")
		  HAL hal = new HAL();
		  @SuppressWarnings("unused")

    OI oi = new OI();
    autoChooser = new SendableChooser<Command>();
    
   autoChooser.addOption("Five Feet", new BaselineAuto(false));

   SmartDashboard.putData("AUTO CHOOSER", autoChooser);
 }
 @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Turret Position", HAL.turret.getTurretPosition());
    SmartDashboard.putNumber("Hood Position", HAL.hood.getHoodPosition());
    SmartDashboard.putNumber("Gyro Angle", HAL.navX.getAngle());
    SmartDashboard.putNumber("Gyro Yaw", HAL.navX.getRawGyroX());
    SmartDashboard.putNumber("Distance from target in feet", HAL.limelight.getDistanceFromTarget());
    SmartDashboard.putNumber("Shooter Speed", HAL.shoot.getShooterVelocity());
    SmartDashboard.putNumber("tele height", HAL.telescope.getPosition());
    SmartDashboard.putNumber("Shooter RPM", HAL.shoot.getRPM());
    SmartDashboard.putNumber("Differential", HAL.shoot.differential());
    SmartDashboard.putNumber("Shooter % Output", HAL.shoot.getPercentageOutput());
  }
@Override
 public void disabledInit() {
  Scheduler.getInstance().removeAll();
}

// @Override
//   public void disabledPeriodic() {
//     Scheduler.getInstance().run();
//   }

@Override
  public void autonomousInit() {

    autoCommand = autoChooser.getSelected();
		autoCommand = (Command) autoChooser.getSelected();
		
		if (autoCommand != null) {
			autoCommand.start();
    }
    
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }


  public void teleopInit() {
    if (autoCommand != null){
      autoCommand.cancel();
    }
    SmartDashboard.putNumber("Is on", 10);
    // SmartDashboard.putBoolean("Ready to Shoot?", HAL.hood.isangledY());
    // SmartDashboard.putNumber("Hpos", hoodc.getSelectedSensorPosition());
    // HAL.limelight.setPipeline(1);
  }
  
 
  public void teleopPeriodic(){
    mill.seed();
    oi.listener();
    //SmartDashboard.putNumber("turretbith", HAL.turret.getTurretPosition());
  }

  public void test() {
  }
}

