/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber_Subsystem extends SubsystemBase {
  /**
   * Creates a new Climber_Subsystem.
   */
  private final TalonSRX Winch_1 = new TalonSRX(1);
  private final TalonSRX Winch_2 = new TalonSRX(2);

  public Climber_Subsystem() {

  }

  public void Climb_Up() {
    Winch_1.set(ControlMode.PercentOutput, 1.0);
    Winch_2.set(ControlMode.PercentOutput, -1.0);
  }

  public void Climb_Down() {
    Winch_1.set(ControlMode.PercentOutput, -1.0);
    Winch_2.set(ControlMode.PercentOutput, 1.0);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
