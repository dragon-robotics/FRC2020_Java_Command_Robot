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
  private SimpleWidget shooterLeftPosition;
  private SimpleWidget shooterLeftVelocity;
  private SimpleWidget shooterLeftVelocityConversionFactor;
  private SimpleWidget shooterRightPosition;
  private SimpleWidget shooterRightVelocity;
  private SimpleWidget shooterRightVelocityConversionFactor;

  private final CANSparkMax shooterLeft = new CANSparkMax(Constants.SPARKMAX_SHOOTER_LEFT, MotorType.kBrushless);
  private final CANSparkMax shooterRight = new CANSparkMax(Constants.SPARKMAX_SHOOTER_RIGHT, MotorType.kBrushless);

  private final CANEncoder shooterLeftEncoder = new CANEncoder(shooterLeft);
  private final CANEncoder shooterRightEncoder = new CANEncoder(shooterRight);
  
  public Shooter_Subsystem() {
    tabTitle = "shooterTab";
    shooterTab = Shuffleboard.getTab(tabTitle);

    // Set the left shooter to inversely follow the right shooter //
    shooterRight.follow(shooterLeft, true);
  }

  public void DisplayNavXData() {
    shooterLeftPosition = shooterTab.add("position", shooterLeftEncoder.getPosition());
    shooterLeftVelocity = shooterTab.add("velocity", shooterLeftEncoder.getVelocity());
    shooterLeftVelocityConversionFactor = shooterTab.add("velocityConversionFactor", shooterLeftEncoder.getVelocityConversionFactor());
    shooterRightPosition = shooterTab.add("position", shooterRightEncoder.getPosition());
    shooterRightVelocity = shooterTab.add("velocity", shooterRightEncoder.getVelocity());
    shooterRightVelocityConversionFactor = shooterTab.add("velocityConversionFactor", shooterRightEncoder.getVelocityConversionFactor());
  }

 /* public void UpdateNavXData() {
    shooterLeftPosition = shooterEncoder.getPosition().getDouble();
    shooterLeftVelocity()
    shooterLeftVelocityConversionFactor.setDouble(shooterEncoder.getVelocityConversionFactor());
  } */

  @Override
  public void periodic() {
    
    // This method will be called once per scheduler run
  }
  public void Shoot_Variable(double shoot_speed) {
    shooterLeft.set(shoot_speed);
  }
  public void Shoot_Button() {
    shooterLeft.set(1);
  }

  public void Shoot_Stop() {
    shooterLeft.set(0);
  }
}
