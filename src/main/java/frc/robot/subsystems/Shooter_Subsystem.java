/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.RobotContainer;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter_Subsystem extends SubsystemBase {
  /**
   * Creates a new Shooter_Subsystem.
   */
  private final TalonSRX shooter = new TalonSRX(Constants.TALONSRX_SHOOT);
  public Shooter_Subsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void Shooter_Shoot_Joy(double shoot_speed) {
    shooter.set(ControlMode.PercentOutput, shoot_speed);

  }
  public void Shooter_Shoot_Button() {
    shooter.set(ControlMode.PercentOutput, 1);
  }

  public void Stop_Shoot() {
    shooter.set(ControlMode.PercentOutput, 0);
  }
}
