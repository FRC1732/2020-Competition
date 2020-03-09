/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ControlPanel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanel;

public class PositionControl extends CommandBase {
  ControlPanel controlPanel;
  /**
   * Creates a new PositionControl.
   */
  public PositionControl(ControlPanel controlPanel) {
    addRequirements(controlPanel);
    this.controlPanel = controlPanel;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    controlPanel.ProcessGameData();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    controlPanel.spinMotor();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    controlPanel.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return controlPanel.atPosition();
  }
}
