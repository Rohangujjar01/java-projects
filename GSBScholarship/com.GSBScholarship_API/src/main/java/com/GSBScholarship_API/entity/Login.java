package com.GSBScholarship_API.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "online_login")
public class Login {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private  int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="mobile_no")
	private String mobileNo;

	@Column(name="otp")
	private int otp;
	
	@Column(name="accept")
	private Boolean accept;
	
	@Column(name="submit")
	private Boolean submit;
	
	@Column(name="otptime")
	private Long otptime;
	
	@Column(name="schol_year")
	private String scholYear;


	public Long getOtptime() {
		return otptime;
	}

	public void setOtptime(Long otptime) {
		this.otptime = otptime;
	}

	public Boolean getAccept() {
		return accept;
	}

	public void setAccept(Boolean accept) {
		this.accept = accept;
	}

	public Boolean getSubmit() {
		return submit;
	}

	public void setSubmit(Boolean submit) {
		this.submit = submit;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	

	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getScholYear() {
		return scholYear;
	}

	public void setScholYear(String scholYear) {
		this.scholYear = scholYear;
	}

	public Login(int id, String name, String mobileNo, int otp, Boolean accept, Boolean submit, Long otptime,
			String scholYear) {
		super();
		this.id = id;
		this.name = name;
		this.mobileNo = mobileNo;
		this.otp = otp;
		this.accept = accept;
		this.submit = submit;
		this.otptime = otptime;
		this.scholYear = scholYear;
	}

	@Override
	public String toString() {
		return "Login [id=" + id + ", name=" + name + ", mobileNo=" + mobileNo + ", otp=" + otp + ", accept=" + accept
				+ ", submit=" + submit + ", otptime=" + otptime + ", scholYear=" + scholYear + "]";
	}

	
	

	
}
