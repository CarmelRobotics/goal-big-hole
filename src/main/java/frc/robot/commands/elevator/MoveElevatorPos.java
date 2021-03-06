/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Elevator;

public class MoveElevatorPos extends Command {

  private Elevator el;

  Command move;

  private int goal;
  private double heightOfTarget;
  private boolean runOnce;

  public MoveElevatorPos(int desiredPos) {

    el = Robot.elevator;
    goal = desiredPos;
    runOnce = false;

    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if (!RobotMap.ELEV_MOVING && !runOnce) {
      RobotMap.ELEV_MOVING = true;
      heightOfTarget = el.convertInToRot(RobotMap.ELEV_INCHES[goal]);
      double currentRelativePos = el.getElevatorActualEncoderPos() - el.getRelativeZero();
      //move = new MoveElevatorRotations(heightOfTarget - currentRelativePos);
      //move.start();
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    runOnce = true;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return !RobotMap.ELEV_MOVING;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    move.close();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    move.close();
  }

}
