package com.GSBScholarship_API.dto;

public class GenderCourseCountDTO {
    private String gender;
    private String course;
    private int recordCount;

    public GenderCourseCountDTO(String gender, String course, int recordCount) {
        this.gender = gender;
        this.course = course;
        this.recordCount = recordCount;
    }

    // Getters and setters
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

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
}

