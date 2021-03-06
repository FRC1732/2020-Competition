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

public class Climber extends SubsystemBase {
  /**
   * Creates a new Climber.
   */
  private TalonSRX climberRight;
  private TalonSRX climberLeft;
  private Solenoid brakingSolenoid;
  private double leftSet = .5;
  private double rightSet = .5;


  public Climber() {
    climberRight = new TalonSRX(Constants.CLIMBER_RIGHT_ID);
    climberLeft = new TalonSRX(Constants.CLIMBER_LEFT_ID);
    climberLeft.configFactoryDefault();
    climberRight.configFactoryDefault();
    climberLeft.setNeutralMode(NeutralMode.Brake);
    climberRight.setNeutralMode(NeutralMode.Brake);
    climberLeft.setInverted(true);
    brakingSolenoid = new Solenoid(Constants.CLIMBER_BRAKING_SOLENOID);
    setBrakeEnabled();

    
    
  }

  // Sets the right climber motor
  public void setRight(double right) {
    climberRight.set(ControlMode.PercentOutput, right);
  }
  // Sets the left climber motor 
  public void setLeft(double left) {
    climberLeft.set(ControlMode.PercentOutput, left);
  }

  // public void voltageDrive(){
  //   if(climberLeft.getMotorOutputVoltage() > climberRight.getMotorOutputVoltage()){
  //     rightSet *= getProportion(climberLeft.getMotorOutputVoltage(), climberRight.getMotorOutputVoltage());
  //   } else {
  //     leftSet *= getProportion(climberRight.getMotorOutputVoltage(), climberLeft.getMotorOutputVoltage());
  //   }
  // }

  public void setBrakeEnabled(){
    brakingSolenoid.set(false);
  }

  public void setBrakeDisabled(){
    brakingSolenoid.set(true);
  }

  public void manualUp(){
    setRight(rightSet);
    setLeft(leftSet);
  }

  public void manualDown(){
    setRight(-rightSet);
    setLeft(-leftSet);
  }
  
  private double getProportion(double high, double low){
    return high/low;
  }

  public void stopMotors(){
    setLeft(0);
    setRight(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
