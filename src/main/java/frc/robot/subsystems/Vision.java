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
    ty = table.getEntry("tx");
    ledMode = table.getEntry("ledMode");
    camMode = table.getEntry("camMode");
  }

  public void setLightsEnabled(){
    ledMode.setNumber(1);
  }

  public void setLightsDisabled(){
    ledMode.setNumber(3);
  }

  private boolean getLights(){
    if(ledMode.getNumber(new Integer(-1)) == 3){
      return true;
    } else {
      return false;
    }
  }

  public void toggleLights(){

  }

  public void toggle(){
    ledMode.setNumber(1)
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
