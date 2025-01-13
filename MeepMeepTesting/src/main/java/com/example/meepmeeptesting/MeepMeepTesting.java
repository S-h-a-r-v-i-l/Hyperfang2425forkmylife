package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(11.8, 61.7, Math.toRadians(180)))

                .setTangent(Math.toRadians(330))
                .splineToLinearHeading(new Pose2d(57, 53, (5*Math.PI)/4), Math.toRadians(0))
                        .setTangent(Math.toRadians(250))
                        .splineToLinearHeading(new Pose2d(48, 40, (6*Math.PI)/4), Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(57, 53, (5*Math.PI)/4), Math.toRadians(100))
                        .splineToLinearHeading(new Pose2d(57, 40, (6*Math.PI)/4), Math.toRadians(100))
                        .splineToLinearHeading(new Pose2d(57, 53, (5*Math.PI)/4), Math.toRadians(100))
                        .splineToLinearHeading(new Pose2d(48, 25, (8*Math.PI)/4), Math.toRadians(100))
                        .splineToLinearHeading(new Pose2d(57, 53, (5*Math.PI)/4), Math.toRadians(100))
                        .splineToLinearHeading(new Pose2d(26, 0, Math.PI), Math.toRadians(150))
                        .build());

        /*/myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(11.8, 61.7, Math.toRadians(180)))

                .setTangent(Math.toRadians(330))
                .splineToLinearHeading(new Pose2d(57, 53, (5*Math.PI)/4), Math.toRadians(0))
                .setTangent(Math.toRadians(250))
                .splineToLinearHeading(new Pose2d(26, 0, Math.PI), Math.toRadians(200))
                .build());
        /*/
//                .setTangent(Math.toRadians(155))
//                .splineToLinearHeading(new Pose2d(60, -60, (11*Math.PI)/6), Math.toRadians(205))
//                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}