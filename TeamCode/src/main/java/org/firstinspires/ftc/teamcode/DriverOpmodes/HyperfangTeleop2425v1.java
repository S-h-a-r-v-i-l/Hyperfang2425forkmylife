package org.firstinspires.ftc.teamcode.DriverOpmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class HyperfangTeleop2425v1 extends LinearOpMode{
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
    public void runOpMode() {
        double speed, lift; speed = 1; lift = 1;

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


        Gamepad currentGamepad1 = new Gamepad();
        Gamepad currentGamepad2 = new Gamepad();

        Gamepad previousGamepad1 = new Gamepad();
        Gamepad previousGamepad2 = new Gamepad();

        while (opModeIsActive()) {

            previousGamepad1.copy(currentGamepad1);
            previousGamepad2.copy(currentGamepad2);
            currentGamepad1.copy(gamepad1);
            currentGamepad2.copy(gamepad2);

            double y = -currentGamepad1.left_stick_y * speed; // Remember, Y stick value is reversed
            double x = currentGamepad1.left_stick_x * speed; // Counteract imperfect strafing
            double rx = currentGamepad1.right_stick_x * speed;



            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            leftFront.setPower(frontLeftPower);
            leftBack.setPower(backLeftPower);
            rightFront.setPower(frontRightPower);
            rightBack.setPower(backRightPower);




            lL.setPower(gamepad2.left_stick_y * 0.5);
            rL.setPower(gamepad2.left_stick_y * 0.5);

            intakeServoExtenderRight.setPower(gamepad2.right_stick_y);
            intakeServoExtenderLeft.setPower(-1 * gamepad2.right_stick_y);


            if (gamepad1.y) {
                lRP.setPower(-1);
                rRP.setPower(1);
            } else if (gamepad1.a) {
                lRP.setPower(1);
                rRP.setPower(-1);
            }else {
                lRP.setPower(0);
                rRP.setPower(0);
            }

            if (gamepad2.y) {
                armServoLeft.setPower(-1);
                armServoRight.setPower(1);
            } else if (gamepad2.a) {
                armServoLeft.setPower(1);
                armServoRight.setPower(-1);
            }else {
                armServoRight.setPower(0);
                armServoLeft.setPower(0);
            }

            if (gamepad2.x) {
                clawOpen.setPower(1);
            } else if (gamepad2.b) {
                clawOpen.setPower(-1);
            }else {
                clawOpen.setPower(0);
            }

            if(gamepad2.dpad_left){

            } else if(gamepad2.dpad_up){
                intakeClawRotaterLeft.setPosition(0);
                intakeClawRotaterRight.setPosition(0);
            } else if(gamepad2.dpad_right){

            }


            if (gamepad2.right_trigger >= 0.5) {
                intakeSpinnerLeft.setPower(1);
                intakeSpinnerRight.setPower(-1);
            } else if (gamepad2.left_trigger >= 0.5) {
                intakeSpinnerLeft.setPower(-1);
                intakeSpinnerRight.setPower(1);
            }else {
                intakeSpinnerLeft.setPower(0);
                intakeSpinnerRight.setPower(0);
            }



            


            if (currentGamepad1.right_bumper && !previousGamepad1.right_bumper) {speed = Math.min(speed + 0.2, 1);}
            else if (currentGamepad1.left_bumper && !previousGamepad1.left_bumper) {speed = Math.max(speed - 0.2, 0);}

            telemetry.addLine("speed: " + speed);
            telemetry.update();
            sleep(50);
        }
    }
}

