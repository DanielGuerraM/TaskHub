package com.example.taskhub.project.enums;

public class ProjectStatus {
    public enum projectStatusList {
        TO_START,
        IN_PROCESS,
        STOPPED,
        COMPLETED,
        CANCELLED
    }

    public static String setProjectStatus(projectStatusList projectStatus) {
        switch (projectStatus) {
            case TO_START: return "to_start";
            case IN_PROCESS: return "in_process";
            case STOPPED: return "stopped";
            case COMPLETED: return "completed";
            case CANCELLED: return "cancelled";

            default: return "to_start";
        }
    }
}
