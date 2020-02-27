/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.test;

/**
 * Add your docs here.
 */
public abstract class AbstractMotor<M> implements Motor {
    private String name;
    private Integer canId;
    protected M motorController;

    public AbstractMotor(String name, Integer canId, M motorController){
        this.name = name;
        this.canId = canId;
        this.motorController = motorController;
    }

    public String getName() {
        return name;
    }

    public Integer getCanId() {
        return canId;
    }

    public abstract void setPower(double power);

    protected abstract String getMotorType();

    public void stop() {
        setPower(0);
    }

    public String displayInfo() {
        return String.format("Motor %s of type %s on CAN Id %d", name, getMotorType(), canId);
    }
}
