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

  // private final DoubleSolenoid piston_left = new DoubleSolenoid(Constants.DOUBLESOLENOID_INTAKE_LEFT, Constants.DOUBLESOLENOID_INTAKE_LEFT);
  // private final DoubleSolenoid piston_right = new DoubleSolenoid(Constants.DOUBLESOLENOID_INTAKE_RIGHT, Constants.DOUBLESOLENOID_INTAKE_RIGHT);
  
  public Intake_Subsystem() {

  }

  public void Outtake_PowerCell(){
    intake.set(ControlMode.PercentOutput, 0.5);
  }

  public void Intake_PowerCell() {
    intake.set(ControlMode.PercentOutput, -0.5);
  }
  
  public void Intake_Stop() {
    intake.set(ControlMode.PercentOutput, 0);
  }
  
  // public void Intake_Deploy() {
  //   piston_left.set(Value.kForward);
  //   piston_right.set(Value.kForward);
  // }
  
  // public void Intake_Retract() {
  //   piston_left.set(Value.kReverse);
  //   piston_right.set(Value.kReverse);
  // }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
