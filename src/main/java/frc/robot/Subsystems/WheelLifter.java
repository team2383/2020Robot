// package frc.robot.Subsystems;

// import edu.wpi.first.wpilibj.DoubleSolenoid;
// import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
// import frc.robot.RobotMap;


// public class WheelLifter{
    
//     int toggle=1;
//     public boolean run=false;
//     // DoubleSolenoid solenoid3 = new DoubleSolenoid(RobotMap.s3fchannel, RobotMap.s3rchannel);


//     public WheelLifter(){

//     }

//     public void prepLift(){
//         run=true;
//     }

//     public void stopLift(){
//         if (toggle>0){
//             // solenoid3.set(Value.kForward);
//         }else{
//             // solenoid3.set(Value.kReverse);
//         }
//         run=false;
//         toggle*=-1;
//     }
// }