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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  private TalonSRX shooterMaster = new TalonSRX(Constants.SHOOTER_SHOOTER_MASTER_ID);
  private VictorSPX shooterFollower = new VictorSPX(Constants.SHOOTER_SHOOTER_FOLLOWER_ID);

  private int setPoint = 75000;
  private int normal = 75000;
  private int close = 85000;
  private int far = 95000;
  private int deadband = 1000;

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

  public void testMotors(){
    shooterMaster.set(ControlMode.PercentOutput, 1);
  }

  public boolean maintainRPM(double d) {
    setShooterMode(d);
    if(shooterMaster.getSelectedSensorVelocity() < setPoint){
      shooterMaster.set(ControlMode.PercentOutput, 1);
    } else {
      shooterMaster.set(ControlMode.PercentOutput, .65);
    }
    putFlywheelSpeed();
    return shooterMaster.getSelectedSensorVelocity() > setPoint-deadband;
  }

  public void putFlywheelSpeed(){
    SmartDashboard.putNumber("Flywheel Speed", shooterMaster.getSelectedSensorVelocity());
  }

  public void setShooterMode(double y){
    if(y > .5 ){
      setPoint = far;
    } else if(y < .5) {
      setPoint = close;
    } else {
      setPoint = normal;
    }

  }

  public void putShooterMode(){
  }

  public void manualUp(){
    //this code is unregulated and dumb...but who knows whether its even going to be used.
    //setPoint += 1000;
  }

  public void manualDown(){
    //this code is unregulated and dumb...but who knows whether its even going to be used.
    //setPoint -= 1000;
  }

  public void stopMotors(){
    shooterMaster.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
