/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DecreaseMotorSpeed;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.IncreaseMotorSpeed;
import frc.robot.commands.MaintainRPM;
import frc.robot.commands.Autonomous.DriveForward;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private Shooter shooter;
  private Drivetrain drivetrainSubsystem;

  private DriveForward driveForwardCommand;
  private DriveWithJoysticks driveWithJoysticksCommand;

  private JoystickButton decreaseMotorSpeedButton;
  private JoystickButton increaseMotorSpeedButton;
  private JoystickButton maintainRPMButton;

  private Joystick leftJoystick;
  private Joystick rightJoystick;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    //Subsystems
    drivetrainSubsystem = new Drivetrain();
    shooter = new Shooter();


    //commands
    driveForwardCommand = new DriveForward(drivetrainSubsystem);
    driveWithJoysticksCommand = new DriveWithJoysticks(leftJoystick,rightJoystick,drivetrainSubsystem);
    defineButtons();

    // Configure the button bindings
    configureButtonBindings();

    System.out.println(RobotProperties.getProperty("name"));
    
  }

  private void defineButtons() {
    leftJoystick = new Joystick(Constants.LEFT_JOYSTICK_PORT_ID);
    rightJoystick = new Joystick(Constants.RIGHT_JOYSTICK_PORT_ID);

    decreaseMotorSpeedButton = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_DECREASE_MOTOR_SPEED);
    increaseMotorSpeedButton = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_INCREASE_MOTOR_SPEED);
    maintainRPMButton = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_MAINTAIN_RPM);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    drivetrainSubsystem.setDefaultCommand(driveWithJoysticksCommand);
    decreaseMotorSpeedButton.whenPressed(new DecreaseMotorSpeed(shooter));
    increaseMotorSpeedButton.whenPressed(new IncreaseMotorSpeed(shooter));
    maintainRPMButton.whenPressed(new MaintainRPM(shooter)); 
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return driveForwardCommand;
  }
}
