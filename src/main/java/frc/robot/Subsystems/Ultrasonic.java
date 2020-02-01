// package frc.robot.Subsystems;

// import edu.wpi.first.wpilibj.AnalogInput;
// import frc.robot.HAL;


// public class Ultrasonic {
  
//   private static final double kValueToInches = 0.125;
//   private final AnalogInput m_ultrasonic = new AnalogInput(10); //random number

//   public void hoodadjust() {
//     double currentDistance = m_ultrasonic.getValue() * kValueToInches;
//     double kdistancetoheight = 20; //random number
//     HAL.hoodie.moveto(currentDistance*kdistancetoheight);
//   }
// }