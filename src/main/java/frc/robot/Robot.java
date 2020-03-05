package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

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
    
   HAL.limelight.setPipeline(1);

   SmartDashboard.putData("AUTO CHOOSER", autoChooser);
 }

 @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Hood Position", HAL.hood.getHoodPosition());
    SmartDashboard.putNumber("Gyro Angle", HAL.navX.getAngle());
    SmartDashboard.putNumber("Gyro Yaw", HAL.navX.getRawGyroX());
    SmartDashboard.putNumber("Gyro Pitch", HAL.navX.getRawGyroZ());
    SmartDashboard.putNumber("Distance from target in inches", HAL.limelight.getDistanceFromTarget());
    SmartDashboard.putNumber("Shooter Speed", HAL.shoot.getShooterVelocity());
    SmartDashboard.putNumber("Shooter RPM", HAL.shoot.getRPM());
    SmartDashboard.putNumber("Differential", HAL.shoot.differential());
    SmartDashboard.putNumber("Shooter % Output", HAL.shoot.getPercentageOutput());
    SmartDashboard.putNumber("Shooter % Output Follower", HAL.shoot.getPercentageOutputFollower());
    SmartDashboard.putNumber("CL Error", HAL.shoot.getClosedLoopError());
    SmartDashboard.putNumber("Left Master Velocity", HAL.drive.getLeftVelocity());
    SmartDashboard.putNumber("Left Position", HAL.drive.getLeftPosition());
    SmartDashboard.putNumber("Right Velocity", HAL.drive.getRightVelocity());
    SmartDashboard.putNumber("Right Position", HAL.drive.getRightPosition());
    SmartDashboard.putNumber("currentTimeMilli", HAL.feeder.displayTimer());
    SmartDashboard.putNumber("Feeder Speed", HAL.feeder.getFeederSpeed());
  }
@Override
 public void disabledInit() {
  Scheduler.getInstance().removeAll();
}


@Override
  public void autonomousInit() {

    autoCommand = autoChooser.getSelected();
		autoCommand = (Command) autoChooser.getSelected();
    HAL.drive.resetEncoders();
    HAL.navX.zeroYaw();
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
    HAL.limelight.setPipeline(1);
    HAL.drive.configMotorController();
    double startDelayTimer2 = Timer.getFPGATimestamp();
  }
  
 
  public void teleopPeriodic(){
    mill.seed();
    oi.listener();
    Scheduler.getInstance().run();
  }

  public void test() {
  }
}

