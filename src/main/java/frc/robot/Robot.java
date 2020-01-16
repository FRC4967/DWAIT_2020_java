/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.solenoids;
import frc.robot.roboMap;
/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  /**
   * info on names of motors MMRight = the main motor on the right side LMRight =
   * the 1st lesser motor on the right RMRight = the 2nd lesser motor on the right
   * same applies for the left side
   */
  VictorSPX MMRight = new VictorSPX(3);
  VictorSPX MMLeft = new VictorSPX(6);

  TalonSRX LiftLeft = new TalonSRX(12);
  TalonSRX LiftRight = new TalonSRX(11);
  VictorSPX intakeLeft = new VictorSPX(7);
  BuiltInAccelerometer gyro = new BuiltInAccelerometer();
  VictorSPX intakeRight = new VictorSPX(8);
  solenoids sullie = new solenoids();
  DigitalInput back = new DigitalInput(9);
  DigitalInput front = new DigitalInput(8);
  roboMap mapped = new roboMap();
  AnalogInput blazeItDevice = new AnalogInput(3);
  UsbCamera vision;
  // there is a point with lift speed where its not enough to actually work below .3
  double liftSpeed = .36;
  //.36
  private Joystick m_Joystick;
  //the speed percent
  double speedVar = .5;
  //.6
  double intakeSpeed = .4;
  // .4
  @Override
  public void robotInit() {
    vision = CameraServer.getInstance().startAutomaticCapture(0); 
    m_Joystick = new Joystick(0);
    sullie.init();
    mapped.follow();
  }



  @Override
  public void teleopPeriodic() {
    SmartDashboard.putBoolean("front", front.get());
    SmartDashboard.putBoolean("back", back.get());
  

    SmartDashboard.putNumber("blazeItDevice", blazeItDevice.getVoltage());
    SmartDashboard.putNumber("gyroX", gyro.getX());
    SmartDashboard.putNumber("gyroZ", gyro.getZ());
    SmartDashboard.putNumber("gyroY", gyro.getY());


    double LeftSpeed = m_Joystick.getRawAxis(1);
    double Rightspeed = m_Joystick.getRawAxis(3);
    double RightSpeed = Rightspeed * -1;

    MMLeft.set(ControlMode.PercentOutput, LeftSpeed * speedVar);
    MMRight.set(ControlMode.PercentOutput, RightSpeed * speedVar);

    
  
    
    if(m_Joystick.getRawButton(9)== true && m_Joystick.getRawButton(5)==true){
      MMLeft.set(ControlMode.PercentOutput, speedVar);
      MMRight.set(ControlMode.PercentOutput, speedVar);
    }
    if(m_Joystick.getRawButton(9)== true && m_Joystick.getRawButton(6)==true){
      MMLeft.set(ControlMode.PercentOutput, speedVar * -1);
      MMRight.set(ControlMode.PercentOutput, speedVar * -1);
    }
    if(m_Joystick.getRawButton(9)== true && m_Joystick.getRawButton(8)==true){
      MMLeft.set(ControlMode.PercentOutput, speedVar);
      MMRight.set(ControlMode.PercentOutput, speedVar * -1);
    }
    if(m_Joystick.getRawButton(9)== true && m_Joystick.getRawButton(7)==true){
      MMLeft.set(ControlMode.PercentOutput, speedVar * -1);
      MMRight.set(ControlMode.PercentOutput, speedVar);
    }
    if(m_Joystick.getRawButton(5)== true && m_Joystick.getRawButton(9)== false && front.get() == true){
      LiftRight.set(ControlMode.PercentOutput, liftSpeed );
      LiftLeft.set(ControlMode.PercentOutput, liftSpeed * -1);
    }
    else if(m_Joystick.getRawButton(6)==true && m_Joystick.getRawButton(9)== false){
      LiftLeft.set(ControlMode.PercentOutput, liftSpeed);
      LiftRight.set(ControlMode.PercentOutput, liftSpeed * -1);
    }
    else{
      LiftLeft.set(ControlMode.PercentOutput, 0);
      LiftRight.set(ControlMode.PercentOutput, 0);
    }

    if(m_Joystick.getRawButton(7)==true && m_Joystick.getRawButton(9)== false){

      intakeLeft.set(ControlMode.PercentOutput, intakeSpeed);
      intakeRight.set(ControlMode.PercentOutput, intakeSpeed);

    }
    else if(m_Joystick.getRawButton(8)==true && m_Joystick.getRawButton(9)== false){

      intakeLeft.set(ControlMode.PercentOutput, -intakeSpeed );
      intakeRight.set(ControlMode.PercentOutput, -intakeSpeed);

    }
    else{
      intakeLeft.set(ControlMode.PercentOutput, 0);
      intakeRight.set(ControlMode.PercentOutput, 0);
    }
    if(m_Joystick.getRawButton(10)==true){
      if(blazeItDevice.getVoltage()<1.4){
        LiftRight.set(ControlMode.PercentOutput, liftSpeed );
        LiftLeft.set(ControlMode.PercentOutput, liftSpeed * -1);
      }
      else if(blazeItDevice.getVoltage()>3.6){
        LiftLeft.set(ControlMode.PercentOutput, liftSpeed );
        LiftRight.set(ControlMode.PercentOutput, liftSpeed * -1);

      }
      else if(blazeItDevice.getVoltage()<2.4 && blazeItDevice.getVoltage()>=1.4){
        LiftRight.set(ControlMode.PercentOutput, liftSpeed * .7);
        LiftLeft.set(ControlMode.PercentOutput, liftSpeed * -.7);
      }
      else if(blazeItDevice.getVoltage()>2.6 && blazeItDevice.getVoltage()<=3.6){
        LiftLeft.set(ControlMode.PercentOutput, liftSpeed * .7);
        LiftRight.set(ControlMode.PercentOutput, liftSpeed * -.7);

      }
      else{
        LiftLeft.set(ControlMode.PercentOutput, 0);
        LiftRight.set(ControlMode.PercentOutput, 0);
      }

    }
    
    sullie.on(m_Joystick.getRawButton(1));
  }
  
}
