/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Add your docs here.
 */
public class OI {
    private Joystick left = new Joystick(RobotMap.OI_LEFT_ID);
    private Joystick right = new Joystick(RobotMap.OI_RIGHT_ID);
    
    public double getLeftJoystick() {
        return Math.signum(left.getY()) * Math.pow(left.getY(), 2);
    }
    
    public double getRightJoystick() {
        return Math.signum(right.getY()) * Math.pow(right.getY(), 2);
    }
}
