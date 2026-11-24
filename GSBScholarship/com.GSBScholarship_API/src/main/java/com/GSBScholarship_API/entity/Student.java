package com.GSBScholarship_API.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


import java.util.Date;

@Entity
@Table(name = "online_student")
public class Student {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private  Integer id;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="middleName")
	private String middleName;
	
	@Column(name="mobileNo")
	private String mobileNo;
	
	@Column(name="gender")
	private String gender;

	@Column(name="emailId")
	private String emailId;
	
	
	@Column(name="dob")
	private String dob;
		
	@Column(name="status")
	private String status;

	
	
	@Column(name="aadhar")
	private String aadhar;
	
	@Column(name="sdate")
	private Date sdate;
	
	@Column(name="pancard")
	private String pancard;
	
	@Column(name="address")
	private String address;
	
	@Column(name="town")
	private String town;
	
	@Column(name="district")
	private String district;
	
	@Column(name="pin")
	private String pin;
	
	@Column(name="std")
	private String std;
	
	@Column(name="telephone")
	private String telephone;
	
	@Column(name="marks")
	private String marks;
	
	@Column(name="percentage")
	private float percentage;
	

	@Column(name="fatherFirstName")
	private String fatherFirstName;
	
	@Column(name="fatherLastName")
	private String fatherLastName;
	
	@Column(name="fatherMiddleName")
	private String fatherMiddleName;
	
	@Column(name="faddress")
	private String faddress;
	
	@Column(name="ftown")
	private String ftown;
	
	@Column(name="fdistrict")
	private String fdistrict;
	
	@Column(name="fpin")
	private String fpin;
	
	@Column(name="occuption")
	private String occuption;
	
	@Column(name="income")
	private float income;
	
	@Column(name="course")
	private String course ;
	
	@Column(name="sclass")
	private String sclass ;
	
	@Column(name="fees")
	private float fees;
	
	@Column(name="schoolName")
	private String schoolName;
	
	@Column(name="saddress")
	private String saddress;
	
	@Column(name="stown")
	private String stown;
	
	@Column(name="sdistrict")
	private String sdistrict;
	
	@Column(name="spin")
	private String spin;
	
	@Column(name="sstd")
	private String sstd;
	
	@Column(name="stelePhone")
	private String stelePhone;
	
	@Column(name="headMaster")
	private String headMaster;
	
	@Column(name="bankName")
	private String bankName;
	
	@Column(name="acco")
	private String acco;
	
	@Column(name="ifsc")
	private String ifsc;
	
	@Column(name="branch")
	private String branch;
	
	@Column(name="micr")
	private String micr;
	
	@Column(name="note")
	private String note;
	@Column(name="eligible")
	private boolean eligible;
	
	@Column(name="holderName")
	private String holderName;
	
	@Column(name="Scholarship_year")
	private String  syear;
	
	@Column(name="updated_date")
	private Date  updatedDate;
	
	@Column(name="updated_by")
	private String  updatedBy;
	
	@Column(name="remarks")
	private String  remarks;
	
	
	public String getSyear() {
		return syear;
	}

	public void setSyear(String syear) {
		this.syear = syear;
	}

	public boolean isEligible() {
		return eligible;
	}

	public void setEligible(boolean eligible) {
		this.eligible = eligible;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getPancard() {
		return pancard;
	}

	public void setPancard(String pancard) {
		this.pancard = pancard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getStd() {
		return std;
	}

	public void setStd(String std) {
		this.std = std;
	}

	
	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public String getFatherFirstName() {
		return fatherFirstName;
	}

	public void setFatherFirstName(String fatherFirstName) {
		this.fatherFirstName = fatherFirstName;
	}

	public String getFatherLastName() {
		return fatherLastName;
	}

	public void setFatherLastName(String fatherLastName) {
		this.fatherLastName = fatherLastName;
	}

	public String getFatherMiddleName() {
		return fatherMiddleName;
	}

	public void setFatherMiddleName(String fatherMiddleName) {
		this.fatherMiddleName = fatherMiddleName;
	}

	public String getFaddress() {
		return faddress;
	}

	public void setFaddress(String faddress) {
		this.faddress = faddress;
	}

	public String getFtown() {
		return ftown;
	}

	public void setFtown(String ftown) {
		this.ftown = ftown;
	}

	public String getFdistrict() {
		return fdistrict;
	}

	public void setFdistrict(String fdistrict) {
		this.fdistrict = fdistrict;
	}

	public String getFpin() {
		return fpin;
	}

	public void setFpin(String fpin) {
		this.fpin = fpin;
	}

	public String getOccuption() {
		return occuption;
	}

	public void setOccuption(String occuption) {
		this.occuption = occuption;
	}

	

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getSclass() {
		return sclass;
	}

	public void setSclass(String sclass) {
		this.sclass = sclass;
	}


	public float getIncome() {
		return income;
	}

	public void setIncome(float income) {
		this.income = income;
	}

	public float getFees() {
		return fees;
	}

	public void setFees(float fees) {
		this.fees = fees;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSaddress() {
		return saddress;
	}

	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}

	public String getStown() {
		return stown;
	}

	public void setStown(String stown) {
		this.stown = stown;
	}

	public String getSdistrict() {
		return sdistrict;
	}

	public void setSdistrict(String sdistrict) {
		this.sdistrict = sdistrict;
	}

	public String getSpin() {
		return spin;
	}

	public void setSpin(String spin) {
		this.spin = spin;
	}

	public String getSstd() {
		return sstd;
	}

	public void setSstd(String sstd) {
		this.sstd = sstd;
	}

	public String getStelePhone() {
		return stelePhone;
	}

	public void setStelePhone(String stelePhone) {
		this.stelePhone = stelePhone;
	}

	public String getHeadMaster() {
		return headMaster;
	}

	public void setHeadMaster(String headMaster) {
		this.headMaster = headMaster;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAcco() {
		return acco;
	}

	public void setAcco(String acco) {
		this.acco = acco;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getMicr() {
		return micr;
	}

	public void setMicr(String micr) {
		this.micr = micr;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	@JsonManagedReference
	@OneToOne(targetEntity = Image.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "student")
	private Image image=new Image();
	
	
	
//	 	@JsonManagedReference
//		@OneToMany(fetch = FetchType.EAGER, mappedBy="student", orphanRemoval = true)
//	    @MapKeyColumn(name="id")
//	    private Map<Long, Image> images = new HashMap<Long, Image>();

//	public Map<Long, Image> getImages() {
//		return images;
//	}
//
//	public void setImages(Map<Long, Image> images) {
//		this.images = images;
//	}

	

	public Date getSdate() {
		return sdate;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}



	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName="
				+ middleName + ", mobileNo=" + mobileNo + ", gender=" + gender + ", emailId=" + emailId + ", dob=" + dob
				+ ", status=" + status + ", aadhar=" + aadhar + ", sdate=" + sdate + ", pancard=" + pancard
				+ ", address=" + address + ", town=" + town + ", district=" + district + ", pin=" + pin + ", std=" + std
				+ ", telephone=" + telephone + ", marks=" + marks + ", percentage=" + percentage + ", fatherFirstName="
				+ fatherFirstName + ", fatherLastName=" + fatherLastName + ", fatherMiddleName=" + fatherMiddleName
				+ ", faddress=" + faddress + ", ftown=" + ftown + ", fdistrict=" + fdistrict + ", fpin=" + fpin
				+ ", occuption=" + occuption + ", income=" + income + ", course=" + course + ", sclass=" + sclass
				+ ", fees=" + fees + ", schoolName=" + schoolName + ", saddress=" + saddress + ", stown=" + stown
				+ ", sdistrict=" + sdistrict + ", spin=" + spin + ", sstd=" + sstd + ", stelePhone=" + stelePhone
				+ ", headMaster=" + headMaster + ", bankName=" + bankName + ", acco=" + acco + ", ifsc=" + ifsc
				+ ", branch=" + branch + ", micr=" + micr + ", note=" + note + ", eligible=" + eligible
				+ ", holderName=" + holderName + ", syear=" + syear + ", image=" + image + "]";
	}

	public Student(String firstName, String lastName, String middleName, String mobileNo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.mobileNo = mobileNo;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}



	
}
