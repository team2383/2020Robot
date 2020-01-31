package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import frc.robot.HAL;


/**
 * This is a sample program demonstrating how to use an ultrasonic sensor and
 * proportional control to maintain a set distance from an object.
 */

public class Ultrasonic {
  
  // factor to convert sensor values to a distance in inches
  private static final double kValueToInches = 0.125;

  // proportional speed constant
  private final AnalogInput m_ultrasonic = new AnalogInput(10); //random number

  public void hoodadjust() {
    double currentDistance = m_ultrasonic.getValue() * kValueToInches;
    double kdistancetoheight = 20; //random number
    HAL.hoodie.moveto(currentDistance*kdistancetoheight);
  }
}