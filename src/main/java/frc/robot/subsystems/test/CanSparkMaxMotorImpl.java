/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.test;

import com.revrobotics.CANSparkMax;

/**
 * Add your docs here.
 */
public class CanSparkMaxMotorImpl extends AbstractMotor<CANSparkMax> implements Motor {

    public CanSparkMaxMotorImpl(String name, Integer canId, CANSparkMax motorController) {
        super(name, canId, motorController);
    }

    @Override
    public void setPower(double power) {
        motorController.set(power);
    }

    @Override
    protected String getMotorType() {
        return "Spark Max";
    }
}
