/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.Align_PID;
import frc.robot.commands.Align_Profiled_PID;
import frc.robot.commands.Arcade_Drive;
import frc.robot.commands.Tank_Drive;
import frc.robot.commands.Climb_Down;
import frc.robot.commands.Climb_Up;
import frc.robot.commands.Compressor_Start;
import frc.robot.commands.Find_Target;
import frc.robot.commands.Indexer_Stop;
import frc.robot.commands.Indexer_To_Intake;
import frc.robot.commands.Indexer_To_Shooter;
import frc.robot.commands.Intake_Deploy;
import frc.robot.commands.Intake_PowerCell;
import frc.robot.commands.Intake_Retract;
import frc.robot.commands.Intake_Stop;
import frc.robot.commands.Move_Hook;
import frc.robot.commands.Outtake_PowerCell;
import frc.robot.commands.Set_LED;
import frc.robot.commands.Shoot_Joy;
import frc.robot.commands.Stop_Climb;
import frc.robot.commands.Compressor_Stop;
import frc.robot.subsystems.Climber_Subsystem;
import frc.robot.subsystems.Compressor_Subsystem;
import frc.robot.subsystems.DriveTrain_Subsystem;
import frc.robot.subsystems.Intake_Subsystem;
import frc.robot.subsystems.Limelight_Subsystem;
import frc.robot.subsystems.NavXIMU_Subsystem;
import frc.robot.subsystems.Shooter_Subsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...

    // Subsystems //
    private final DriveTrain_Subsystem m_driveTrainSubsystem = new DriveTrain_Subsystem();
    // private final Climber_Subsystem m_climberSubsystem = new Climber_Subsystem();
    private final Shooter_Subsystem m_shooterSubsystem = new Shooter_Subsystem();
    private final Intake_Subsystem m_intakeSubsystem = new Intake_Subsystem();
    private final Compressor_Subsystem m_compressorSubsystem = new Compressor_Subsystem();
    // private final Limelight_Subsystem n_limelightSubsystem = new Limelight_Subsystem();

    // DriveTrain Commands //
    private final Arcade_Drive m_arcadeDriveCommand = new Arcade_Drive(m_driveTrainSubsystem);
    
    // Compressor Commands //
    private final Compressor_Start m_Compressor_Start = new Compressor_Start(m_compressorSubsystem);
    private final Compressor_Stop m_Compressor_Stop = new Compressor_Stop(m_compressorSubsystem);
    
    // Shooter Commands //
    private final Shoot_Joy m_Shoot_Joy = new Shoot_Joy(m_shooterSubsystem);
    
    // Intake/Indexer Commands //
    private final Intake_PowerCell m_Intake_PowerCell = new Intake_PowerCell(m_intakeSubsystem);
    private final Outtake_PowerCell m_Outtake_PowerCell = new Outtake_PowerCell(m_intakeSubsystem);
    private final Intake_Stop m_Intake_Stop = new Intake_Stop(m_intakeSubsystem);
    private final Intake_Deploy m_Intake_Deploy = new Intake_Deploy(m_intakeSubsystem);
    private final Intake_Retract m_Intake_Retract = new Intake_Retract(m_intakeSubsystem);
    private final Indexer_To_Intake m_Indexer_To_Intake = new Indexer_To_Intake(m_intakeSubsystem);
    private final Indexer_To_Shooter m_Indexer_To_Shooter = new Indexer_To_Shooter(m_intakeSubsystem);
    private final Indexer_Stop m_Indexer_Stop = new Indexer_Stop(m_intakeSubsystem);

    // private final Move_Hook m_Move_Hook = new Move_Hook(m_climberSubsystem);
    // private final Climb_Up m_climbUpCommand = new Climb_Up(m_climberSubsystem);
    // private final Climb_Down m_climbDownCommand = new Climb_Down(m_climberSubsystem);
    // private final Stop_Climb m_stopClimbCommand = new Stop_Climb(m_climberSubsystem);
    // private final Shooter_Shoot_Button m_Shooter_Shoot_Button = new Shooter_Shoot_Button(m_shooterSubsystem);
    // private final Piston_Intake_Out p_Intake_Out = new Piston_Intake_Out(m_intakeSubsystem);
    // private final Piston_Intake_In p_Intake_In = new Piston_Intake_In(m_intakeSubsystem);
    // private final Find_Target n_Find_Target = new Find_Target(n_limelightSubsystem, m_driveTrainSubsystem);
    // private final Set_LED n_Set_LED = new Set_LED(n_limelightSubsystem);
    // private final Align_Profiled_PID n_Align_PID = new Align_Profiled_PID(m_driveTrainSubsystem, n_limelightSubsystem);


    // Commands for Autonomous Period //
    private final Arcade_Drive m_autoCommand = new Arcade_Drive(m_driveTrainSubsystem);

    // Controller Mappings //

    /* Robot Driver - AKA Driver 1 */
    public static final Joystick j_stick_driver = new Joystick(0);  // Drive joystick (0) initialization
    public static final JoystickButton j_stick_driver_LB = new JoystickButton(j_stick_driver, Constants.BUTTON_RIGHT);              // Left button
    public static final JoystickButton j_stick_driver_RB = new JoystickButton(j_stick_driver, Constants.BUTTON_LEFT);               // Right button 
    public static final JoystickButton j_stick_driver_X = new JoystickButton(j_stick_driver, Constants.BUTTON_X);                   // X button
    public static final JoystickButton j_stick_driver_Y = new JoystickButton(j_stick_driver, Constants.BUTTON_Y);                   // Y button
    public static final JoystickButton j_stick_driver_A = new JoystickButton(j_stick_driver, Constants.BUTTON_A);                   // A button
    public static final JoystickButton j_stick_driver_B = new JoystickButton(j_stick_driver, Constants.BUTTON_B);                   // B button
    public static final JoystickButton j_stick_driver_Back = new JoystickButton(j_stick_driver, Constants.BUTTON_BACK);             // Back button
    public static final JoystickButton j_stick_driver_Start = new JoystickButton(j_stick_driver, Constants.BUTTON_START);           // Start button
    public static final JoystickButton j_stick_driver_leftStick = new JoystickButton(j_stick_driver, Constants.BUTTON_LEFTSTICK);   // Left-Stick button
    public static final JoystickButton j_stick_driver_rightStick = new JoystickButton(j_stick_driver, Constants.BUTTON_RIGHTSTICK); // Right-Stick button

    /* Robot Controller - AKA Driver 2 */
    public static final Joystick j_stick_control = new Joystick(1);  // Control joystick (1) initialization
    public static final JoystickButton j_stick_control_LB = new JoystickButton(j_stick_control, Constants.BUTTON_RIGHT);              // Left button
    public static final JoystickButton j_stick_control_RB = new JoystickButton(j_stick_control, Constants.BUTTON_LEFT);               // Right button 
    public static final JoystickButton j_stick_control_X = new JoystickButton(j_stick_control, Constants.BUTTON_X);                   // X button
    public static final JoystickButton j_stick_control_Y = new JoystickButton(j_stick_control, Constants.BUTTON_Y);                   // Y button
    public static final JoystickButton j_stick_control_A = new JoystickButton(j_stick_control, Constants.BUTTON_A);                   // A button
    public static final JoystickButton j_stick_control_B = new JoystickButton(j_stick_control, Constants.BUTTON_B);                   // B button
    public static final JoystickButton j_stick_control_Back = new JoystickButton(j_stick_control, Constants.BUTTON_BACK);             // Back button
    public static final JoystickButton j_stick_control_Start = new JoystickButton(j_stick_control, Constants.BUTTON_START);           // Start button
    public static final JoystickButton j_stick_control_leftStick = new JoystickButton(j_stick_control, Constants.BUTTON_LEFTSTICK);   // Left-Stick button
    public static final JoystickButton j_stick_control_rightStick = new JoystickButton(j_stick_control, Constants.BUTTON_RIGHTSTICK); // Right-Stick button

    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button bindings //
        configureButtonBindings();
        
        // Default Command(s) //
        m_driveTrainSubsystem.setDefaultCommand(m_arcadeDriveCommand);  // Defaults to Arcade Drive
        
        m_shooterSubsystem.setDefaultCommand(m_Shoot_Joy);
        
        m_compressorSubsystem.setDefaultCommand(m_Compressor_Stop);
        
        m_intakeSubsystem.setDefaultCommand(m_Intake_Stop);

        // m_climberSubsystem.setDefaultCommand(m_stopClimbCommand);    // Defaults to climber not running
        // m_climberSubsystem.setDefaultCommand(m_Move_Hook);              // Defaults to climber not running
        // m_intakeSubsystem.setDefaultCommand(m_Intake_Stop);
        // n_limelightSubsystem.setDefaultCommand(n_Set_LED);
    }

    /**
     * Use this method to define your button->command mappings.  Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        // j_stick_control_A.whenHeld(m_climbUpCommand);
        // j_stick_control_A.whenReleased(m_stopClimbCommand);

        j_stick_control_leftStick.whenPressed(m_Compressor_Start);
        j_stick_control_leftStick.whenReleased(m_Compressor_Stop);

        j_stick_control_A.whenHeld(m_Intake_PowerCell);
        j_stick_control_A.whenReleased(m_Intake_Stop);

        j_stick_control_B.whenHeld(m_Outtake_PowerCell);
        j_stick_control_B.whenReleased(m_Intake_Stop);

        j_stick_control_X.whenPressed(m_Intake_Deploy);
        j_stick_control_Y.whenPressed(m_Intake_Retract);

        j_stick_control_LB.whenHeld(m_Indexer_To_Intake);
        j_stick_control_LB.whenReleased(m_Indexer_Stop);

        j_stick_control_RB.whenHeld(m_Indexer_To_Shooter);
        j_stick_control_RB.whenReleased(m_Indexer_Stop);

        // j_stick_control_X.whenPressed(p_Intake_Out);
        // j_stick_control_Y.whenPressed(p_Intake_In);

        // j_stick_driver_A.whenHeld(n_Find_Target);
        // j_stick_driver_A.whenReleased(n_Set_LED);

        // j_stick_driver_B.whenHeld(n_Align_PID);
        // j_stick_driver_B.whenReleased(n_Set_LED);
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ArcadeDriveCommand will run in autonomous
        return m_autoCommand;
    }
}
