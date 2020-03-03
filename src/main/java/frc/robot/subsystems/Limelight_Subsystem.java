/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Limelight_Subsystem extends SubsystemBase {

  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  private String tabTitle;
  private ShuffleboardTab limelightTab;


  private NetworkTable get_x;
  private NetworkTable get_y;
  private NetworkTable get_a;
  private NetworkTable get_t; 

  public Limelight_Subsystem() {
    tabTitle = "LimelightTab";
    limelightTab = Shuffleboard.getTab(tabTitle);

    DisplayLimelightData();
  }

  /* To be completed */

  /* Contour Information - 1 if found, 0 is not */

  public void Find_Target() {

  }
  public void Align_Profiled_PID() {
    
  }

  public double Distance() {
    double distance = (Constants.Limelight.targetHeight - Constants.Limelight.limelightHeight) / Math.tan(Constants.Limelight.limelightAngle + Get_Y());
    return distance;
  }

  public double Get_Target() {
    double tv = table.getEntry("tv").getDouble(0);
    return tv;
  } 
  public double Get_X(){
    double tx = table.getEntry("tx").getDouble(2375);
    return tx;
  }

  public double Get_Y() {
    double ty = table.getEntry("ty").getDouble(2375);
    return ty;
  }

  public double Get_Area() {
    double ta = table.getEntry("ta").getDouble(2375);
    return ta;
  }

  public double Get_Skew(){
    double ts = table.getEntry("ts").getDouble(2375);
    return ts;
  }

  public double Get_Pipeline_Latency(){
    double tl = table.getEntry("tl").getDouble(2375);
    return tl;
  }

  public double Get_Sidelength_Of_Shortest_Side_Of_Fitted_Bounding_Box(){
    double tshort = table.getEntry("tshort").getDouble(2375);
    return tshort;
  }

  public double Get_Sidelength_Of_Longest_Side_Of_Fitted_Bounding_Box() {
    double tlong = table.getEntry("tlong").getDouble(2375);
    return tlong;
  }

  public double Get_Horizontal_Sidelength_Of_Rough_Bounding_Box() {
    double thor = table.getEntry("thor").getDouble(2375);
    return thor;
  }

  public double Get_Vertical_Sidelength_Of_Rough_Bounding_Box() {
    double tvert = table.getEntry("tvert").getDouble(2375);
    return tvert;
  }

  public double Get_Pipeline(){
    double getpipe = table.getEntry("getpipe").getDouble(2375);
    return getpipe;
  }

  /* Camera Controls */

  /*
   * Sets limelight’s LED state
   * 0 - use the LED Mode set in the current pipeline
   * 1 - force off
   * 2 - force blink
   * 3 - force on
   */
  public void Set_LED(int led_value){
    table.getEntry("ledMode").setNumber(led_value);
  }

  /*
   * Sets limelight’s operation mode
   * 0 - Vision Processor
   * 1 - Driver Camera (Increases Exposure, disables vision processing)
   */
  public void Set_Cam_Mode(int cam_mode) {
    table.getEntry("camMode").setNumber(cam_mode);
  }

  /*
   * Sets limelight’s current pipeline
   * 0..9 - Select pipeline 0..9
   */
  public void Set_Pipeline(int pipeline) {
    table.getEntry("pipeline").setNumber(pipeline);
  }

  /*
   * Sets limelight’s streaming mode
   * 0 - Standard - Side-by-side streams if a webcam is attached to Limelight 
   * 1 - PiP Main - The secondary camera stream is placed in the lower-right corner of the primary camera stream
   * 2 - PiP Secondary - The primary camera stream is placed in the lower-right corner of the secondary camera stream
   */
  public void Set_Stream(int stream){
    table.getEntry("stream").setNumber(stream);
  }

  /*
   * Allows users to take snapshots during a match
   * 0 - Stop taking snapshots
   * 1 - Take two snapshots per second
   */
  public void Set_Snapshot(int snapshot) {
    table.getEntry("snapshot").setNumber(snapshot);
  }

  /* Corners */

  /* Raw Targets */

  /* Raw CrossHair */
  public void periodic(double tx, double ty) {
   UpdateLimelightData();
  }

  private void DisplayLimelightData(){
    /* Contour information */
    
    limelightTab.add("tv", table.getEntry("tv").getDouble(0));
    limelightTab.add("tx", table.getEntry("tx").getDouble(0));
    limelightTab.add("ty", table.getEntry("ty").getDouble(0));
    limelightTab.add("ta", table.getEntry("ta").getDouble(0));
    limelightTab.add("ts", table.getEntry("ts").getDouble(0));
    limelightTab.add("tl", table.getEntry("tl").getDouble(0));
    limelightTab.add("tshort", table.getEntry("tshort").getDouble(0));
    limelightTab.add("tlong", table.getEntry("tlong").getDouble(0));
    limelightTab.add("thor", table.getEntry("thor").getDouble(0));
    limelightTab.add("tvert", table.getEntry("tvert").getDouble(0));
    limelightTab.add("getpipe", table.getEntry("getpipe").getDouble(0));
    limelightTab.add("camtran", table.getEntry("camtran").getDouble(0));
  
    /* Raw Contours */
    limelightTab.add("tx0", table.getEntry("tx0").getDouble(0));
    limelightTab.add("ty0", table.getEntry("ty0").getDouble(0));
    limelightTab.add("ta0", table.getEntry("ta0").getDouble(0));
    limelightTab.add("ts0", table.getEntry("ts0").getDouble(0));
    limelightTab.add("tx1", table.getEntry("tx1").getDouble(0));
    limelightTab.add("ty1", table.getEntry("ty1").getDouble(0));
    limelightTab.add("ta1", table.getEntry("ta1").getDouble(0));
    limelightTab.add("ts1", table.getEntry("ts1").getDouble(0));
    limelightTab.add("tx2", table.getEntry("tx2").getDouble(0));
    limelightTab.add("ty2", table.getEntry("ty2").getDouble(0));
    limelightTab.add("ta2", table.getEntry("ta2").getDouble(0));
    limelightTab.add("ts2", table.getEntry("ts2").getDouble(0));

    /* Raw Crosshair */
    limelightTab.add("cx0", table.getEntry("cx0").getDouble(0));
    limelightTab.add("cy0", table.getEntry("cy0").getDouble(0));
    limelightTab.add("cx1", table.getEntry("cx1").getDouble(0));
    limelightTab.add("cy1", table.getEntry("cy1").getDouble(0));
  }
  private void UpdateLimelightData() {
    limelightTab.add("tx", table.getEntry("tx").getDouble(0));
    // SmartDashboard.putNumber("LimelightY", Get_Y());
    // SmartDashboard.putNumber("LimelightArea", Get_Area());
    
  }
}
