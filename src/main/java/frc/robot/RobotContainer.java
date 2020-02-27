/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.List;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.test.Motor;
import frc.robot.subsystems.test.MotorCollection;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // Driver Joysticks
  private Joystick leftJoystick;
  private JoystickButton motorTest;
  private JoystickButton killIt;

  private int index;
  private boolean direction;
  private int cycle;
  private List<Motor> motors;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Subsystems
    MotorCollection motorCollection = new MotorCollection();
    motors = motorCollection.getMotors();
    cycle = 0;

    // Define Buttons
    defineButtons();

    // Configure the button bindings
    configureButtonBindings();
  }

  private void defineButtons() {
    // Joystick declaration
    leftJoystick = new Joystick(Constants.LEFT_JOYSTICK_PORT_ID);

    motorTest = new JoystickButton(leftJoystick, 1);
    killIt = new JoystickButton(leftJoystick, 2);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */

  private void configureButtonBindings() {
    // leftJoystick button configuration
    killIt.whenPressed(new InstantCommand(this::exitNow));

    motorTest.whenPressed(new InstantCommand(this::startMotor)).whenReleased(this::stopMotor);
  }

  private void startMotor() {
    if (cycle == motors.size() * 2) {
      cycle = 0;
    }

    index = cycle / 2;
    direction = (cycle % 2 == 1);

    Motor motor = motors.get(index);
    if(direction) {
      System.out.println("Starting 20% forward" + motor.displayInfo());
      motor.setPower(0.2);
    }else {
      System.out.println("Starting 20% backward" + motor.displayInfo());
      motor.setPower(-0.2);
    }

    cycle++;
  }

  private void stopMotor() {
    Motor motor = motors.get(index);
    System.out.println("Stopping " + motor.displayInfo());
    motor.stop();

    printNextUp();
  }

  private void printNextUp() {
    int next = index + 1;
    if(next >= motors.size()){
      next = 0;
    }

    Motor motor = motors.get(next);
    System.out.println("Next Motor is " + motor.displayInfo());
  }

  private void exitNow() {
    System.out.println("!!!!!!! System Abort !!!!!!");
    System.exit(-1);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {

    return new PrintCommand("Auto Mode goes here");
  }

}