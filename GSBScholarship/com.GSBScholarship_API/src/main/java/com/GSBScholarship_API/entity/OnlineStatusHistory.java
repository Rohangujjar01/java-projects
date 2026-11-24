package com.GSBScholarship_API.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


import java.util.Date;

@Entity
@Table(name = "online_status_history")
public class OnlineStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JsonBackReference
	@ManyToOne(targetEntity = User.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id",referencedColumnName = "user_id")
	private User user;
	
	@JsonBackReference
	@ManyToOne(targetEntity = Student.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="student_id",referencedColumnName = "id")
	private Student student;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "remarks")
    private String remarks;
    
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}	
}

