package frc.robot.ninjaLib;

public class TrajectoryUtil
{
    /*
    * Parameter distance: units in inches
    * Parameter ballVelocity: units are inches/per second
    * Return: the desired hood angle in degrees
    */
    public static double getHoodAngle(double distance, double ballVelocity)
    {
        final double g = 39.3701;
        return Math.toDegrees(Math.asin((2*g*distance)/Math.pow(ballVelocity, 2)));
    }

    /*
    * Parameter distance: units in inches
    * Parameter hoodAngle: units in degrees
    * Return: the desired ball velocity in inches/per second
    */
    public static double getDesiredBallVelocity(double distance, double hoodAngle)
    {
        final double g = 39.3701;
        return Math.sqrt((2*g*distance)/(Math.sin(Math.toRadians(2*hoodAngle))));
    }

    public static double getDesiredMotorOutput(double kP, double desiredBallVelocity)
    {
        return kP;
    }

    public static double getDesiredHoodTicks(double hoodAngle)
    {
        final double k = 0.0;
        return k * hoodAngle;
    }
}