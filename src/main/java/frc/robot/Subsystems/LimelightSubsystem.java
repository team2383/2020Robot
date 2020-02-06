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

    public double getDistanceFromTarget()
    {
      //Units: Inches
      double h1 = 30; //need to confirm
      double h2 = 60; //need to confirm
      double netHeight = h2-h1;
      //Unit: Degrees
      double a1 = yOffset();
      double a2 = 30; //need to confirm
      double netAngle = a1+a2;
    
      double distance = netHeight/(Math.tan(netAngle));
    
      if(hasTargets() == true)
      {
        return distance;
      }
      else
      {
        return 0.0;
      }
    }



}