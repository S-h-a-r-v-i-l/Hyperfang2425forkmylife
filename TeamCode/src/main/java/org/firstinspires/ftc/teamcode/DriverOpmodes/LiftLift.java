package org.firstinspires.ftc.teamcode.DriverOpmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp
public class LiftLift extends LinearOpMode{
    public DcMotorEx lL = null;
    public DcMotorEx rL = null;
    public CRServo rRP = null;
    public CRServo lRP = null;


    @Override
    public void runOpMode() {
        double speed, lift; speed = 1; lift = 1;

        lL = hardwareMap.get(DcMotorEx.class, "leftLift");
        rL = hardwareMap.get(DcMotorEx.class, "rightLift");
//        rRP = hardwareMap.get(CRServo.class, "rightR&P");
//        lRP = hardwareMap.get(CRServo.class, "leftR&P");

        lL.setDirection(DcMotorEx.Direction.REVERSE);
        lL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();


        while (opModeIsActive()) {

            rL.setPower(gamepad1.left_stick_y * 0.5);
            lL.setPower(gamepad1.left_stick_y * 0.5);
//            if (gamepad1.right_bumper) {
//                rRP.setPower(0.2);
//                lRP.setPower(0.2);
//            }
        }
    }
}

