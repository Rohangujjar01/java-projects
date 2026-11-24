package com.GSBScholarship_API.dto;

public class GenderRecordPercentageDTO {
    private String gender;
    private int recordCount;
    private double percentage;

    public GenderRecordPercentageDTO(String gender, int recordCount, double percentage) {
        this.gender = gender;
        this.recordCount = recordCount;
        this.percentage = percentage;
    }

    // Getters and setters
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

