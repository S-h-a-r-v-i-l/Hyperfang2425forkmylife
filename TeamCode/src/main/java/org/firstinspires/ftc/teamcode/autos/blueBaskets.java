package org.firstinspires.ftc.teamcode.autos;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class blueBaskets extends LinearOpMode {

    public DcMotorEx leftFront = null;
    public DcMotorEx leftBack = null;
    public DcMotorEx rightFront = null;
    public DcMotorEx rightBack = null;
    public DcMotorEx lL = null;
    public DcMotorEx rL = null;

    public CRServo rRP = null;
    public CRServo lRP = null;
    public CRServo clawLeft = null; //hanging claws
    public CRServo clawRight = null;
    public CRServo clawOpen = null; //actual claw itself
    public CRServo clawBelt = null; //rotational servo

    @Override
    public void runOpMode() throws InterruptedException {

        ElapsedTime runtime = new ElapsedTime();

        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");
        lL = hardwareMap.get(DcMotorEx.class, "leftLift");
        rL = hardwareMap.get(DcMotorEx.class, "rightLift");
        rRP = hardwareMap.get(CRServo.class, "rightRandP");
        lRP = hardwareMap.get(CRServo.class, "leftRandP");
        clawOpen = hardwareMap.get(CRServo.class, "clawOpen");
        clawBelt = hardwareMap.get(CRServo.class, "clawBelt");
        clawLeft = hardwareMap.get(CRServo.class, "clawRight");
        clawRight = hardwareMap.get(CRServo.class, "clawLeft");

        while(runtime.seconds() < 0.7){
            leftFront.setPower(1);
            rightFront.setPower(1);
            leftBack.setPower(1);
            rightBack.setPower(1);
        }
        while(runtime.seconds() < 1.7)
            leftFront.setPower(1);
            rightFront.setPower(1);
            leftBack.setPower(1);
            rightBack.setPower(1);
        }

    }





}
