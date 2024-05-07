package com.example.taskhub.Task.Enums;

import com.example.taskhub.project.enums.ProjectStatus;

public class TaskStatus {
    public enum TaskStatusList {
        TO_START,
        IN_PROCESS,
        STOPPED,
        COMPLETED,
        CANCELLED,
        ON_HOLD,
        DELAYED
    }

    public static String setTaskStatus(TaskStatus.TaskStatusList taskStatus) {
        switch (taskStatus) {
            case TO_START: return "to_start";
            case IN_PROCESS: return "in_process";
            case STOPPED: return "stopped";
            case COMPLETED: return "completed";
            case CANCELLED: return "cancelled";
            case ON_HOLD: return "on_hold";
            case DELAYED: return "delayed";

            default: return "to_start";
        }
    }
}
