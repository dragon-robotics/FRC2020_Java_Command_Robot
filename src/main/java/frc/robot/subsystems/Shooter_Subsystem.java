/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.RobotContainer;
import frc.robot.Constants;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.CAN;
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
  private NetworkTableEntry motor_position_1;
  private NetworkTableEntry motor_velocity_1;
  private NetworkTableEntry motor_velocityConversionFactor_1;
  private NetworkTableEntry motor_position_2;
  private NetworkTableEntry motor_velocity_2;
  private NetworkTableEntry motor_velocityConversionFactor_2;

  private final CANSparkMax shooterLeft = new CANSparkMax(Constants.SPARKMAX_SHOOT_1, MotorType.kBrushless);
  private final CANSparkMax shooterRight = new CANSparkMax(Constants.SPARKMAX_SHOOT_2, MotorType.kBrushless);

  private final CANEncoder shooterEncoder_Left = shooterLeft.getEncoder();
  private final CANEncoder shooterEncoder_Right = shooterRight.getEncoder();

  private final CANPIDController shooterPID_Left = shooterLeft.getPIDController();
  private final CANPIDController shooterPID_Right = shooterRight.getPIDController();

  public Shooter_Subsystem() {
    tabTitle = "shooterTab";
    shooterTab = Shuffleboard.getTab(tabTitle);
    shooterLeft.restoreFactoryDefaults();
    shooterRight.restoreFactoryDefaults();
    shooterLeft.set(0);
    shooterRight.set(0);
    shooterLeft.setIdleMode(IdleMode.kBrake);
    shooterRight.setIdleMode(IdleMode.kBrake);
    Reset_Encoders();
    DisplayShooterData();
    shooterPID_Left.setP(Constants.Shooter.kP);
    shooterPID_Left.setI(Constants.Shooter.kI);
    shooterPID_Left.setD(Constants.Shooter.kD);
    shooterPID_Left.setIZone(Constants.Shooter.kIz);
    shooterPID_Left.setFF(Constants.Shooter.kFF); //Feed Forward Value
    shooterPID_Left.setOutputRange(Constants.Shooter.kMinOutput, Constants.Shooter.kMaxOutput);
    shooterRight.follow(shooterLeft, true);
  }
  public void DisplayShooterData() {
    motor_position_1 = shooterTab.add("position_1", shooterEncoder_Left.getPosition()).getEntry();
    motor_velocity_1 = shooterTab.add("velocity_1", shooterEncoder_Left.getVelocity()).getEntry();
    motor_velocityConversionFactor_1 = shooterTab.add("velocityConversionFactor_1", shooterEncoder_Left.getVelocityConversionFactor()).getEntry();
    motor_position_2 = shooterTab.add("position_2", shooterEncoder_Right.getPosition()).getEntry();
    motor_velocity_2 = shooterTab.add("velocity_2", shooterEncoder_Right.getVelocity()).getEntry();
    motor_velocityConversionFactor_2 = shooterTab.add("velocityConversionFactor_2", shooterEncoder_Right.getVelocityConversionFactor()).getEntry();
  }

  // public void ChartShooterData(){
  //   /**
  //    * Chart the:
  //    *  1. Velocity of the motor
  //    *  2. Command Setpoint
  //    *  3. Applied output
  //    */

  //    shooterTab.add()
  // }

  public void UpdateShooterData() {
    motor_position_1.setDouble(shooterEncoder_Left.getPosition());
    motor_velocity_1.setDouble(shooterEncoder_Left.getVelocity());
    motor_velocityConversionFactor_1.setDouble(shooterEncoder_Left.getVelocityConversionFactor());
    motor_position_2.setDouble(shooterEncoder_Right.getPosition());
    motor_velocity_2.setDouble(shooterEncoder_Right.getVelocity());
    motor_velocityConversionFactor_2.setDouble(shooterEncoder_Right.getVelocityConversionFactor());
  } 
  public void Reset_Encoders() {
    shooterEncoder_Left.setPosition(0);
    shooterEncoder_Right.setPosition(0);
  }
  public double Get_Motor_Left_Velocity() {
    return shooterEncoder_Left.getVelocity();
  }
  public void Shoot_Joy(double shoot_speed) {
    shooterLeft.set(0); 
    shooterRight.set(0);
  }
  public void Shoot_Button() {
    double setPoint = Constants.Shooter.setPoint * Constants.Shooter.maxVel;
    shooterPID_Left.setReference(setPoint, ControlType.kVelocity);
  }

  public void Shoot_Stop() {
    shooterLeft.set(0);
    shooterRight.set(0);
  }
  @Override
  public void periodic() {
    UpdateShooterData();
    // This method will be called once per scheduler run
  }

  
}
