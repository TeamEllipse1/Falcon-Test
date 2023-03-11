// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
//import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  final NeutralMode kBreakNeutral = NeutralMode.Coast;

  private Joystick joy1 = new Joystick(0);
  TalonFX mTalon = new TalonFX(5, "rio");

  PIDController pid = new PIDController(0.1, 0, 0);
  //in raw sensor units
  double setpoint1 = 0;
  double setpoint2 = 0;
  double setpoint3 = 0;
  double speed = 0;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    mTalon.set(TalonFXControlMode.PercentOutput,0);
    mTalon.getBaseID();
    
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {

  }

  @Override
  public void teleopPeriodic() {
    //joystick
    mTalon.set(TalonFXControlMode.PercentOutput, 0.6 * joy1.getRawAxis(1));

    //pid
   SmartDashboard.putNumber("encoder", mTalon.getSelectedSensorPosition(0));

   if(joy1.getRawButtonPressed(0)){
    speed = pid.calculate(setpoint1);
   }

   mTalon.set(TalonFXControlMode.PercentOutput, speed);
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
