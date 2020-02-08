/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake_Subsystem extends SubsystemBase {
  /**
   * Creates a new Intake_Subsystem.
   */
  private final TalonSRX intake = new TalonSRX(Constants.TALONSRX_INTAKE);
  private final TalonSRX intake_2 = new TalonSRX(Constants.TALONSRX_INTAKE_2);

  private final DoubleSolenoid piston_1 = new DoubleSolenoid(Constants.CAN_DOUBLESOLENOID_1_LEFT, Constants.CAN_DOUBLESOLENOID_1_RIGHT);
  private final DoubleSolenoid piston_2 = new DoubleSolenoid(Constants.CAN_DOUBLESOLENOID_2_LEFT, Constants.CAN_DOUBLESOLENOID_2_RIGHT);
  public Intake_Subsystem() {

  }

  public void Intake() {
    intake.set(ControlMode.PercentOutput, -1);
    intake_2.set(ControlMode.PercentOutput, -1);

  }
  public void Intake_Stop() {
    intake.set(ControlMode.PercentOutput, 0);
    intake_2.set(ControlMode.PercentOutput, 0);
  }
  public void Piston_Intake_Out() {
    piston_1.set(Value.kForward);
    piston_2.set(Value.kForward);
  }
  public void Piston_Intake_In() {
    piston_1.set(Value.kReverse);
    piston_2.set(Value.kReverse);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
