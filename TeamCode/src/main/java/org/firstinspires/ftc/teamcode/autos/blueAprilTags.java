//package org.firstinspires.ftc.teamcode.autos;
//
//import org.firstinspires.ftc.vision.VisionPortal;
//import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
//import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
//
//import java.util.List;
//
//public class blueAprilTags {
//    //TAG 12 is the one cented on the blue
//    private static final boolean USE_WEBCAM = true;  // Set true to use a webcam, or false for a phone camera
//    private static final int DESIRED_TAG_ID = -1;     // Choose the tag you want to approach or set to -1 for ANY tag.
//    private VisionPortal visionPortal;               // Used to manage the video source.
//    private AprilTagProcessor aprilTag;              // Used for managing the AprilTag detection process.
//    private AprilTagDetection desiredTag = null;
//    private int aprilTag_ID = 12;
//    private boolean found = false;
//
//    while (found == false)
//    {
//        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
//        for (AprilTagDetection detection : currentDetections) {
//            // Look to see if we have size info on this tag.
//            if (detection.metadata != null) {
//                //  Check to see if we want to track towards this tag.
//                if (aprilTag_ID < 0) || (detection.id == DESIRED_TAG_ID)) {
//                    // Yes, we want to use this tag.
//                    boolean targetFound = true;
//                    desiredTag = detection;
//                    break;  // don't look any further.
//    }
//}
