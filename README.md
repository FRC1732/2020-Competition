# 2020-Competition
This is the official repository for FRC Team 1732's main robot code for FIRST Infinite Recharge.

## Important (online) vendor library URLs

Using the command palette within VS Code, use `WPILib: Manage Vendor Libraries` and select `Install new libraries (online)` and provide the URLs below

- CTRE/Phoenix: http://devsite.ctr-electronics.com/maven/release/com/ctre/phoenix/Phoenix-latest.json
- REVRobotics: https://www.revrobotics.com/content/sw/max/sdk/REVRobotics.json
- NavX: https://www.kauailabs.com/dist/frc/2020/navx_frc.json

- NEW WPILib Dependency: https://raw.githubusercontent.com/wpilibsuite/allwpilib/master/wpilibNewCommands/WPILibNewCommands.json

## Repository

Before editing files in this repository, read [CONTRIBUTING.md](CONTRIBUTING.md).

## Code

The code herein follows the WPILIB Command Robot pattern, and uses a single code formatting. Please follow these patterns, and practicies when creating commits, otherwise they will not be accepted into the main branch.

### Formatting

The offical formatting is defined in eclipse-formatter.xml. Just use `Shift-Alt-F` or `Format Document` from the command palette to automatically format an open .java file.

## Git Config
Because Java classes are case-sensitive to the file name, Git does not pick up on case file name changes, you need to enter the following command in your terminal

`git config core.ignorecase false`

## Specific Files - DO NOT MODIFY

The following files should not be modified in anyway:

* Main.java (code may not run when deployed)
* build.gradle (is for the gradle build script)
* settings.gradle (sets settings for the gradle build system)
* .gitignore (specifies what file types are ignored when pushed/pulled using GIT)
* eclipse-formatter.xml (defines default java code style)

## Project type

This season the project will utilize the new Command-Based structure as defined here: https://docs.wpilib.org/en/latest/docs/software/commandbased/index.html

This differs from the Command-Based structure in the past which uses OI and RobotMap. 

### Constants

Constants can be found in RobotMap.java. The RobotMap is intended to provide constants in a single place, e.g. CAN IDs, and other identifying information.
This may be updated later to specify parameters via an INI file to be FTP'd to the RoboRio memory

### Subsystems

Subsystems interface with either hardware, either through the WPILib library, or other similar methods. Another way to think of subsystems it that they store and change the physical state of the robot.

Subsystems may also just read from sensors. In these cases, the subsystem is just converting the signals read in from sensors into a more useful format (e.g. encoder ticks to inches).

Some subsystems have a default command, a command to run when no other command requires them.

### Commands

Commands read data from subsystems, and use logic and math to decide on a target state for the subsystem. 

### Operator Input

OI.java has all of the joystick and button inputs. Commands shouldn't use or access Joystick objects directly, but instead just call the appropriate method in OI.java.
