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
import frc.robot.commands.ToggleLimelightLEDS;
import frc.robot.commands.ToggleLimelightVisionMode;
import frc.robot.commands.Autonomous.DriveForward;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;

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
  private Drivetrain drivetrain;
  private Vision vision;

  private DriveForward driveForward;
  private DriveWithJoysticks driveWithJoysticks;

  private JoystickButton decreaseMotorSpeed;
  private JoystickButton increaseMotorSpeed;
  private JoystickButton maintainRPM;
  private JoystickButton toggleLEDS;
  private JoystickButton toggleVisionMode;

  private Joystick leftJoystick;
  private Joystick rightJoystick;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    //Subsystems
    vision = new Vision();
    drivetrain = new Drivetrain();
    shooter = new Shooter();


    //commands
    driveForward = new DriveForward(drivetrain);
    driveWithJoysticks = new DriveWithJoysticks(leftJoystick,rightJoystick,drivetrain);

    defineButtons();

    // Configure the button bindings
    configureButtonBindings();

    System.out.println(RobotProperties.getProperty("name"));
    
  }

  private void defineButtons() {
    leftJoystick = new Joystick(Constants.LEFT_JOYSTICK_PORT_ID);
    rightJoystick = new Joystick(Constants.RIGHT_JOYSTICK_PORT_ID);

    decreaseMotorSpeed = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_DECREASE_MOTOR_SPEED);
    increaseMotorSpeed = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_INCREASE_MOTOR_SPEED);
    maintainRPM = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_MAINTAIN_RPM);

    toggleLEDS = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_TOGGLE_LIMELIGHT_LEDS);
    toggleVisionMode = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_TOGGLE_VISION_MODE);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    drivetrain.setDefaultCommand(driveWithJoysticks);
    decreaseMotorSpeed.whenPressed(new DecreaseMotorSpeed(shooter));
    increaseMotorSpeed.whenPressed(new IncreaseMotorSpeed(shooter));
    maintainRPM.whenPressed(new MaintainRPM(shooter)); 
    toggleLEDS.whenPressed(new ToggleLimelightLEDS(vision));
    toggleVisionMode.whenPressed(new ToggleLimelightVisionMode(vision));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return driveForward;
  }
}
