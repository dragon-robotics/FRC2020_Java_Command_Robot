/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.RobotContainer;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter_Subsystem extends SubsystemBase {
  /**
   * Creates a new Shooter_Subsystem.
   */
  private final CANSparkMax shooter_left = new CANSparkMax(Constants.SPARKMAX_SHOOT_1, MotorType.kBrushless);
  private final CANSparkMax shooter_right = new CANSparkMax(Constants.SPARKMAX_SHOOT_2, MotorType.kBrushless);
  //private final CANEncoder shootEncoder_left

  public Shooter_Subsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void Shooter_Shoot_Joy(double shoot_speed) {
    shooter_left.set(shoot_speed); 
    shooter_right.set(shoot_speed);

  }
  public void Shooter_Shoot_Button() {
    shooter_left.set(1); 
    shooter_right.set(1);
  }

  public void Shooty_McShoot_Face_PID() {

  }

  public void Stop_Shoot() {
    shooter_left.set(0);
    shooter_right.set(0);
  }
}
