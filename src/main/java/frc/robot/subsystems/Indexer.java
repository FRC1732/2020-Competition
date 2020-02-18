/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants;

public class Indexer extends SubsystemBase {
  private final double speed = 1;
  private TalonSRX cellHolder1;
  private TalonSRX conveyor;
  private Solenoid cellGate;

  public Indexer() {
    cellHolder1 = new TalonSRX(Constants.INDEXER_CELLHOLDER1_ID);
    conveyor = new TalonSRX(Constants.INDEXER_CONVEYER_ID);
    cellGate = new Solenoid(Constants.INDEXER_CELLGATE_ID);
  }

  public void openCellGate(boolean extendGate) {
    cellGate.set(extendGate);
  }
  // starts the motor to feed the shooter
  public void feedShooter() {
    conveyor.set(ControlMode.PercentOutput, speed);
  }
  // starts the motor to get the cells away from the shooter
  public void reverseFeedShooter() {
    conveyor.set(ControlMode.PercentOutput, -speed);
  }

  // System.out.print("we need a sensor here");
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}