package frc.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import java.util.function.DoubleSupplier;

import frc.robot.commands.*;
import frc.robot.ninjaLib.Gamepad;
import frc.robot.ninjaLib.StatefulSubsystem;
import frc.robot.subsystems.*;
import static frc.robot.HAL.shoota;
import static frc.robot.HAL.feeda;;

public class OI {

  public static Gamepad driver = new Gamepad(0);

  
  public static DoubleSupplier turn = () -> (driver.getRightX());
  public static DoubleSupplier throttle = () -> -(driver.getLeftY()); 

  // DRIVE (SWITCH)
  // public static DoubleSupplier turn = () -> (driver.getRightTrigger());
  // public static DoubleSupplier throttle = () -> -(driver.getLeftY());

 // SUBSYSTEM CONTROLS

  public static Button bigshoota = new JoystickButton(driver, Gamepad.BUTTON_Y);  
  public static Button lilshoota = new JoystickButton(driver, Gamepad.BUTTON_A); 
  
  public static Button turretaRight = new JoystickButton(driver, Gamepad.BUTTON_B);
  public static Button turretaLeft = new JoystickButton(driver, Gamepad.BUTTON_X);

  public static Button we_eatin = new JoystickButton(driver, Gamepad.BUTTON_SHOULDER_LEFT);  
  public static Button we_spittin = new JoystickButton(driver, Gamepad.BUTTON_SHOULDER_RIGHT);  


  public OI(){
    bigshoota.whenPressed(shoota.setStateCommand(Shoota.State.BIGSHOOTA,Shoota.State.COOLIN, false));
    lilshoota.whenPressed(shoota.setStateCommand(Shoota.State.LILSHOOTA,Shoota.State.COOLIN, false));

    turretaRight.whileHeld(new HoldTurreta());


    we_eatin.whenPressed(feeda.setStateCommand(Feeda.State.EATIN, Feeda.State.VIBIN, false));
    we_spittin.whenPressed(feeda.setStateCommand(Feeda.State.SPITTIN, Feeda.State.VIBIN, false));
  }
}
