package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import java.util.function.DoubleSupplier;
import frc.robot.commands.*;
//import frc.robot.commands.FollowTrajectory;
import frc.robot.ninjaLib.Gamepad;
import frc.robot.subsystems.*;

public class OI {
 
   /////////////////////
  // XBOX CONTROLLER //
  /////////////////////

  public static Gamepad driver = new Gamepad(0);
  
  // DRIVE
  public static DoubleSupplier turn = () -> (driver.getRightX());
  public static DoubleSupplier throttle = () -> -(driver.getLeftY()); 
  public static DoubleSupplier clockManual = () -> (driver.getLeftTrigger());

  // SUBSYSTEM CONTROLS

//   public static Button twelve = new JoystickButton(driver, Gamepad.BUTTON_Y);
//   public static Button three = new JoystickButton(driver, Gamepad.BUTTON_B);
//   public static Button six = new JoystickButton(driver, Gamepad.BUTTON_A);
//   public static Button nine = new JoystickButton(driver, Gamepad.BUTTON_X);

  public OI(){

  
  }
}
