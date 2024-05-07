package com.example.taskhub.Assignment.DTO;

import java.time.LocalDate;
import java.util.List;

public class ResponseUserDTO {
    private String id;
    private String username;
    private String names;
    private String last_names;
    private String email;
    private List<Projects> projects;

    public static class Projects {
        private String id;
        private String title;
        private String description;
        private String status;
        private Float progress;
        private LocalDate start_date;
        private LocalDate finish_date;

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getStatus() {
            return status;
        }

        public Float getProgress() {
            return progress;
        }

        public LocalDate getStart_date() {
            return start_date;
        }

        public LocalDate getFinish_date() {
            return finish_date;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setProgress(Float progress) {
            this.progress = progress;
        }

        public void setStart_date(LocalDate start_date) {
            this.start_date = start_date;
        }

        public void setFinish_date(LocalDate finish_date) {
            this.finish_date = finish_date;
        }
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getNames() {
        return names;
    }

    public String getLast_names() {
        return last_names;
    }

    public String getEmail() {
        return email;
    }

    public List<Projects> getProjects() {
        return projects;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public void setLast_names(String last_names) {
        this.last_names = last_names;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProjects(List<Projects> projects) {
        this.projects = projects;
    }
}
