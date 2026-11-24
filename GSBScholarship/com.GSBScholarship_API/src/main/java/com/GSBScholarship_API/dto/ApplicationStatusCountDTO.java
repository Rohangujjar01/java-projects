package com.GSBScholarship_API.dto;

public class ApplicationStatusCountDTO {
    private String applicationStatus;
    private int recordCount;
    private double percentage;

    public ApplicationStatusCountDTO(String applicationStatus, int recordCount, double percentage) {
        this.applicationStatus = applicationStatus;
        this.recordCount = recordCount;
        this.percentage = percentage;
    }

    // Getters and setters
    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
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

