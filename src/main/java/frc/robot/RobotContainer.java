/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import javax.print.attribute.standard.JobHoldUntil;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DecreaseMotorSpeed;
import frc.robot.commands.IncreaseMotorSpeed;
import frc.robot.commands.MaintainRPM;
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

  private final Shooter shooter;

  private final DecreaseMotorSpeed decreaseMotorSpeed;
  private final IncreaseMotorSpeed increaseMotorSpeed;
  private final MaintainRPM maintainRPM;

  public static Joystick left;
  public static Joystick right;

  public static JoystickButton decreaseMotorSpeedButton;
  public static JoystickButton increaseMotorSpeedButton;
  public static JoystickButton maintainRPMButton;


  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    shooter = new Shooter();

    decreaseMotorSpeed = new DecreaseMotorSpeed(shooter);
    increaseMotorSpeed = new IncreaseMotorSpeed(shooter);
    maintainRPM = new MaintainRPM(shooter);

    left = new Joystick(Constants.JOYSTICK_LEFT);
    right = new Joystick(Constants.JOYSTICK_RIGHT);

    decreaseMotorSpeedButton = new JoystickButton(left, Constants.JOYSTICKBUTTON_DECREASE_MOTOR_SPEED);
    increaseMotorSpeedButton = new JoystickButton(left, Constants.JOYSTICKBUTTON_INCREASE_MOTOR_SPEED);
    maintainRPMButton = new JoystickButton(left, Constants.JOYSTICKBUTTON_MAINTAIN_RPM);


    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    decreaseMotorSpeedButton.whenPressed(decreaseMotorSpeed);
    increaseMotorSpeedButton.whenPressed(increaseMotorSpeed);
    maintainRPMButton.whenPressed(maintainRPM); 
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
