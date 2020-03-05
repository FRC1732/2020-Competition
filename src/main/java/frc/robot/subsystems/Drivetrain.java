/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import com.revrobotics.AlternateEncoderType;
import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  // creates the motor objects 
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
    
    initializeEncodersStuff();
  }

  public void initializeMotorControllers(){
    // assignes the motor objects to a value
    leftMaster = new CANSparkMax(Constants.DRIVETRAIN_LEFTMASTER_ID, MotorType.kBrushless);
    left1 = new CANSparkMax(Constants.DRIVETRAIN_LEFT1_ID, MotorType.kBrushless);
    left2 = new CANSparkMax(Constants.DRIVETRAIN_LEFT2_ID, MotorType.kBrushless);

    rightMaster = new CANSparkMax(Constants.DRIVETRAIN_RIGHT2_ID, MotorType.kBrushless);
    right1 = new CANSparkMax(Constants.DRIVETRAIN_RIGHTMASTER_ID, MotorType.kBrushless);
    right2 = new CANSparkMax(Constants.DRIVETRAIN_RIGHT1_ID, MotorType.kBrushless);

    

   
    leftMaster.restoreFactoryDefaults();
    left1.restoreFactoryDefaults();
    left2.restoreFactoryDefaults();
 

    rightMaster.restoreFactoryDefaults();
    right1.restoreFactoryDefaults();
    right2.restoreFactoryDefaults();
    // Sets the motors to inverted. 
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
    // Sets the motors to follow the master 
    left1.follow(leftMaster);
    left2.follow(leftMaster);

    right1.follow(rightMaster);
    right2.follow(rightMaster);    

    leftMaster.burnFlash();
    left1.burnFlash();
    left2.burnFlash();

    rightMaster.burnFlash();
    right1.burnFlash();
    right2.burnFlash();
    
    leftMaster.getEncoder().setPosition(0);
    rightMaster.getEncoder().setPosition(0);
    
    leftMaster.setOpenLoopRampRate(0.5);
    rightMaster.setOpenLoopRampRate(0.5);

    leftMaster.setSmartCurrentLimit(20);
    rightMaster.setSmartCurrentLimit(30);
    right1.setSmartCurrentLimit(30);
    right2.setSmartCurrentLimit(30);
    left1.setSmartCurrentLimit(20);
    left2.setSmartCurrentLimit(20);

    
  }
  // starts the motors
  public void set(double left, double right) {
    leftMaster.set(left);
    rightMaster.set(right);
  }
  // Stops the motors from 
  public void stop() {
    leftMaster.set(0);
	  rightMaster.set(0);
  }
  public double getLeftEncoder(){
    return leftMaster.getEncoder().getPosition();
  }
  public double getRightEncoder(){
    return rightMaster.getEncoder().getPosition();
  }
  public double getAlternateRightEncoder() {
    return rightMaster.getAlternateEncoder(AlternateEncoderType.kQuadrature,256).getPosition();
  }
  public double getAlternateLeftEncoder(){
    return leftMaster.getAlternateEncoder(AlternateEncoderType.kQuadrature,256).getPosition();
  }
  public boolean getForwardRightLimitSwitch(){
    return rightMaster.getForwardLimitSwitch(CANDigitalInput.LimitSwitchPolarity.kNormallyOpen).get();
  }
  public boolean getForwardLeftLimitSwitch(){
    return leftMaster.getForwardLimitSwitch(CANDigitalInput.LimitSwitchPolarity.kNormallyOpen).get();
  }
  public boolean getReverseRightLimitSwitch(){
    return rightMaster.getReverseLimitSwitch(CANDigitalInput.LimitSwitchPolarity.kNormallyOpen).get();
  }
  public boolean getReverseLeftLimitSwitch() {
    return leftMaster.getReverseLimitSwitch(CANDigitalInput.LimitSwitchPolarity.kNormallyOpen).get();
  }
  private void initializeEncodersStuff(){
    Shuffleboard.getTab("SmartDashboard").addNumber("Left Encoder", leftEncoderSupplier);
    Shuffleboard.getTab("SmartDashboard").addNumber("Right Encoder", rightEncoderSupplier);
    Shuffleboard.getTab("SmartDashboard").addNumber("Alternate Left", leftAlternateEncoderSupplier);
    Shuffleboard.getTab("SmartDashboard").addNumber("Alternate Right", rightAlternateEncoderSupplier);
    Shuffleboard.getTab("SmartDashboard").addBoolean("Forward Left Limit Switch", ForwardLeftLimitSwitch);
    Shuffleboard.getTab("SmartDashboard").addBoolean("Forward Right Limit Switch", ForwardRightLimitSwitch);
    Shuffleboard.getTab("SmartDashboard").addBoolean("Reverse Left Limit Switch", ReverseLeftLimitSwitch);
    Shuffleboard.getTab("SmartDashboard").addBoolean("Reverse Right Limit Switch", ReverseRightLimitSwitch);
    
  }
  
  BooleanSupplier ForwardLeftLimitSwitch =  new BooleanSupplier(){
    @Override
    public boolean getAsBoolean() {
      // TODO Auto-generated method stub
      return getForwardLeftLimitSwitch();
    }
  };
  BooleanSupplier ForwardRightLimitSwitch =  new BooleanSupplier(){
    @Override
    public boolean getAsBoolean() {
      // TODO Auto-generated method stub
      return getForwardRightLimitSwitch();
    }
  };
  BooleanSupplier ReverseLeftLimitSwitch =  new BooleanSupplier(){
    @Override
    public boolean getAsBoolean() {
      // TODO Auto-generated method stub
      return getReverseLeftLimitSwitch();
    }
  };
  BooleanSupplier ReverseRightLimitSwitch =  new BooleanSupplier(){
    @Override
    public boolean getAsBoolean() {
      // TODO Auto-generated method stub
      return getReverseRightLimitSwitch();
    }
  };
  DoubleSupplier leftAlternateEncoderSupplier =  new DoubleSupplier(){
    @Override
    public double getAsDouble() {
      // TODO Auto-generated method stub
      return getAlternateLeftEncoder();
    }
  };
  DoubleSupplier rightAlternateEncoderSupplier =  new DoubleSupplier(){
    @Override
    public double getAsDouble() {
      // TODO Auto-generated method stub
      return getAlternateRightEncoder();
    }
  };
  DoubleSupplier leftEncoderSupplier =  new DoubleSupplier(){
  
    @Override
    public double getAsDouble() {
      // TODO Auto-generated method stub
      return getLeftEncoder();
    }
  };
  DoubleSupplier rightEncoderSupplier =  new DoubleSupplier(){
  
    @Override
    public double getAsDouble() {
      // TODO Auto-generated method stub
      return getRightEncoder();
    }
  };
  public void resetEncoders(){
    leftMaster.getEncoder().setPosition(0);
    rightMaster.getEncoder().setPosition(0);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
