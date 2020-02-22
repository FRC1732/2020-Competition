/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
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
    shooterMaster.setSensorPhase(true);

    shooterFollower.setInverted(true);
    shooterFollower.follow(shooterMaster);
  }
  // prints motor speed 
  public void printMotorVelocity(){
    System.out.println(shooterMaster.getSelectedSensorVelocity());
  }
  // sets the solenoid 
  public void setAdjustmentSolenoid(Solenoid adjustmentSolenoid) {
    this.adjustmentSolenoid = adjustmentSolenoid;
  }
  // sets the rotational solenoid 
  public void setRotationSolenoid(Solenoid rotationSolenoid) {
    this.rotationSolenoid = rotationSolenoid;
  }

  public void testMotors(){
    shooterMaster.set(ControlMode.PercentOutput, 30);
    System.out.println("Inverted| "+shooterFollower.getInverted());
  }

  //increaseMotorSpeed is only to be used for testing
  public void increaseMotorSpeed(){
    motorSpeed += 0.01;
    motorSpeed = motorSpeed >= 1.00 ? 1 : motorSpeed;
    System.out.println("Motor ++ | " + motorSpeed);
    setCurrentMotorSpeed();
  }

  //decreaseMotorSpeed is only to be used for testing
  public void decreaseMotorSpeed(){
    motorSpeed -= 0.01;
    motorSpeed = motorSpeed <= -1.00 ? -1 : motorSpeed;
    System.out.println("Motor -- | " + motorSpeed);
    setCurrentMotorSpeed();
  }

  public void setCurrentMotorSpeed(){
    shooterMaster.set(ControlMode.PercentOutput, motorSpeed);
  }

  public void maintainRPM() {
    if(shooterMaster.getSelectedSensorVelocity() < 125000){
      shooterMaster.set(ControlMode.PercentOutput, 1);
    } else {
      shooterMaster.set(ControlMode.PercentOutput, .85);
    }
  }

  public void stopMotors(){
    shooterMaster.set(ControlMode.PercentOutput, 0);
  }

  public boolean getAtSpeed(){
    return (shooterMaster.getSelectedSensorPosition() < 125000);
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
