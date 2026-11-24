package com.GSBScholarship_API.dto;

public class MonthlyCountDTO {
	
	    private String monthName;
	    private int rowCount;

	    public MonthlyCountDTO(String monthName, int rowCount) {
	        this.monthName = monthName;
	        this.rowCount = rowCount;
	    }

	    // Getters and setters
	    public String getMonthName() {
	        return monthName;
	    }

	    public void setMonthName(String monthName) {
	        this.monthName = monthName;
	    }

	    public int getRowCount() {
	        return rowCount;
	    }

	    public void setRowCount(int rowCount) {
	        this.rowCount = rowCount;
	    }
	}	
	

