package org.firstinspires.ftc.teamcode.autos;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.MecanumDrive;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
@Autonomous(name = "BlueBasketsAuto", group = "Autonomous")
public class BlueSideTestAuto extends LinearOpMode {
    public class Lifts{
        private DcMotorEx leftLift;
        private DcMotorEx rightLift;

        public Lifts(HardwareMap hardwareMap) {
            leftLift = hardwareMap.get(DcMotorEx.class, "leftLift");
            leftLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftLift.setDirection(DcMotorSimple.Direction.FORWARD);

            rightLift = hardwareMap.get(DcMotorEx.class, "rightLift");
            rightLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightLift.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        public class LiftUp implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    leftLift.setPower(0.8);
                    rightLift.setPower(0.8);
                    initialized = true;
                }

                double pos = leftLift.getCurrentPosition();
                packet.put("liftPos", pos);
                if (pos < 3000.0) {
                    return true;
                } else {
                    leftLift.setPower(0);
                    rightLift.setPower(0);
                    return false;
                }
            }
        }
        public Action liftUp() {
            return new LiftUp();
        }

        public class LiftDown implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    leftLift.setPower(-0.8);
                    rightLift.setPower(-0.8);
                    initialized = true;
                }

                double pos = leftLift.getCurrentPosition();
                packet.put("liftPos", pos);
                if (pos > 100.0) {
                    return true;
                } else {
                    leftLift.setPower(0);
                    rightLift.setPower(0);
                    return false;
                }
            }
        }
        public Action liftDown(){
            return new LiftDown();
        }
    }

    public class Claw{
        private CRServo clawOpen;
        private CRServo asr;
        private CRServo asl;

        public Claw(HardwareMap hMap){
            clawOpen = hMap.get(CRServo.class, "clawOpen");
            asr = hMap.get(CRServo.class, "elbowR");
            asl = hMap.get(CRServo.class, "elbowL");
        }

        public class CloseClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                ElapsedTime t = new ElapsedTime();
                t.startTime();
                while(t.seconds() < 0.5){ clawOpen.setPower(-1);}
                return false;
            }
        }
        public Action closeClaw() {
            return new CloseClaw();
        }

        public class OpenClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                ElapsedTime t = new ElapsedTime();
                t.startTime();
                while(t.seconds() < 0.5){ clawOpen.setPower(1);}
                return false;
            }
        }
        public Action openClaw() {
            return new OpenClaw();
        }

        public class RotateClawForward implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                ElapsedTime t = new ElapsedTime();
                t.startTime();
                while(t.seconds() < 0.3){ asl.setPower(-1); asl.setPower(1);}
                return false;
            }
        }
        public Action rotateClawForward() {
            return new RotateClawForward();
        }

        public class RotateClawBackward implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                ElapsedTime t = new ElapsedTime();
                t.startTime();
                while(t.seconds() < 0.3){ asl.setPower(1); asl.setPower(-1);}
                return false;
            }
        }
        public Action rotateClawBackward() {
            return new RotateClawBackward();
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d initialPose = new Pose2d(11.8, 61.7, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Claw claw = new Claw(hardwareMap);
        Lifts leftLift = new Lifts(hardwareMap);
        Lifts rightLift = new Lifts(hardwareMap);

        TrajectoryActionBuilder mainTab = drive.actionBuilder(initialPose);


    }

}
