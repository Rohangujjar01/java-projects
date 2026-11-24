package com.GSBScholarship_API.dto;

public class CourseStatusCountsDTO {
    private String course;
    private int inReview;
    private int accepted;
    private int rejected;
    private int pctSponsored;
    private int individualSponsored;
    private int toBeConsidered;

    public CourseStatusCountsDTO(String course, int inReview, int accepted, int rejected, int pctSponsored, int individualSponsored, int toBeConsidered) {
        this.course = course;
        this.inReview = inReview;
        this.accepted = accepted;
        this.rejected = rejected;
        this.pctSponsored = pctSponsored;
        this.individualSponsored = individualSponsored;
        this.toBeConsidered = toBeConsidered;
    }

    // Getters and setters
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getInReview() {
        return inReview;
    }

    public void setInReview(int inReview) {
        this.inReview = inReview;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public int getRejected() {
        return rejected;
    }

    public void setRejected(int rejected) {
        this.rejected = rejected;
    }

    public int getPctSponsored() {
        return pctSponsored;
    }

    public void setPctSponsored(int pctSponsored) {
        this.pctSponsored = pctSponsored;
    }

    public int getIndividualSponsored() {
        return individualSponsored;
    }

    public void setIndividualSponsored(int individualSponsored) {
        this.individualSponsored = individualSponsored;
    }

    public int getToBeConsidered() {
        return toBeConsidered;
    }

    public void setToBeConsidered(int toBeConsidered) {
        this.toBeConsidered = toBeConsidered;
    }
}

