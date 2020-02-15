/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  private CANSparkMax leftMaster;
  private CANSparkMax left1;
  private CANSparkMax left2;
  private CANSparkMax rightMaster;
  private CANSparkMax right1;
  private CANSparkMax right2;

  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {
    initializeMotorControllers();
  }

  public void initializeMotorControllers(){
    leftMaster = new CANSparkMax(Constants.DRIVETRAIN_LEFTMASTER_ID, MotorType.kBrushless);
    left1 = new CANSparkMax(Constants.DRIVETRAIN_LEFT1_ID, MotorType.kBrushless);
    left2 = new CANSparkMax(Constants.DRIVETRAIN_LEFT2_ID, MotorType.kBrushless);

    rightMaster = new CANSparkMax(Constants.DRIVETRAIN_RIGHTMASTER_ID, MotorType.kBrushless);
    right1 = new CANSparkMax(Constants.DRIVETRAIN_RIGHT1_ID, MotorType.kBrushless);
    right2 = new CANSparkMax(Constants.DRIVETRAIN_RIGHT2_ID, MotorType.kBrushless);

    leftMaster.restoreFactoryDefaults();
    left1.restoreFactoryDefaults();
    left2.restoreFactoryDefaults();

    rightMaster.restoreFactoryDefaults();
    right1.restoreFactoryDefaults();
    right2.restoreFactoryDefaults();

    leftMaster.setInverted(false);
    left1.setInverted(false);
    left2.setInverted(false);

    rightMaster.setInverted(true);
    right1.setInverted(true);
    right2.setInverted(true);

    leftMaster.setIdleMode(IdleMode.kBrake);
    rightMaster.setIdleMode(IdleMode.kBrake);
    left1.setIdleMode(IdleMode.kBrake);
    left2.setIdleMode(IdleMode.kBrake);
    right1.setIdleMode(IdleMode.kBrake);
    right2.setIdleMode(IdleMode.kBrake);

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
    leftMaster.set(0);
	  rightMaster.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
