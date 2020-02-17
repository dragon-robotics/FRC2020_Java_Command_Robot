/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Elevator_Subsystem extends SubsystemBase {
  /**
   * Creates a new Elevator_Subsystem.
   */
  private CANSparkMax elevatorSparkMax = new CANSparkMax(Constants.SPARKMAX_ELEVATOR_1, MotorType.kBrushless);


  public Elevator_Subsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
