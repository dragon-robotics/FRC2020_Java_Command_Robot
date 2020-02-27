/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

  public class Indexer_Subsystem extends SubsystemBase {
  /*
   * Creates a new Elevator_Subsystem.
   */
  private CANSparkMax IndexerSparkMax_1 = new CANSparkMax(Constants.SPARKMAX_INDEXER_1, MotorType.kBrushless);
  private CANSparkMax IndexerSparkMax_2 = new CANSparkMax(Constants.SPARKMAX_INDEXER_2, MotorType.kBrushless);
  private CANSparkMax IndexerSparkMax_3 = new CANSparkMax(Constants.SPARKMAX_INDEXER_3, MotorType.kBrushless);

  public Indexer_Subsystem() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  } 
  public void Indexer_Motion_Joy(double direction) {
    IndexerSparkMax_1.set(direction);
    IndexerSparkMax_2.set(direction);
    IndexerSparkMax_3.set(direction);

  }

  public void Stop_Indexer() {
    IndexerSparkMax_1.set(0);
    IndexerSparkMax_2.set(0);
    IndexerSparkMax_3.set(0);
  }

  } 


