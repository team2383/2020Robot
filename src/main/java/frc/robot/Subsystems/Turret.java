package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.HAL;
import frc.robot.Field;

public class Turret{

  WPI_TalonSRX turret = new WPI_TalonSRX(RobotMap.turretPort);

  public Turret()
  {}
 
  public void move(double stick){
    if (getTurretPosition() > 36200 && stick<0){
      turret.set(ControlMode.PercentOutput, 0);

    }
    else if (getTurretPosition() < -27900 && stick>0){
      turret.set(ControlMode.PercentOutput, 0);

    }
    else if (Math.abs(stick)>0.05){
      turret.set(ControlMode.PercentOutput, stick);

    } else {
      turret.set(ControlMode.PercentOutput, 0.0);

    }
      
  }

  public void moveto(double pos){
    turret.set(ControlMode.MotionMagic, pos);
  }

  public void limeTOn(double stick){
    double output;
    HAL.limelight.setPipeline(1);
    double xOffset = HAL.limelight.xOffset();
    double stop = 0;
    double divisor = 27;
    output = -(xOffset/divisor) * 1.1; 

    if(Field.limelightOn) {
      if (HAL.limelight.hasTargets()){
        if (getTurretPosition() > 35208 && output<0){
          turret.set(ControlMode.PercentOutput, 0);
    
        }
        else if (getTurretPosition() < -29900 && output>0){
          turret.set(ControlMode.PercentOutput, 0);
    
        }
        else {
          turret.set(ControlMode.PercentOutput, output);
    
        } 
  
      }
      else{
        //HAL.limelight.setPipeline(pipeline);
        turret.set(ControlMode.PercentOutput, 0);
      }


    }
    else{
      HAL.limelight.setPipeline(2);
      move(stick);
      
    }
    
    

  }

  public void limeTOff(double pipeline){
    HAL.limelight.setPipeline(pipeline);
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



