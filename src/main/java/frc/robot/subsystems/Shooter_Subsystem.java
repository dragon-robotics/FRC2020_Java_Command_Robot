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
  private SimpleWidget motorPositionLeft;
  private SimpleWidget motorVelocityLeft;
  private SimpleWidget motorVelocityConversionFactorLeft;
  private SimpleWidget motorPositionRight;
  private SimpleWidget motorVelocityRight;
  private SimpleWidget motorVelocityConversionFactorRight;

  private final CANSparkMax shooterLeft = new CANSparkMax(Constants.SPARKMAX_SHOOT_1, MotorType.kBrushless);
  private final CANSparkMax shooterRight = new CANSparkMax(Constants.SPARKMAX_SHOOT_2, MotorType.kBrushless);

  private final CANEncoder shooterEncoderLeft = new CANEncoder(shooterLeft);
  private final CANEncoder shooterEncoderRight = new CANEncoder(shooterRight);
  public Shooter_Subsystem() {
    tabTitle = "shooterTab";
    shooterTab = Shuffleboard.getTab(tabTitle);
  }
  public void DisplayNavXData() {
    motorPositionLeft = shooterTab.add("position", shooterEncoderLeft.getPosition());
    motorVelocityLeft = shooterTab.add("velocity", shooterEncoderLeft.getVelocity());
    motorVelocityConversionFactorLeft = shooterTab.add("velocityConversionFactor", shooterEncoderLeft.getVelocityConversionFactor());
  //  motorPositionRight = shooterTab.add("position", shooterEncoderRight.getPosition());
  //  motorVelocityRight = shooterTab.add("velocity", shooterEncoderRight.getVelocity());
   // motorVelocityConversionFactorRight = shooterTab.add("velocityConversionFactor", shooterEncoderRight.getVelocityConversionFactor());
  
  
  }
 /* public void UpdateNavXData() {
    motorPositionLeft = shooterEncoder.getPosition().getDouble();
    motorVelocityLeft()
    motorVelocityConversionFactorLeft.setDouble(shooterEncoder.getVelocityConversionFactor());
  } */
  @Override
  public void periodic() {
    
    // This method will be called once per scheduler run
  }
  public void Shooter_Shoot_Joy(double shoot_speed) {
    shooterLeft.set(shoot_speed); 
    shooterRight.set(shoot_speed);

  }
  public void Shooter_Shoot_Button() {
    shooterLeft.set(1); 
    shooterRight.set(1);
  }

  public void Shooty_McShoot_Face_PID() {

  }

  public void Stop_Shoot() {
    shooterLeft.set(0);
    shooterRight.set(0);
  }
}
