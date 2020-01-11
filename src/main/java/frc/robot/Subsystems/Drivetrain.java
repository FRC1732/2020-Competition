/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.Commands.DriveWithJoysticks;

public class Drivetrain extends SubsystemBase {
  /**
   * Creates a new Drivetrain.
   */
  private CANSparkMax leftMaster;
  private CANSparkMax left1;
  private CANSparkMax left2;
  private CANSparkMax rightMaster;
  private CANSparkMax right1;
  private CANSparkMax right2;
  
  public Drivetrain() {
    
    leftMaster = new CANSparkMax(RobotMap.DRIVETRAIN_LEFTMASTER_ID, MotorType.kBrushless);
    left1 = new CANSparkMax(RobotMap.DRIVETRAIN_LEFT1_ID, MotorType.kBrushless);
    left2 = new CANSparkMax(RobotMap.DRIVETRAIN_LEFT2_ID, MotorType.kBrushless);
    rightMaster = new CANSparkMax(RobotMap.DRIVETRAIN_RIGHTMASTER_ID, MotorType.kBrushless);
    right1 = new CANSparkMax(RobotMap.DRIVETRAIN_RIGHT1_ID, MotorType.kBrushless);
    right2 = new CANSparkMax(RobotMap.DRIVETRAIN_RIGHT2_ID, MotorType.kBrushless);
    
    left1.follow(leftMaster);
    left2.follow(leftMaster);
    right1.follow(rightMaster);
    right2.follow(rightMaster);
    
  }
  
  public void set(double left, double right) {
    leftMaster.set(left);
    rightMaster.set(right);
  }
  
  public void stop() {
    this.set(0, 0);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
}
