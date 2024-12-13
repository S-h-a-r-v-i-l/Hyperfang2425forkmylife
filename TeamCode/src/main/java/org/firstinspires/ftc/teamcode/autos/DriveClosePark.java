package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class DriveClosePark extends LinearOpMode{
    public DcMotorEx leftFront = null;
    public DcMotorEx leftBack = null;
    public DcMotorEx rightFront = null;
    public DcMotorEx rightBack = null;
    public DcMotorEx lL = null;
    public DcMotorEx rL = null;

    public CRServo rRP = null;
    public CRServo lRP = null;
    public CRServo clawOpen = null; //actual claw itself
    public CRServo armServoRight = null;
    public CRServo armServoLeft = null;
    public CRServo intakeServoExtenderRight = null;
    public CRServo intakeServoExtenderLeft = null;
    public Servo intakeClawRotaterRight = null;
    public Servo intakeClawRotaterLeft = null;
    public CRServo intakeSpinnerRight = null;
    public CRServo intakeSpinnerLeft = null;


    @Override
    public void runOpMode() throws InterruptedException {


        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");
        lL = hardwareMap.get(DcMotorEx.class, "leftLift");
        rL = hardwareMap.get(DcMotorEx.class, "rightLift");

        rRP = hardwareMap.get(CRServo.class, "hangR");
        lRP = hardwareMap.get(CRServo.class, "hangL");
        clawOpen = hardwareMap.get(CRServo.class, "clawOpen");
        armServoRight = hardwareMap.get(CRServo.class, "elbowR");
        armServoLeft = hardwareMap.get(CRServo.class, "elbowL");
        intakeServoExtenderRight = hardwareMap.get(CRServo.class, "intakeSlidesR");
        intakeServoExtenderLeft = hardwareMap.get(CRServo.class, "intakeSlidesL");
        intakeClawRotaterRight = hardwareMap.get(Servo.class, "intakeRotateR");
        intakeClawRotaterLeft = hardwareMap.get(Servo.class, "intakeRotateL");
        intakeSpinnerRight = hardwareMap.get(CRServo.class, "intakeWheelR");
        intakeSpinnerLeft = hardwareMap.get(CRServo.class, "intakeWheelL");

        leftBack.setDirection(DcMotorEx.Direction.REVERSE);
        leftFront.setDirection(DcMotorEx.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        lL.setDirection(DcMotorEx.Direction.REVERSE);


        lL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        leftFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        waitForStart();

        if (opModeIsActive()) {
            leftBack.setPower(0.3);
            rightBack.setPower(0.3);
            leftFront.setPower(0.3);
            rightFront.setPower(0.3);
            sleep(2000);
        }
    }

}
