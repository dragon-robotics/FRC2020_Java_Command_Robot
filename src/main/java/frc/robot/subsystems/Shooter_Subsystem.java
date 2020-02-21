/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.RobotContainer;
import frc.robot.Constants;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter_Subsystem extends SubsystemBase {
  /**
   * Creates a new Shooter_Subsystem.
   */
  private String tabTitle;
  private ShuffleboardTab shooterTab;
  private SimpleWidget motor_position_1;
  private SimpleWidget motor_velocity_1;
  private SimpleWidget motor_velocityConversionFactor_1;
  private SimpleWidget motor_position_2;
  private SimpleWidget motor_velocity_2;
  private SimpleWidget motor_velocityConversionFactor_2;

  private final CANSparkMax shooter = new CANSparkMax(Constants.SPARKMAX_SHOOT_1, MotorType.kBrushless);
  private final CANSparkMax shooter_1 = new CANSparkMax(Constants.SPARKMAX_SHOOT_2, MotorType.kBrushless);

  private final CANEncoder shooterEncoder = new CANEncoder(shooter);
  private final CANEncoder shooterEncoder_1 = new CANEncoder(shooter_1);
  public Shooter_Subsystem() {
    tabTitle = "shooterTab";
    shooterTab = Shuffleboard.getTab(tabTitle);
  }
  public void DisplayNavXData() {
    motor_position_1 = shooterTab.add("position", shooterEncoder.getPosition());
    motor_velocity_1 = shooterTab.add("velocity", shooterEncoder.getVelocity());
    motor_velocityConversionFactor_1 = shooterTab.add("velocityConversionFactor", shooterEncoder.getVelocityConversionFactor());
  //  motor_position_2 = shooterTab.add("position", shooterEncoder_1.getPosition());
  //  motor_velocity_2 = shooterTab.add("velocity", shooterEncoder_1.getVelocity());
   // motor_velocityConversionFactor_2 = shooterTab.add("velocityConversionFactor", shooterEncoder_1.getVelocityConversionFactor());
  
  
  }
 /* public void UpdateNavXData() {
    motor_position_1 = shooterEncoder.getPosition().getDouble();
    motor_velocity_1()
    motor_velocityConversionFactor_1.setDouble(shooterEncoder.getVelocityConversionFactor());
  } */
  @Override
  public void periodic() {
    
    // This method will be called once per scheduler run
  }
  public void Shooter_Shoot_Joy(double shoot_speed) {
    shooter.set(shoot_speed); 
    shooter_1.set(shoot_speed);

  }
  public void Shooter_Shoot_Button() {
    shooter.set(1);
    shooter_1.set(1); 
  }

  public void Stop_Shoot() {
    shooter.set(0);
    shooter_1.set(0);
  }
}
