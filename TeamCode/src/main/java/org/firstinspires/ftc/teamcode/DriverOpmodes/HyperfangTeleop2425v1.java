package org.firstinspires.ftc.teamcode.DriverOpmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

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
    public CRServo intakeClawRotaterRight = null;
    public CRServo intakeClawRotaterLeft = null;
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
        rRP = hardwareMap.get(CRServo.class, "rightRandP");
        lRP = hardwareMap.get(CRServo.class, "leftRandP");
        clawOpen = hardwareMap.get(CRServo.class, "clawOpen");
        armServoRight = hardwareMap.get(CRServo.class, "asr");
        armServoLeft = hardwareMap.get(CRServo.class, "asl");
        intakeServoExtenderRight = hardwareMap.get(CRServo.class, "iser");
        intakeServoExtenderLeft = hardwareMap.get(CRServo.class, "isel");
        intakeClawRotaterRight = hardwareMap.get(CRServo.class, "icrr");
        intakeClawRotaterLeft = hardwareMap.get(CRServo.class, "icrl");
        intakeSpinnerRight = hardwareMap.get(CRServo.class, "isr");
        intakeSpinnerLeft = hardwareMap.get(CRServo.class, "isl");

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

            lRP.setPower(gamepad2.right_stick_y);
            rRP.setPower(-1 * gamepad2.right_stick_y);



            if (gamepad2.right_trigger >= 0.5) {
                clawOpen.setPower(1);
            } else if (gamepad2.left_trigger >= 0.5) {
                clawOpen.setPower(-1);
            }else {
                clawOpen.setPower(0);
            }

            


            if (currentGamepad1.right_bumper && !previousGamepad1.right_bumper) {speed = Math.min(speed + 0.2, 1);}
            else if (currentGamepad1.left_bumper && !previousGamepad1.left_bumper) {speed = Math.max(speed - 0.2, 0);}

            telemetry.addLine("speed: " + speed);
            telemetry.update();
            sleep(50);
        }
    }
}

