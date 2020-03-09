/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class TankDrive extends CommandBase {
  private Joystick leftJoystick;
  private Joystick rightJoystick;
  private Drivetrain drivetrain;
  // following doubles are for ramping
  // private final double MAX_STEP = .5;
  // private double previousValue;

  /**
   * Creates a new DriveWithJoysticks.
   * 
   * @param rightJoystick
   * @param leftJoystick
   * @param drivetrainSubsystem
   */
  public TankDrive(Joystick leftJoystick, Joystick rightJoystick, Drivetrain drivetrain) {
    addRequirements(drivetrain);
    this.leftJoystick = leftJoystick;
    this.rightJoystick = rightJoystick;
    this.drivetrain = drivetrain;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // public double ramp(double currentValue){
  //   if(Math.signum(previousValue) == Math.signum(currentValue)){
  //     if(currentValue - previousValue > MAX_STEP){
  //       return previousValue += MAX_STEP;
  //     } else {
  //       previousValue = currentValue;
  //       return currentValue;
  //     }
  //   }
  //   previousValue = currentValue;
  //   return currentValue;    
  // }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // gets the y value of the joysticks
    double left = leftJoystick.getY() * leftJoystick.getY() * Math.signum(leftJoystick.getY());
    double right = rightJoystick.getY() * rightJoystick.getY() * Math.signum(rightJoystick.getY());

    drivetrain.set(left,right);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}