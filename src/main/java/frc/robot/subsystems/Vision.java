/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase {
  private NetworkTable table; 
  private NetworkTableEntry tv; 
  private NetworkTableEntry tx; 
  private NetworkTableEntry ty;
  private NetworkTableEntry ledMode; 
  private NetworkTableEntry camMode; 
  
  /**
   * Creates a new Vision.
   */
  public Vision() {
    table = NetworkTableInstance.getDefault().getTable("limelight");
    tv = table.getEntry("tv");
    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    ledMode = table.getEntry("ledMode");
    camMode = table.getEntry("camMode");
  }

  public double calculateDistance(){
    return 77.75/Math.tan(ty.getDouble(0));
  }

  //turns off the limelight LEDs
  //this should only be called by toggleLed
  private void setLedOff(){
    ledMode.setNumber(1);
  }

  //turns on the limelight LEDs
  //this should only be called by toggleLed
  private void setLedOn(){
    ledMode.setNumber(3);
  }

  //this returns a 1 if the LEDs are off or a 3 if the LEDs are on
  //once again this is used by toggleLed
  private double getLedStatus(){
    return ledMode.getDouble(-1);
  }    

  //using the previous three methods, this toggles the LEDs between on and oof
  public void toggleLed(){
    if(getLedStatus() == 1){
      setLedOn();
    } else {
      setLedOff();
    }

  }

  //returns true if the limelight sees a target or false is the limelight doesn't see a target
  public boolean hasTarget(){
    if(tv.getDouble(0) == 1){
      return true;
    } else {
      return false;
    }
  }

  //Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
  public double getX(){
    return tx.getDouble(0);
  }

  //Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
  public double getY(){
    return ty.getDouble(0);
  }

  //sets the limelight to function as a vision processing camera
  //this is used by toggleVisionMode
  public void setModeLimelight(){
    camMode.setDouble(0);
  }

  //sets the limelight to function as a driver vision camera
  //this is used by toggleVisionMode
  public void setModeCamera(){
    camMode.setDouble(1);
  }

  //returns whether the limelight is functioning as a limelight or a vision camera
  //this is used by toggleVisionMode
  public double getVisionMode(){
    return camMode.getDouble(-1);
  }
  
  //toggles the limelight mode between functioning as a driver vision camera and a limelight
  public void toggleVisionMode(){
    if(getVisionMode() == 0){
      setModeCamera();
    } else {
      setModeLimelight();
    }
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
