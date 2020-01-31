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
import frc.robot.Constants;

public class Indexer extends SubsystemBase {
    final double speed = 1;
    

    public Indexer() {
      private VictorSPX cellHolder1 = new VictorSPX(Constants.INDEXER_CELLHOLDER1_ID);
      private VictorSPX cellHodler2 = new VictorSPX(Constants.INDEXER_CELLHOLDER2_ID);
      private VictorSPX conveyor = new VictorSPX(Constants.INDEXER_CONVEYER_ID);
      private Solenoid cellGate = new Solenoid(Constants.INDEXER_CELLGATE_ID);
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
