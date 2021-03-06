/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Drivetrain;

/**
 * Add your docs here.
 */
public class ReverseDrivetrain extends InstantCommand {
  private Drivetrain drivetrain;

  /**
   * Add your docs here.
   */
  public ReverseDrivetrain(Drivetrain drivetrain) {
    super();
    addRequirements(drivetrain);
    this.drivetrain = drivetrain; 
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called once when the command executes
  @Override
  public void initialize() {
    final double SPEED = -0.2;
    drivetrain.set(SPEED, SPEED);
    if (drivetrain.getLeftEncoder() - drivetrain.getRightEncoder() > 1 || drivetrain.getLeftEncoder() - drivetrain.getRightEncoder() < -1){
      if(drivetrain.getLeftEncoder() > drivetrain.getRightEncoder()){
        drivetrain.set(SPEED - ((drivetrain.getLeftEncoder()-drivetrain.getRightEncoder())/100), SPEED);
      }
      else{
        drivetrain.set(SPEED, SPEED - ((drivetrain.getRightEncoder()-drivetrain.getLeftEncoder())/100));
      }
 }
  }

}
