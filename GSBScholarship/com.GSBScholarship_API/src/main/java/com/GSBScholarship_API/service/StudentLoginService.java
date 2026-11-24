package com.GSBScholarship_API.service;

import com.GSBScholarship_API.dto.SampleResponseDto;
import com.GSBScholarship_API.entity.Login;
import com.GSBScholarship_API.entity.OnlineNewStudent;
import com.GSBScholarship_API.entity.Student;
import com.GSBScholarship_API.repository.LoginRepo;
import com.GSBScholarship_API.repository.OnlineNewStudentRepository;
import com.GSBScholarship_API.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;





@Service
public class StudentLoginService {

	
	@Value("${smsSenderName}")
    private String smsSenderName;
	
	@Value("${lastdate}")
	private String lastdate;
	
    @Autowired
    private LoginRepo loginRepo;
	
    @Autowired
    private  OnlineNewStudentRepository newStudentRepository;
    
    @Autowired
    private StudentRepo stdRepo;
    
    
    public SampleResponseDto sendOTP(String mobileNo,String aadhar)
			throws MalformedURLException, IOException {
    	SampleResponseDto sampleResponseDto = new SampleResponseDto(); 
    	
			String lyear = this.getLastYear();	
			String currentAcademicyear = this.getCurrentYear();
			

			// online_new_student
			List<OnlineNewStudent> onlineNewStudentsOnMobileNumber = newStudentRepository.getNewMobileNoRecored(mobileNo);
			if (onlineNewStudentsOnMobileNumber == null || onlineNewStudentsOnMobileNumber.size() == 0) {
				sampleResponseDto.setId(1);
				sampleResponseDto.setMessage("Your mobile number or aadhar has not registered with us");
				sampleResponseDto.setStatus("Failed");
				sampleResponseDto.setStatusCode(200);
				return sampleResponseDto;
			}

			// online_new_student with aadhar
			List<OnlineNewStudent> onlineNewStudents = newStudentRepository.getNewMobileNoRecored(mobileNo, aadhar);
			System.out.println("currentAcademicyear  ==  " + currentAcademicyear);
			List<Login> logins = loginRepo.getLoginRecoredOnAadhar(currentAcademicyear, aadhar);
			Login onlineLogin = logins != null && logins.size() != 0 ? logins.get(0) : null;

			int count = stdRepo.getLoginCount(currentAcademicyear, aadhar);

			if (count > 1) {
				loginRepo.deleteLogin(currentAcademicyear, aadhar);
			}
			
			logins = loginRepo.getLoginRecoredOnAadhar(currentAcademicyear, aadhar);
			
			if (logins != null && logins.size() > 0) {
				// model.addAttribute("userForm", loginuser);
				System.out.println("logins.get(0).getSubmit()   " + logins.get(0).getSubmit());
				if (logins.get(0).getSubmit() == true) {
					
					sampleResponseDto.setId(1);
					sampleResponseDto.setMessage("Your form is already submitted !");
					sampleResponseDto.setStatus("Failed");
					sampleResponseDto.setStatusCode(200);
					return sampleResponseDto;
					
				}
			}
			if (onlineNewStudents != null && onlineNewStudents.size() > 0) {
				if (logins != null && logins.size() > 0) {
					// model.addAttribute("userForm", loginuser);
					System.out.println("logins.get(0).getSubmit()   " + logins.get(0).getSubmit());
					if (logins.get(0).getSubmit() == true) {
						
						sampleResponseDto.setId(1);
						sampleResponseDto.setMessage("Your form is already submitted !");
						sampleResponseDto.setStatus("Failed");
						sampleResponseDto.setStatusCode(200);
						return sampleResponseDto;
					} else {
						System.out.println("online_new_student  elseee");
						this.sendOtp(mobileNo, aadhar, onlineLogin);
						sampleResponseDto.setId(1);
						sampleResponseDto.setMessage("OTP sended successfuly");
						sampleResponseDto.setStatus("Success");
						sampleResponseDto.setStatusCode(200);
						return sampleResponseDto;
						

					}
					// model.addAttribute("userForm", logs);

				} else {
					System.out.println("online_new_student **************** is null" + onlineLogin);
					this.sendOtp(mobileNo, aadhar, onlineLogin);
					sampleResponseDto.setId(1);
					sampleResponseDto.setMessage("OTP sended successfuly");
					sampleResponseDto.setStatus("Success");
					sampleResponseDto.setStatusCode(200);
					return sampleResponseDto;

				}

			}

			List<Student> students = stdRepo.getMobileNoRecords(mobileNo, aadhar);
			if (onlineNewStudents == null || onlineNewStudents.size() == 0) {
				//System.out.println("students =======  " + students);

				if (students == null || students.size() == 0) {
					
					sampleResponseDto.setId(1);
					sampleResponseDto.setMessage("Your mobile number or aadhar has not registered with us");
					sampleResponseDto.setStatus("Failed");
					sampleResponseDto.setStatusCode(200);
					return sampleResponseDto;
				} else {
					System.out.println("online student ********************  " + onlineLogin);
					this.sendOtp(mobileNo, aadhar, onlineLogin);
					sampleResponseDto.setId(1);
					sampleResponseDto.setMessage("OTP sended successfuly");
					sampleResponseDto.setStatus("Success");
					sampleResponseDto.setStatusCode(200);
					return sampleResponseDto;
				}

			}
			return sampleResponseDto;
	}
    public String getLastYear() {
		Integer startYear = null;
		Integer endYear = null;
		LocalDate currentdate = LocalDate.now();
		Integer month = currentdate.getMonthValue();
		if (month <= 3) {
			startYear = currentdate.getYear() - 1;
			endYear = currentdate.getYear();
		} else {
			startYear = currentdate.getYear();
			endYear = currentdate.getYear() + 1;
		}
		startYear = startYear - 1;
		endYear = endYear - 1;
		String lyear = startYear.toString() + "-" + endYear.toString().substring(2);
		System.out.println("lyear  ==============================  " + lyear);
		return lyear;
	}
    
    
    
    
	public SampleResponseDto otpVerify(int otp,String mobileNo,String aadhar ) {
		
		SampleResponseDto sampleResponseDto = new SampleResponseDto();  
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
			simpleDateFormat.applyPattern("yyyy");
			String format = simpleDateFormat.format(new Date());
			int year = Integer.parseInt(format);

			String lyear = this.getLastYear();

			String cyear = this.getCurrentYear();

			
			boolean otpFlag = false;
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Login login = null;
			Login loginter = null;
			List<Login> logins = loginRepo.getLoginRecords(mobileNo, cyear, aadhar);
			Iterator<Login> iter = logins.iterator();
			while (iter.hasNext()) {
				loginter = iter.next();
				if (loginter.getSubmit() == true) {
					iter.remove();
				}
			}
			iter = logins.iterator();
			for (int i = 0; i < logins.size(); i++) {
				loginter = logins.get(i);
				System.out.println("loginter  === " + loginter);

				if (otp == loginter.getOtp() && (timestamp.getTime() - loginter.getOtptime()) < 60000) {
					otpFlag = true;
				}
			}

			if (otpFlag) {
				sampleResponseDto.setId(1);
				sampleResponseDto.setMessage("OTP verify successfuly");
				sampleResponseDto.setStatus("Success");
				sampleResponseDto.setStatusCode(200);
				return sampleResponseDto;
			} else {
				sampleResponseDto.setId(1);
				sampleResponseDto.setMessage("Please Enter Correct and Valid  PASSCODE !");
				sampleResponseDto.setStatus("Failed");
				sampleResponseDto.setStatusCode(200);
				return sampleResponseDto;
			}
	}
    
	public void sendOtp(String mobileno, String aadhar, Login login) throws MalformedURLException, IOException {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		int otp = ThreadLocalRandom.current().nextInt(100000, 999999 + 1);
//			String url = "https://api.mylogin.co.in/api/v2/SendSMS?ApiKey=5l4NwIzq4OXv69dq5gaYip0koE1ATfL0RIsLQQTzX7Y="
//					+ "&ClientId=b50b3dd7-b5b3-4b97-98ae-3852256ee261&SenderId="
//					+ smsSenderName
//					+ "&Message=Dear%20GSB%20Scholarship%20League%20Student,%20your%20OTP%20for%20ONline%20Application%20form%20is%20"
//					+ otp + "&MobileNumbers=91" + mobileno;

		String url = "https://api.mylogin.co.in/api/v2/SendSMS?ApiKey=5l4NwIzq4OXv69dq5gaYip0koE1ATfL0RIsLQQTzX7Y=&ClientId=b50b3dd7-b5b3-4b97-98ae-3852256ee261&SenderId="
				+ smsSenderName
				+ "&Message=Dear%20Gaud%20Saraswat%20Brahmin%20Scholarship%20League%20Student,%20your%20PASSCODE%20for%20Online%20"
				+ "Application%20form%20is%20" + otp + "%20-%20RVTTHP" + "&MobileNumbers=91" + mobileno;

		System.out.println(url);
		// String
		// url="http://bulksms.mysmsmantra.com:8080/WebSMS/SMSAPI.jsp?username="+smsUserName+"&password="+smsPassword+"&sendername="+smsSenderName+"&mobileno=91"+logs.getMobileNo()+"&message=Dear+GSB+Scholarship+League+Student,+your+OTP+for+Online+Application+form+is+"+logs.getOtp();
		try {
			URLConnection connection = new URL(url).openConnection();
			InputStream response = connection.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();

			// TODO: handle exception
		}
		if (login == null) {
			login = new Login();
			login.setName(aadhar);
			login.setMobileNo(mobileno);
			login.setOtptime(timestamp.getTime());
			login.setOtp(otp);
			login.setAccept(false);
			login.setSubmit(false);
			login.setScholYear(this.getCurrentYear());
		} else {
			login.setOtptime(timestamp.getTime());
			login.setOtp(otp);
		}
		loginRepo.save(login);

	}	
	
	public String getCurrentYear() {
		Integer startYear = null;
		Integer endYear = null;
		LocalDate currentdate = LocalDate.now();
		Integer month = currentdate.getMonthValue();
		if (month <= 3) {
			startYear = currentdate.getYear() - 1;
			endYear = currentdate.getYear();
		} else {
			startYear = currentdate.getYear();
			endYear = currentdate.getYear() + 1;
		}

		String lyear = startYear.toString() + "-" + endYear.toString().substring(2);
		System.out.println("lyear  ==============================  " + lyear);

		return lyear;
	}
	
	
	
	
}
