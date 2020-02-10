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
  //We make it return an int so it can exit if a certain condition is reached
  int move(double stick){
    if (getTurretPosition() > 37000 && stick>0){
      return 0;
    }
    else if (getTurretPosition() < -27000 && stick<0){
      return 0;
    }
    turret.set(ControlMode.PercentOutput, stick);
    return 1;
      
  }

  // public void move(double stick){
  //     turret.set(ControlMode.PercentOutput, stick);
  // }

  public void moveto(double pos){
    turret.set(ControlMode.MotionMagic, pos);
  }

  public void limeT(double pipeline){
    double output;
    double xOffset = HAL.limelight.xOffset();
    double stop = 0;
    double divisor = 27;

    output = (xOffset/divisor); 
    
    if (HAL.limelight.hasTargets()){
      if (getTurretPosition() < -26000){
        moveto(-25500);
      }
      else if (getTurretPosition() > 36000){
        moveto(-35500);
      }
      else{
      //HAL.limelight.setPipeline(pipeline);
      turret.set(ControlMode.PercentOutput, -output);
      }
    }
    else{
      //HAL.limelight.setPipeline(pipeline);
      turret.set(ControlMode.PercentOutput, stop);
    }
  }

  public void off(){
    turret.set(ControlMode.PercentOutput, 0);
  }

  public void zeroTurret(){
    double xOffset = HAL.limelight.xOffset();
    if (xOffset > -3 && xOffset < 3 && HAL.limelight.hasTargets()){
      //turret.setSelectedSensorPosition(0);
     //HAL.TnavX.reset();
    }
  }

  public void angular(){
    setPosition((331776/360) * HAL.TnavX.getAngle());
  }

  public int getTurretPosition(){
    return turret.getSelectedSensorPosition();
  }

  public void setPosition(double pos){
    turret.set(ControlMode.MotionMagic, pos);
  }


  public void periodic(){
    SmartDashboard.putNumber("Turret Position", turret.getSelectedSensorPosition());
    SmartDashboard.putNumber("Gyro Yaw", HAL.navX.getYaw());
    SmartDashboard.putNumber("Gyro Angle", HAL.navX.getAngle());
    SmartDashboard.putNumber("Gyro Yaw", HAL.navX.getRawGyroX());
  }
}



