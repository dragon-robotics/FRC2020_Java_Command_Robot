/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Compressor_Subsystem extends SubsystemBase {
  /**
   * Creates a new Compressor_Subsystem.
   */
  private final Compressor compressor = new Compressor(0);
  public Compressor_Subsystem() {

  }
  public void Compressor_Start() {
    compressor.setClosedLoopControl(true);
  }
  public void Stop_Compressor() {
    compressor.setClosedLoopControl(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
