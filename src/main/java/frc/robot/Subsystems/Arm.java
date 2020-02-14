package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import frc.robot.RobotMap;


public class Arm{

  int toggle=1;
  public boolean run=false;
  DoubleSolenoid solenoid2 = new DoubleSolenoid(RobotMap.s2fchannel,RobotMap.s2rchannel);
  DoubleSolenoid solenoid3 = new DoubleSolenoid(RobotMap.s3fchannel,RobotMap.s3rchannel);


  public Arm()
  {
      solenoid2.set(Value.kReverse);
      solenoid3.set(Value.kReverse);
  }

  public void activate(){
      if (toggle>0){
          solenoid2.set(Value.kForward);
          solenoid3.set(Value.kForward);
      }else{
          solenoid2.set(Value.kReverse);
          solenoid3.set(Value.kReverse);
      }
      run=false;
      toggle*=-1;
  }

  public void prepClimb(){
      run=true;
  }

}


