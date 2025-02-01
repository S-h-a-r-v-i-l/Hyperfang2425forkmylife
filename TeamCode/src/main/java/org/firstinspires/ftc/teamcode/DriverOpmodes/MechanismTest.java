package org.firstinspires.ftc.teamcode.DriverOpmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class MechanismTest extends LinearOpMode {
    public Servo rightElbow = null;
    public Servo leftElbow = null;


    @Override
    public void runOpMode() throws InterruptedException {
        rightElbow = hardwareMap.get(Servo.class, "elbowR");
        leftElbow = hardwareMap.get(Servo.class, "elbowL");
        double position = 0;

        Gamepad currentGamepad1 = new Gamepad();
        Gamepad currentGamepad2 = new Gamepad();

        Gamepad previousGamepad1 = new Gamepad();
        Gamepad previousGamepad2 = new Gamepad();
        leftElbow.setDirection(Servo.Direction.REVERSE);

        waitForStart();
        while (opModeIsActive()) {
            previousGamepad1.copy(currentGamepad1);
            previousGamepad2.copy(currentGamepad2);
            currentGamepad1.copy(gamepad1);
            currentGamepad2.copy(gamepad2);

            if (currentGamepad2.right_bumper && !previousGamepad2.right_bumper) {position+= 0.01;}
            else if (currentGamepad2.left_bumper && !previousGamepad2.left_bumper) {position -= 0.01;}

            if (gamepad2.a) {
                leftElbow.setDirection(Servo.Direction.REVERSE);
                leftElbow.setPosition(position);
            }
            if (gamepad2.b) {
                rightElbow.setPosition(position);
            }

            telemetry.addLine("Position: " + position);
            telemetry.update();

            //0.11 = ground, 0.20 = horizontal, 0.56 = straight up left
            //0.41 = ground, 0.46 = horizontal, 1 = straight up right
        }
    }
}
