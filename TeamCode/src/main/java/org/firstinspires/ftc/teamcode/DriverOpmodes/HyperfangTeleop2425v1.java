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
    public Servo rightElbow = null;
    public Servo leftElbow = null;
    public CRServo intakeServoExtenderRight = null;
    public CRServo intakeServoExtenderLeft = null;
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
        rightElbow = hardwareMap.get(Servo.class, "elbowR");
        leftElbow = hardwareMap.get(Servo.class, "elbowL");
        intakeServoExtenderRight = hardwareMap.get(CRServo.class, "intakeSlidesR");
        intakeServoExtenderLeft = hardwareMap.get(CRServo.class, "intakeSlidesL");
        intakeClawRotaterRight = hardwareMap.get(Servo.class, "intakeRotateR");
        intakeClawRotaterLeft = hardwareMap.get(Servo.class, "intakeRotateL");
        intakeSpinnerRight = hardwareMap.get(CRServo.class, "intakeWheelR");
        intakeSpinnerLeft = hardwareMap.get(CRServo.class, "intakeWheelL");
        distanceSensor = hardwareMap.get(DistanceSensor.class, "distance");


        lL.setDirection(DcMotorEx.Direction.REVERSE);
        rightFront.setDirection(DcMotorEx.Direction.REVERSE);
        rightBack.setDirection(DcMotorEx.Direction.REVERSE);

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
            telemetry.update();

            lL.setPower(-gamepad2.right_stick_y * 0.6);
            rL.setPower(-gamepad2.right_stick_y * 0.4);
            if (gamepad2.right_stick_y >= 0.2) {
                intakeClawRotaterRight.setDirection(Servo.Direction.REVERSE);
                intakeClawRotaterRight.setPosition(0.2);

            }

            // Todo: Intake controls----------------------------------------------------------------

            // Spinner controls
            if(gamepad1.left_bumper){
                intakeSpinnerLeft.setPower(-1);
                intakeSpinnerRight.setPower(1);
            } else if(gamepad1.right_bumper){
                intakeSpinnerLeft.setPower(1);
                intakeSpinnerRight.setPower(-1);
            }

            // Todo: Intake position controls, update setPosition values and tune
            if (distanceSensor.getDistance(DistanceUnit.INCH) > 6)
                intakeClawRotaterRight.setDirection(Servo.Direction.REVERSE);
                intakeClawRotaterRight.setPosition(0.6);
            }
            if (gamepad1.dpad_left) {
                intakeClawRotaterRight.setDirection(Servo.Direction.REVERSE);
                intakeClawRotaterRight.setPosition(0.6);
            } else if (gamepad1.dpad_right) {
                intakeClawRotaterRight.setDirection(Servo.Direction.REVERSE);
                intakeClawRotaterRight.setPosition(0.2);
            } else if (gamepad1.dpad_up) {
                intakeClawRotaterRight.setDirection(Servo.Direction.REVERSE);
                intakeClawRotaterRight.setPosition(0.45);
            }



            // Todo: Decide if we are using setPosition or not
            // Todo: Also guestimate servo orientation

            // Set Position Special
            if(gamepad1.y){
                intakeServoExtenderRight.setPower(1);
                intakeServoExtenderLeft.setPower(-1);
            } else if(gamepad1.a){
                intakeServoExtenderRight.setPower(-1);
                intakeServoExtenderLeft.setPower(1);
            }

            // Todo: Outtake controls---------------------------------------------------------------

            // Elbow code

            // Todo: Elbow position controls, update setPosition values and tune
            if (gamepad2.dpad_left) {
                leftElbow.setDirection(Servo.Direction.REVERSE);
                leftElbow.setPosition(0.85);
                rightElbow.setPosition(0.85);
            } else if (gamepad2.dpad_up) {
                leftElbow.setDirection(Servo.Direction.REVERSE);
                leftElbow.setPosition(0.75);
                rightElbow.setPosition(0.75);
            } else if (gamepad2.dpad_right) {
                leftElbow.setDirection(Servo.Direction.REVERSE);
                leftElbow.setPosition(0.6);
                rightElbow.setPosition(0.6);
            } else if (gamepad2.dpad_down) {
                leftElbow.setDirection(Servo.Direction.REVERSE);
                leftElbow.setPosition(0.2);
                rightElbow.setPosition(0.2);
            }

            // Claw open code

            if (gamepad2.x) {
                clawOpen.setPower(1);
            } else if (gamepad2.b) {
                clawOpen.setPower(-1);
            }else {
                clawOpen.setPower(0);
            }


















//            boolean extended = false;
//
////            if(lL.getPower() > 0){
////                extended = true;
////                intakeClawRotaterRight.setDirection(Servo.Direction.REVERSE);
////                intakeClawRotaterRight.setPosition(0.7);
////            }
//
//            if (gamepad1.x)
//            {
//                intakeServoExtenderRight.setPosition(0);
//                intakeServoExtenderLeft.setPosition(0);
//            }
//            if (gamepad1.b)
//            {
//                intakeServoExtenderRight.setPosition(1);
//                intakeServoExtenderLeft.setPosition(1);
//            }
//
//            if (gamepad1.y) {
//                lRP.setPower(-1);
//                rRP.setPower(1);
//            } else if (gamepad1.a) {
//                lRP.setPower(1);
//                rRP.setPower(-1);
//            }else {
//                lRP.setPower(0);
//                rRP.setPower(0);
//            }
//
//            if (gamepad2.y) {
//                armServoLeft.setPower(-0.2);
//                armServoRight.setPower(0.2);
//            } else if (gamepad2.a) {
//                armServoLeft.setPower(0.2);
//                armServoRight.setPower(-0.2);
//            }else {
//                armServoRight.setPower(0);
//                armServoLeft.setPower(0);
//            }
//
////            if (gamepad2.left_stick_y > 0) {
////                intakeServoExtenderLeft.setPower(-gamepad2.left_stick_y);
////                intakeServoExtenderRight.setPower(gamepad2.left_stick_y);
////            } else if (gamepad2.left_stick_y < 0) {
////                intakeServoExtenderLeft.setPower(gamepad2.left_stick_y);
////                intakeServoExtenderRight.setPower(-gamepad2.left_stick_y);
////            }else {
////                intakeServoExtenderLeft.setPower(0);
////                intakeServoExtenderRight.setPower(0);
////            }
//
//            if (gamepad2.x) {
//                clawOpen.setPower(1);
//            } else if (gamepad2.b) {
//                clawOpen.setPower(-1);
//            }else {
//                clawOpen.setPower(0);
//            }
//
//            if(gamepad2.left_bumper){
//                extended = false;
//            }
//
//
//            double intakePos = 0.0;
//            if (gamepad2.dpad_left) {
//                intakePos = 0.6;
//                intakeClawRotaterRight.setDirection(Servo.Direction.REVERSE);
//                intakeClawRotaterRight.setPosition(intakePos);
//            } else if (gamepad2.dpad_right) {
//                intakePos = 0.2;
//                intakeClawRotaterRight.setDirection(Servo.Direction.REVERSE);
//                intakeClawRotaterRight.setPosition(intakePos);
//            } else if (gamepad2.dpad_up) {
//                intakePos = 0.45;
//                intakeClawRotaterRight.setDirection(Servo.Direction.REVERSE);
//                intakeClawRotaterRight.setPosition(intakePos);
//            }
//
//
//            if (gamepad2.right_trigger >= 0.5) {
//                intakeSpinnerLeft.setPower(1);
//                intakeSpinnerRight.setPower(-1);
//            } else if (gamepad2.left_trigger >= 0.5) {
//                intakeSpinnerLeft.setPower(-1);
//                intakeSpinnerRight.setPower(1);
//            }else {
//                intakeSpinnerLeft.setPower(0);
//                intakeSpinnerRight.setPower(0);
//            }
//
//
//            if (currentGamepad1.right_bumper && !previousGamepad1.right_bumper) {speed = Math.min(speed + 0.2, 1);}
//            else if (currentGamepad1.left_bumper && !previousGamepad1.left_bumper) {speed = Math.max(speed - 0.2, 0);}
//
//            telemetry.addLine("speed: " + speed);
//            telemetry.update();
//            sleep(50);
//
//
//            if (currentGamepad2.y) {
//                lRP.setPower(1);
//                rRP.setPower(1);
//            }
//            if (currentGamepad2.a) {
//                lRP.setPower(-1);
//                rRP.setPower(-1);
//            }
//
//            if (distanceSensor.getDistance(DistanceUnit.INCH) > 6)
//            {
//                intakeServoExtenderLeft.setPosition(0);
//                intakeServoExtenderRight.setPosition(0);
//            }
        }
    }
}

