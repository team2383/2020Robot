package frc.robot;

import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.SPI;
import com.kauailabs.navx.frc.AHRS;

public class HAL {

    public static Drivetrain drive = new Drivetrain();
    public static Shoota shoota = new Shoota();
    public static AHRS navX = new AHRS(SPI.Port.kMXP);
    
}
