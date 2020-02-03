package frc.robot;

//import edu.wpi.first.hal.HAL;
import edu.wpi.first.hal.sim.DriverStationSim;
import edu.wpi.first.wpilibj.DriverStation;

import frc.robot.HAL;
import static frc.robot.Subsystems.Hood.hoodc;
import static frc.robot.Subsystems.Motor.motor;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class. If you change the name
 * of this class or the package after creating this project, you must also
 * update the build.gradle file in the project.
 */
public class Robot extends TimedRobot{
  public static Loop mill = new Loop();
  OI oi = new OI();
  public void robotInit() {

  }

  public void disabled() {
  }

  public void autonomous() {
  }

  public void teleopInit() {
    SmartDashboard.putNumber("Is on", 10);
    }

  public void teleopPeriodic(){
    mill.seed();
    oi.listener();
    SmartDashboard.putBoolean("Ready to Shoot?", HAL.hood.isangledY());
    SmartDashboard.putNumber("Hpos", hoodc.getSelectedSensorPosition());
    SmartDashboard.putNumber("Mpos", motor.getSelectedSensorPosition());
    SmartDashboard.putBoolean("Greater than 2000", motor.getSelectedSensorPosition() > 2000);
 
  }
  public void test() {
  }
}

