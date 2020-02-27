/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  /**
   * Creates a new Climber.
   */
  private TalonSRX climberRight;
  private TalonSRX climberLeft;
  private double UP_SPEED = .5;
  private double DOWN_SPEED = -.25;

  public Climber() {
    climberRight = new TalonSRX(Constants.CLIMBER_RIGHT_ID);
    climberLeft = new TalonSRX(Constants.CLIMBER_LEFT_ID);

    climberLeft.configFactoryDefault();
    climberRight.configFactoryDefault();
    
  }

  //need button to control each motor
  /**
   * @param climberLeft the climberLeft to set
   */

  // Sets the right climber motor
  public void setRight(double right) {
    climberRight.set(ControlMode.PercentOutput, right);
  }
  // Sets the left climber motor 
  public void setLeft(double left) {
    climberLeft.set(ControlMode.PercentOutput, left);
  }

  public void manualUp(){
    setRight(UP_SPEED);
    setLeft(UP_SPEED);
  }

  public void manualDown(){
    setRight(DOWN_SPEED);
    setLeft(DOWN_SPEED);
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
