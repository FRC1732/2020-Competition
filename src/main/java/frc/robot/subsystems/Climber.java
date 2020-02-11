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
  private final double UP_SPEED = 0.5;
  private final double DOWN_SPEED = 0.25;

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

  //only use for testing

  public void setRight(double right) {
    climberRight.set(ControlMode.PercentOutput, right);
  }

  //only use for testing
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

  public void climb(){
    //input code to autoclimb here
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
