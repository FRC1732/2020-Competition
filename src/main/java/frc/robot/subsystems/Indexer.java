/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Indexer extends SubsystemBase {
  private TalonSRX feeder;
  private VictorSPX conveyor;

  public Indexer() {
    feeder = new TalonSRX(Constants.INDEXER_FEEDER_ID);
    conveyor = new VictorSPX(Constants.INDEXER_CONVEYER_ID);

    feeder.setNeutralMode(NeutralMode.Coast);
    conveyor.setNeutralMode(NeutralMode.Coast);

    conveyor.setInverted(true);
    feeder.setInverted(true);
  }

  public void feedShooter(){
    feeder.set(ControlMode.PercentOutput, .75);
  }

  public void reverseFeedShooter(){
    feeder.set(ControlMode.PercentOutput, -75);
  }

  public void stopFeeder(){
    feeder.set(ControlMode.PercentOutput, 0);
  }

  public void forwardConveyor() {
    conveyor.set(ControlMode.PercentOutput, 1);
    System.out.println("Conveyor Voltage| "+conveyor.getMotorOutputVoltage());
  }

  public void reverseConveyor() {
    conveyor.set(ControlMode.PercentOutput, -1);
  }

  public void stopConveyor(){
    conveyor.set(ControlMode.PercentOutput, 0);
  }

  // we need a sensor here
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}