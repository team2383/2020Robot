package frc.robot;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.hal.sim.DriverStationSim;
import edu.wpi.first.wpilibj.DriverStation;


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
  }
  public void test() {
  }
}

