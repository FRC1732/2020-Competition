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
import edu.wpi.first.wpilibj.Solenoid;

public class Indexer extends SubsystemBase {
    final double speed = 1;
    VictorSPX cellHolder1 = new VictorSPX(1);
    VictorSPX cellHodler2 = new VictorSPX(2);
    VictorSPX conveyor = new VictorSPX(3);
    Solenoid cellGate = new Solenoid(4);

    public Indexer() {

    }

    public void openCellGate(boolean extendGate) {
        cellGate.set(extendGate);
    }

    public void feedShooter() {
        conveyor.set(ControlMode.PercentOutput, speed);
    }

    public void reverseFeedShooter() {
        conveyor.set(ControlMode.PercentOutput, -speed);
    }
    // System.out.print("we need a sensor here");
    @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
