package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class LimelightSubsystem extends Subsystem {

  private double get(String name) {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry(name).getDouble(0);
  }

  public boolean hasTargets() {
    return get("tv") > 0;
  }

  public double pipeline() {
    return get("getpipe");
  }

  public double xOffset() {
    return get("tx");
  }

  public double yOffset() {
    return get("ty");
  }

  public double area() {
    return get("ta");
  }

  public double skew() {
    return get("ts");
  }

  public double latency() {
    return get("tl");
  }

  public double shortSideLength() {
    return get("tshort");
  }

  public double longSideLength() {
    return get("tlong");
  }

  public double horizontalLength() {
    return get("thoriz");
  }

  public double verticalLength() {
    return get("tvert");
  }

  public double camtran()
  {
    return get("camtran");
  }

  @Override
  public void initDefaultCommand() {
  }

  public void setPipeline(double pipeline) {
		NetworkTableEntry pipelineEntry = NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline");
    	pipelineEntry.setNumber(pipeline);
    }

  public void periodic(){
  SmartDashboard.putNumber("Area", area());
  SmartDashboard.putNumber("xOffset", xOffset());
}
}