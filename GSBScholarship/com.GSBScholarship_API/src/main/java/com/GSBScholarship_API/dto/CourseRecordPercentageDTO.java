package com.GSBScholarship_API.dto;

public class CourseRecordPercentageDTO {
    private String course;
    private int recordCount;
    private double percentage;

    public CourseRecordPercentageDTO(String course, int recordCount, double percentage) {
        this.course = course;
        this.recordCount = recordCount;
        this.percentage = percentage;
    }

    // Getters and setters
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}

