/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Climber_Subsystem;

public class Move_Hook extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Climber_Subsystem m_subsystem;
    
    /**
     * Creates a new Move_Hook.
     */
    public Move_Hook(Climber_Subsystem subsystem) {
        // Use addRequirements() here to declare subsystem dependencies.
        m_subsystem = subsystem;

        addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //boolean LB_Pressed = RobotContainer.j_stick_control_LB.getButton
    double leftJoyY = RobotContainer.j_stick_control.getRawAxis(Constants.AXIS_LEFT_Y);
    leftJoyY = leftJoyY * 0.6;

    m_subsystem.Move_Hook(leftJoyY);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
