/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class DrivetrainOn extends InstantCommand {
  private Drivetrain drivetrain;

  public DrivetrainOn(Drivetrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
    this.drivetrain = drivetrain;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    final double SPEED = 0.2;
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
