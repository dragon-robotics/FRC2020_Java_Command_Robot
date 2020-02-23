/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Indexer_Subsystem extends SubsystemBase {
  /**
   * Creates a new Indexer_Subsystem.
   */

  private final WPI_TalonSRX indexerTop = new WPI_TalonSRX(Constants.TALONSRX_INDEXER_TOP);
  private final WPI_TalonSRX indexerBottom = new WPI_TalonSRX(Constants.TALONSRX_INDEXER_BOT);

  public Indexer_Subsystem() {
    // Set top indexer to follow bottom indexer //
    indexerBottom.follow(indexerTop, FollowerType.PercentOutput);
    indexerBottom.setInverted(InvertType.OpposeMaster);
  }

  public void Indexer_To_Shooter() {
    indexerTop.set(ControlMode.PercentOutput, 1);
  }

  public void Indexer_To_Intake() {
    indexerTop.set(ControlMode.PercentOutput, -1);
  }

  public void Indexer_Stop() {
    indexerTop.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
