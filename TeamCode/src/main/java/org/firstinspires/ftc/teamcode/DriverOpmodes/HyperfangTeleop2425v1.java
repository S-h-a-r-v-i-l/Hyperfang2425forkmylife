package org.firstinspires.ftc.teamcode.DriverOpmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

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
    public Servo intakeServoExtenderRight = null;
    public Servo intakeServoExtenderLeft = null;
    public Servo intakeClawRotaterRight = null;
    public Servo intakeClawRotaterLeft = null;
    public CRServo intakeSpinnerRight = null;
    public CRServo intakeSpinnerLeft = null;
    public DistanceSensor distanceSensor = null;


    @Override
    public void runOpMode() throws InterruptedException {
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
        intakeServoExtenderRight = hardwareMap.get(Servo.class, "intakeSlidesR");
        intakeServoExtenderLeft = hardwareMap.get(Servo.class, "intakeSlidesL");
        intakeClawRotaterRight = hardwareMap.get(Servo.class, "intakeRotateR");
        intakeClawRotaterLeft = hardwareMap.get(Servo.class, "intakeRotateL");
        intakeSpinnerRight = hardwareMap.get(CRServo.class, "intakeWheelR");
        intakeSpinnerLeft = hardwareMap.get(CRServo.class, "intakeWheelL");
        distanceSensor = hardwareMap.get(DistanceSensor.class, "distance");


        lL.setDirection(DcMotorEx.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

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
        rL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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

//            double y = -currentGamepad1.left_stick_y * speed; // Remember, Y stick value is reversed
//            double x = currentGamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
//            double rx = currentGamepad1.right_stick_x * speed;
//
//
//
//            // Denominator is the largest motor power (absolute value) or 1
//            // This ensures all the powers maintain the same ratio,
//            // but only if at least one is out of the range [-1, 1]
//            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
//            double frontLeftPower = (y + x + rx) / denominator;
//            double backLeftPower = (y - x + rx) / denominator;
//            double frontRightPower = (y - x - rx) / denominator;
//            double backRightPower = (y + x - rx) / denominator;

            double left_stick_x = gamepad1.left_stick_x;
            double left_stick_y = gamepad1.left_stick_y;
            double right_stick_x = gamepad1.right_stick_x;

            if(Math.abs(left_stick_x) > 0.1 ||Math.abs(left_stick_y) > .1 || Math.abs(right_stick_x) > 0.1) {
                rightFront.setPower(.7*((left_stick_y + left_stick_x) + right_stick_x));
                leftFront.setPower(.7*((left_stick_y - left_stick_x) - right_stick_x));
                rightBack.setPower(.7*((left_stick_y - left_stick_x) + right_stick_x));
                leftBack.setPower(.7*((left_stick_y + left_stick_x) - right_stick_x));
            }
            else{
                leftFront.setPower(0);
                rightFront.setPower(0);
                rightBack.setPower(0);
                leftBack.setPower(0);
            }
            telemetry.addData("fr:", rightFront.getPower());
            telemetry.addData("br:", rightBack.getPower());
            telemetry.addData("fl:", leftFront.getPower());
            telemetry.addData("bl:", leftBack.getPower());
            telemetry.addData("Lift motor position", rL.getCurrentPosition());

            lL.setPower(-gamepad2.right_stick_y * 0.8);
            rL.setPower(-gamepad2.right_stick_y * 0.8);

            boolean extended = false;

//            if(lL.getPower() > 0){
//                extended = true;
//                intakeClawRotaterRight.setDirection(Servo.Direction.REVERSE);
//                intakeClawRotaterRight.setPosition(0.7);
//            }

            if (gamepad1.x)
            {
                intakeServoExtenderRight.setPosition(0);
                intakeServoExtenderLeft.setPosition(0);
            }
            if (gamepad1.b)
            {
                intakeServoExtenderRight.setPosition(1);
                intakeServoExtenderLeft.setPosition(1);
            }

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
                armServoLeft.setPower(-0.2);
                armServoRight.setPower(0.2);
            } else if (gamepad2.a) {
                armServoLeft.setPower(0.2);
                armServoRight.setPower(-0.2);
            }else {
                armServoRight.setPower(0);
                armServoLeft.setPower(0);
            }

//            if (gamepad2.left_stick_y > 0) {
//                intakeServoExtenderLeft.setPower(-gamepad2.left_stick_y);
//                intakeServoExtenderRight.setPower(gamepad2.left_stick_y);
//            } else if (gamepad2.left_stick_y < 0) {
//                intakeServoExtenderLeft.setPower(gamepad2.left_stick_y);
//                intakeServoExtenderRight.setPower(-gamepad2.left_stick_y);
//            }else {
//                intakeServoExtenderLeft.setPower(0);
//                intakeServoExtenderRight.setPower(0);
//            }

            if (gamepad2.x) {
                clawOpen.setPower(1);
            } else if (gamepad2.b) {
                clawOpen.setPower(-1);
            }else {
                clawOpen.setPower(0);
            }

            if(gamepad2.left_bumper){
                extended = false;
            }


            double intakePos = 0.0;
            if (gamepad2.dpad_left) {
                intakePos = 0.6;
                intakeClawRotaterRight.setDirection(Servo.Direction.REVERSE);
                intakeClawRotaterRight.setPosition(intakePos);
            } else if (gamepad2.dpad_right) {
                intakePos = 0.2;
                intakeClawRotaterRight.setDirection(Servo.Direction.REVERSE);
                intakeClawRotaterRight.setPosition(intakePos);
            } else if (gamepad2.dpad_up) {
                intakePos = 0.45;
                intakeClawRotaterRight.setDirection(Servo.Direction.REVERSE);
                intakeClawRotaterRight.setPosition(intakePos);
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


            if (currentGamepad2.y) {
                lRP.setPower(1);
                rRP.setPower(1);
            }
            if (currentGamepad2.a) {
                lRP.setPower(-1);
                rRP.setPower(-1);
            }

            if (distanceSensor.getDistance(DistanceUnit.INCH) > 6)
            {
                intakeServoExtenderLeft.setPosition(0);
                intakeServoExtenderRight.setPosition(0);
            }
        }
    }
}

