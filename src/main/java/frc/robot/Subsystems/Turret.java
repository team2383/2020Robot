package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.HAL;

public class Turret{

  WPI_TalonSRX turret = new WPI_TalonSRX(RobotMap.turretPort);

  public Turret()
  {}
  
  public void move(double stick){
    turret.set(ControlMode.PercentOutput, stick);
  }

  public void limeT(double pipeline){
    double output;
    double xOffset = HAL.limelight.xOffset();
    double stop = 0;
    double divisor = 27*2;

    output = (xOffset/divisor); 
    
    if (HAL.limelight.hasTargets()){
      HAL.limelight.setPipeline(pipeline);
      turret.set(ControlMode.PercentOutput, -output);
    }
    else{
      HAL.limelight.setPipeline(pipeline);
      turret.set(ControlMode.PercentOutput, stop);
    }
  }

  public void off(){
    turret.set(ControlMode.PercentOutput, 0);
  }

  public void zeroTurret(){
    double xOffset = HAL.limelight.xOffset();
    if (xOffset > -3 && xOffset < 3){
      turret.setSelectedSensorPosition(0);
      HAL.navX.reset();
    }
  }

  public int getTurretPosition(){
    return turret.getSelectedSensorPosition();
  }

  // public void periodic(){
  //   SmartDashboard.putNumber("Turret Position", turret.getSelectedSensorPosition());
  //   SmartDashboard.putNumber("Gyro Yaw", HAL.navX.getYaw());
  //   SmartDashboard.putNumber("Gyro Angle", HAL.navX.getAngle());
  //   SmartDashboard.putNumber("Gyro Yaw", HAL.navX.getRawGyroX());
  // }
}



