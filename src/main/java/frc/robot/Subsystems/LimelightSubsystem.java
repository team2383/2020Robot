package frc.robot.Subsystems;


import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.HAL;


public class LimelightSubsystem {

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
  
  public void setPipeline(double pipeline) {
		NetworkTableEntry pipelineEntry = NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline");
    	pipelineEntry.setNumber(pipeline);
    }


}