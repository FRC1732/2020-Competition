/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  private CANSparkMax shooter = new CANSparkMax(Constants.SHOOTER_SHOOTER_ID, MotorType.kBrushless);
  private Solenoid adjustmentSolenoid = new Solenoid(Constants.SHOOTER_ADJUSTMENT_SOLENOID_ID);
  private Solenoid rotationSolenoid = new Solenoid(Constants.SHOOTER_ROTATION_SOLENOID_ID);

  public Shooter() {

  }
  public void set(double speed) {
    shooter.set(speed);
  }

  public void setAdjustmentSolenoid(Solenoid adjustmentSolenoid) {
    this.adjustmentSolenoid = adjustmentSolenoid;
  }
  
  public void setRotationSolenoid(Solenoid rotationSolenoid) {
    this.rotationSolenoid = rotationSolenoid;
  }

  public double getShooter() {
    return shooter.get()*1;
    //Add code to get speed
  }

  public Solenoid getAdjustmentSolenoid() {
    return adjustmentSolenoid;
  }

  public Solenoid getRotationSolenoid() {
    return rotationSolenoid;
  }

  public boolean atSpeed() {
    return false;
  }
  
  public void shootBall() {
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
