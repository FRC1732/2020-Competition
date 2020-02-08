/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  private TalonSRX shooterMaster = new TalonSRX(Constants.SHOOTER_SHOOTER_MASTER_ID);
  private TalonSRX shooterFollower = new TalonSRX(Constants.SHOOTER_SHOOTER_FOLLOWER_ID);
  private Solenoid adjustmentSolenoid = new Solenoid(Constants.SHOOTER_ADJUSTMENT_SOLENOID_ID);
  private Solenoid rotationSolenoid = new Solenoid(Constants.SHOOTER_ROTATION_SOLENOID_ID);

  private double motorSpeed = 0.0;

  public Shooter() {
    shooterMaster.configFactoryDefault();
    shooterFollower.configFactoryDefault();

    shooterMaster.setInverted(false);
    shooterMaster.setInverted(false);
    shooterMaster.config_kP(0, .12);
    shooterMaster.config_kI(0, 0);
    shooterMaster.config_kD(0, 0);
    shooterMaster.config_kF(0, .0078);
    shooterMaster.setNeutralMode(NeutralMode.Coast);

    shooterFollower.setInverted(true);
    shooterFollower.setNeutralMode(NeutralMode.Coast);
    shooterFollower.follow(shooterMaster);
  }

  public void printMotorVelocity(){
    System.out.println(shooterMaster.getSelectedSensorVelocity());
  }

  public void setAdjustmentSolenoid(Solenoid adjustmentSolenoid) {
    this.adjustmentSolenoid = adjustmentSolenoid;
  }
  
  public void setRotationSolenoid(Solenoid rotationSolenoid) {
    this.rotationSolenoid = rotationSolenoid;
  }

  public void increaseMotorSpeed(){
    motorSpeed += 0.01;
    motorSpeed = motorSpeed >= 1.00 ? 1 : motorSpeed;
    System.out.println("Motor ++ | " + motorSpeed);
    setCurrentMotorSpeed();
  }

  public void decreaseMotorSpeed(){
    motorSpeed -= 0.01;
    motorSpeed = motorSpeed <= -1.00 ? -1 : motorSpeed;
    System.out.println("Motor -- | " + motorSpeed);
    setCurrentMotorSpeed();
  }

  private void setCurrentMotorSpeed(){
    shooterMaster.set(ControlMode.PercentOutput, motorSpeed);
  }

  public void maintainRPM() {
    shooterMaster.set(ControlMode.Velocity, 86110);
  }

  public Solenoid getAdjustmentSolenoid() {
    return adjustmentSolenoid;
  }

  public Solenoid getRotationSolenoid() {
    return rotationSolenoid;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
