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
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  private TalonSRX shooterMaster = new TalonSRX(Constants.SHOOTER_SHOOTER_MASTER_ID);
  private VictorSPX shooterFollower = new VictorSPX(Constants.SHOOTER_SHOOTER_FOLLOWER_ID);

  private int setPoint = 125000;

  public Shooter() {
    shooterMaster.configFactoryDefault();
    shooterFollower.configFactoryDefault();

    shooterMaster.setNeutralMode(NeutralMode.Coast);
    shooterFollower.setNeutralMode(NeutralMode.Coast);

    shooterMaster.setInverted(false);
    shooterMaster.setSensorPhase(true);

    shooterFollower.setInverted(true);
    shooterFollower.follow(shooterMaster);
  }

  public void maintainRPM() {
    if(shooterMaster.getSelectedSensorVelocity() < setPoint){
      shooterMaster.set(ControlMode.PercentOutput, 1);
    } else {
      shooterMaster.set(ControlMode.PercentOutput, .85);
    }
  }

  public void stopMotors(){
    shooterMaster.set(ControlMode.PercentOutput, 0);
  }

  public boolean getAtSpeed(){
    return (shooterMaster.getSelectedSensorVelocity() > 120000);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
