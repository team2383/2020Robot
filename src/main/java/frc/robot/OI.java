package frc.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import java.util.function.DoubleSupplier;

import frc.robot.commands.*;
import frc.robot.ninjaLib.Gamepad;
import frc.robot.ninjaLib.StatefulSubsystem;
import frc.robot.subsystems.*;
import static frc.robot.HAL.shoota;


public class OI {

  public static Gamepad driver = new Gamepad(0);

  
  public static DoubleSupplier turn = () -> (driver.getRightX());
  public static DoubleSupplier throttle = () -> -(driver.getLeftY()); 

  // DRIVE (SWITCH)
  // public static DoubleSupplier turn = () -> (driver.getRightTrigger());
  // public static DoubleSupplier throttle = () -> -(driver.getLeftY());

 // SUBSYSTEM CONTROLS

  public static Button bigshoota = new JoystickButton(driver, Gamepad.BUTTON_Y);  public static Button three = new JoystickButton(driver, Gamepad.BUTTON_B);
  public static Button lilshoota = new JoystickButton(driver, Gamepad.BUTTON_A);  public static Button nine = new JoystickButton(driver, Gamepad.BUTTON_X);


  public OI(){
  bigshoota.whenPressed(shoota.setStateCommand(Shoota.State.BIGSHOOTA,Shoota.State.COOLIN, false));
  lilshoota.whenPressed(shoota.setStateCommand(Shoota.State.LILSHOOTA,Shoota.State.COOLIN, false));
  }
}
