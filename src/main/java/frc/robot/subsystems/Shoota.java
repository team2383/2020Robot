/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.ninjaLib.*;
import frc.robot.Constants;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Shoota extends StatefulSubsystem<Shoota.State> {
  
  CANSparkMax shootaMaster = new CANSparkMax(Constants.shooterMasterPort, MotorType.kBrushless);
  CANSparkMax shootaFollower = new CANSparkMax(Constants.shooterFollowerPort, MotorType.kBrushless);

  public CANPIDController shootampid = new CANPIDController(shootaMaster);
  public CANPIDController shootafpid = new CANPIDController(shootaFollower);

  public void configMotorController(int timeout){
    shootampid.setP(1);
    shootampid.setI(0);
    shootampid.setD(0);

    shootafpid.setP(1);
    shootafpid.setI(0);
    shootafpid.setD(0);
  }

  public static enum State{
    BIGSHOOTA,
    LILSHOOTA,
    COOLIN
  }

  
  public Shoota()
  {
   
  }



  @Override
  public void setState(State state) {
		switch (state) {
      case BIGSHOOTA:
        //runs at 80%
        shootaMaster.set(0.8); 
        shootaFollower.set(0.8);
        break;
        case LILSHOOTA:
        //runs at 50%
        shootaMaster.set(0.6); 
        shootaFollower.set(0.6);
        break;
      // default; just be coolin tho
      default:
      case COOLIN:
      shootaMaster.set(0); 
      shootaFollower.set(0);
        break;
    }
    }

  @Override
  public void initDefaultCommand() {
    
  }
}