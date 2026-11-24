package com.GSBScholarship_API.service;

import com.GSBScholarship_API.dto.*;
import com.GSBScholarship_API.entity.*;
import com.GSBScholarship_API.repository.*;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.GZIPOutputStream;



@Service
public class StudentService {

	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private LoginRepo loginRepo;
	
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private ImageRepository images;
	
	@Autowired
	private OnlineNewStudentRepository newStudentRepository;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private StatusHistoryRepository statusHistoryRepository;
	
	public StudentApplicantListResponseDto dynamicSearch(String scholarshipYear, String course, String sclass, String status) {
		
		List<Student> applicantList = studentRepo.findByDynamicSearch(scholarshipYear, course, sclass, status);
		List<StudentApplicantListDto> applicantListDtos = new ArrayList<>();
        StudentApplicantListResponseDto applicantListResponseDto = new StudentApplicantListResponseDto();
		
		for(Student student:applicantList)
        {  
        	StudentApplicantListDto applicantListDto = new StudentApplicantListDto();
        	applicantListDto.setSrNo(student.getId());
        	applicantListDto.setName(student.getFirstName()+" "+student.getLastName());
        	applicantListDto.setDate(student.getSdate());
        	applicantListDto.setCategory(student.getCourse());
        	applicantListDto.setSclass(student.getSclass());
        	applicantListDto.setMobileNo(student.getMobileNo());
        	applicantListDto.setStatus(student.getStatus());
        	applicantListDto.setRemark(student.getNote());
        	applicantListDto.setCertificate(student.getImage()!=null?student.getImage().getCertificatePath():"");
        	applicantListDto.setMarksheet(student.getImage()!=null?student.getImage().getMarksheetPath():"");
        	applicantListDto.setStudentAadharcard(student.getImage()!=null?student.getImage().getAadharcardPath():"");
        	applicantListDto.setReceipt(student.getImage()!=null?student.getImage().getFeeReceptPath():"");
        	applicantListDtos.add(applicantListDto);
        }
       if(applicantList.size()!=0)
        	{
        	applicantListResponseDto.setId(1);
        	applicantListResponseDto.setMessage("Record's found");
        	applicantListResponseDto.setStatus("Success");
        	applicantListResponseDto.setStatusCode(200);
        	applicantListResponseDto.setApplicantList(applicantListDtos);
        	}
       else
       {
       	applicantListResponseDto.setId(1);
       	applicantListResponseDto.setMessage("Record's not found");
       	applicantListResponseDto.setStatus("Failed");
       	applicantListResponseDto.setStatusCode(200);   
       }
       return applicantListResponseDto;
    }
	
	public YearListResponseDto getYearList(){
		YearListResponseDto yearListResponseDto = new YearListResponseDto();
		List<String> yearlist =studentRepo.getYearList();
		
		if(yearlist != null && !yearlist.isEmpty())
    	{
			yearListResponseDto.setId(1);
			yearListResponseDto.setMessage("Record's found");
			yearListResponseDto.setStatus("Success");
			yearListResponseDto.setStatusCode(200);
			yearListResponseDto.setYearList(yearlist);
    	}
   else
   {
	   yearListResponseDto.setId(1);
	   yearListResponseDto.setMessage("Record's not found");
	   yearListResponseDto.setStatus("Failed");
	   yearListResponseDto.setStatusCode(200);   
   }
   return yearListResponseDto;
	}
	
	public StatusListResponseDto getStatusList(){
		StatusListResponseDto statusListResponseDto = new StatusListResponseDto();
		List<String> yearlist =statusRepository.getStatusList();
		
		if(yearlist != null && !yearlist.isEmpty())
    	{
			statusListResponseDto.setId(1);
			statusListResponseDto.setMessage("Record's found");
			statusListResponseDto.setStatus("Success");
			statusListResponseDto.setStatusCode(200);
			statusListResponseDto.setStatusList(yearlist);
    	}
   else
   {
	   statusListResponseDto.setId(1);
	   statusListResponseDto.setMessage("Record's not found");
	   statusListResponseDto.setStatus("Failed");
	   statusListResponseDto.setStatusCode(200);   
   }
   return statusListResponseDto;
	}
	
	public StudentFormDetailsResponseDto getStudentDetailsById(Integer id){
		StudentFormDetailsResponseDto studentFormDetailsResponseDto = new StudentFormDetailsResponseDto();
		  Student student  =studentRepo.findById(id).get();
		  StudentFormDetailsDTO formDetailsDTO = new StudentFormDetailsDTO();
		  formDetailsDTO.setScholarshipYear(student.getSyear());
		  formDetailsDTO.setFirstName(student.getFirstName());
		  formDetailsDTO.setMiddleName(student.getMiddleName());
		  formDetailsDTO.setLastName(student.getLastName());
		  formDetailsDTO.setDateOfBirth(student.getDob());
		  formDetailsDTO.setMobileNumber(student.getMobileNo());
		  formDetailsDTO.setEmailId(student.getEmailId());
		  formDetailsDTO.setGender(student.getGender());
		  formDetailsDTO.setAadharNumber(student.getAadhar());
		  formDetailsDTO.setTotalMarksInPreviousYear(student.getMarks());
		  formDetailsDTO.setPercentage(student.getPercentage());
		  formDetailsDTO.setFatherGuardianName(student.getFatherFirstName());
		  formDetailsDTO.setMotherName(student.getFatherLastName());
		  formDetailsDTO.setStdCode(student.getStd());
		  formDetailsDTO.setPhoneNumber(student.getTelephone());
		  formDetailsDTO.setParentsMobileNumber(student.getFatherMiddleName());
		  formDetailsDTO.setParentsAddress1(student.getFaddress());
		  formDetailsDTO.setAddressAndCity(student.getDistrict());
		  formDetailsDTO.setState(student.getFdistrict());
		  formDetailsDTO.setPincode(student.getFpin());
		  formDetailsDTO.setProfessionOrBusiness(student.getOccuption());
		  formDetailsDTO.setYearlyIncome(student.getIncome());
		  formDetailsDTO.setCourseOfStudy(student.getCourse());
		  formDetailsDTO.setClassStudying(student.getSclass());
		  formDetailsDTO.setFeesPaidForYear(student.getFees());
		  formDetailsDTO.setSchoolOrCollegeName(student.getSchoolName());
		  formDetailsDTO.setPrincipalName(student.getHeadMaster());
		  formDetailsDTO.setSchoolStdCode(student.getSstd());
		  formDetailsDTO.setSchoolPhoneNumber(student.getStelePhone());
		  formDetailsDTO.setSchoolAddress(student.getSaddress());
		  formDetailsDTO.setSchollAddressAndCity(student.getTown());
		  formDetailsDTO.setSchollState(student.getSdistrict());
		  formDetailsDTO.setSchollpincode(student.getSpin());
		  formDetailsDTO.setBankName(student.getBankName());
		  formDetailsDTO.setBranchName(student.getBranch());
		  formDetailsDTO.setAccountHolderName(student.getHolderName());
		  formDetailsDTO.setAccountNumber(student.getAcco());
		  formDetailsDTO.setIfscCode(student.getIfsc());
		  formDetailsDTO.setMicrCode(student.getMicr());
		  formDetailsDTO.setReceiptOfFeePaid(student.getImage()!=null?student.getImage().getFeeReceptPath():"");
		  formDetailsDTO.setMarksheet(student.getImage()!=null?student.getImage().getMarksheetPath():"");
		  formDetailsDTO.setStudyCertificate(student.getImage()!=null?student.getImage().getCertificatePath():"");
		  formDetailsDTO.setStudentAadharCard(student.getImage()!=null?student.getImage().getAadharcardPath():"");
		  formDetailsDTO.setApplicationStatus(student.getStatus());
		  formDetailsDTO.setRemark(student.getNote());
		
			studentFormDetailsResponseDto.setId(1);
			studentFormDetailsResponseDto.setMessage("Record's found");
			studentFormDetailsResponseDto.setStatus("Success");
			studentFormDetailsResponseDto.setStatusCode(200);
			studentFormDetailsResponseDto.setStudentFormDetails(formDetailsDTO);
			return studentFormDetailsResponseDto;
    	}
	
	public SampleResponseDto save(MultipartFile fee,MultipartFile certificate,String mobileno,MultipartFile marksheet,StudentFormDetailsDTO studentFormDetailsDTO,MultipartFile aadharcard,HttpServletRequest servletRequest) throws IOException {
		
			// Thread.sleep(20000);
		     SampleResponseDto sampleResponseDto = new SampleResponseDto();
            System.out.println("data ==  "+studentFormDetailsDTO.getMobileNumber()+" year ==  "+ studentFormDetailsDTO.getScholarshipYear()+"aadhar == "+studentFormDetailsDTO.getAadharNumber());
			List<Integer> logins = studentRepo.getLoginRecords(studentFormDetailsDTO.getMobileNumber(), this.getCurrentYear(),studentFormDetailsDTO.getAadharNumber());
			if (logins != null && logins.size() != 0) {
				sampleResponseDto.setId(1);
				sampleResponseDto.setMessage("Your form is already submitted !");
				sampleResponseDto.setStatus("Failed");
				sampleResponseDto.setStatusCode(200);
				return sampleResponseDto;
			}
			System.out.println("fee ============   " + fee.getOriginalFilename() + " "
					+ this.getExtention(fee.getOriginalFilename()));
			System.out.println("certificate ============   " + certificate.getOriginalFilename() + "  "
					+ this.getExtention(certificate.getOriginalFilename()));
			System.out.println("marksheet  ==========  " + marksheet.getOriginalFilename() + " "
					+ this.getExtention(marksheet.getOriginalFilename()));
			System.out.println("aadharcard ==========  " + aadharcard.getOriginalFilename() + " "
					+ this.getExtention(aadharcard.getOriginalFilename()));
			  Student stud = new Student();
			
			  System.out.println("studentFormDetailsDTO.getScholarshipYear()==="+studentFormDetailsDTO.getScholarshipYear());
			  
			  stud.setSyear(studentFormDetailsDTO.getScholarshipYear());
			  stud.setFirstName(studentFormDetailsDTO.getFirstName());
			  stud.setMiddleName(studentFormDetailsDTO.getMiddleName());
			  stud.setLastName(studentFormDetailsDTO.getLastName());
			  stud.setDob(studentFormDetailsDTO.getDateOfBirth());
			  stud.setMobileNo(studentFormDetailsDTO.getMobileNumber());
			  stud.setEmailId(studentFormDetailsDTO.getEmailId());
			  stud.setGender(studentFormDetailsDTO.getGender());
			  stud.setAadhar(studentFormDetailsDTO.getAadharNumber());
			  stud.setMarks(studentFormDetailsDTO.getTotalMarksInPreviousYear());
			  stud.setPercentage(studentFormDetailsDTO.getPercentage());
			  stud.setFatherFirstName(studentFormDetailsDTO.getFatherGuardianName());
			  stud.setFatherLastName(studentFormDetailsDTO.getMotherName());
			  stud.setStd(studentFormDetailsDTO.getStdCode());
			  stud.setTelephone(studentFormDetailsDTO.getPhoneNumber());
			  stud.setFatherMiddleName(studentFormDetailsDTO.getParentsMobileNumber());
			  stud.setFaddress(studentFormDetailsDTO.getParentsAddress1());
			  stud.setDistrict(studentFormDetailsDTO.getAddressAndCity());
			  stud.setFdistrict(studentFormDetailsDTO.getState());
			  stud.setFpin(studentFormDetailsDTO.getPincode());
			  stud.setOccuption(studentFormDetailsDTO.getProfessionOrBusiness());
			  stud.setIncome(studentFormDetailsDTO.getYearlyIncome());
			  stud.setCourse(studentFormDetailsDTO.getCourseOfStudy());
			  stud.setSclass(studentFormDetailsDTO.getClassStudying());
			  stud.setFees(studentFormDetailsDTO.getFeesPaidForYear());
			  stud.setSchoolName(studentFormDetailsDTO.getSchoolOrCollegeName());    
			  stud.setHeadMaster(studentFormDetailsDTO.getPrincipalName());   
			  stud.setSstd(studentFormDetailsDTO.getSchoolStdCode()) ;   
			  stud.setStelePhone(studentFormDetailsDTO.getSchoolPhoneNumber());
			  stud.setSaddress(studentFormDetailsDTO.getSchoolAddress());
			  stud.setTown(studentFormDetailsDTO.getSchollAddressAndCity());
			  stud.setSdistrict(studentFormDetailsDTO.getSchollState());
			  stud.setSpin(studentFormDetailsDTO.getSchollpincode());
			  stud.setBankName(studentFormDetailsDTO.getBankName());
			  stud.setBranch(studentFormDetailsDTO.getBranchName());
			  stud.setHolderName(studentFormDetailsDTO.getAccountHolderName());
			  stud.setAcco(studentFormDetailsDTO.getAccountNumber());
			  stud.setIfsc(studentFormDetailsDTO.getIfscCode());
			  stud.setMicr(studentFormDetailsDTO.getMicrCode());
			// system.out.println(aadharcard.getOriginalFilename());
			String serverPath = null;
			stud.setEligible(true);
			List<Login> log = null;
			Date d = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
			simpleDateFormat.applyPattern("yyyy");
			String format = simpleDateFormat.format(new Date());
			int year = Integer.parseInt(format);
			// String lyear = (year - 1) + "-" + (year - 2000);
			// String cyear = year + "-" + ((year + 1) - 2000);
			String lyear = this.getLastYear();
			String cyear = this.getCurrentYear();
			// Login log = loginRepo.findByName(stud.getFirstName().trim().toUpperCase());
			System.out.println("mobileno  -       " + cyear + "-------  " + mobileno);

			System.out.println(
					"stud.getFirstName().trim().toUpperCase()  --------  " + stud.getFirstName().trim().toUpperCase());
			log = loginRepo.getLoginRecords(mobileno, cyear,stud.getAadhar().trim());

			stud.setSdate(new Date());
			stud.setSyear(cyear);
			stud.setStatus("In Review");
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
			studentRepo.save(stud);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Student hotelsd = studentRepo.findById(studentRepo.getMaxId()).get();

			String feeExtention = "";
			String markExtention = "";
			String certExtention = "";
			String aadharExtention = "";
			String cancelledChequeExtention = "";

			double compressionFactor = 0.3;

			byte[] feeBytes = fee.getBytes();

			byte[] certificateBytes = certificate.getBytes();

			byte[] marksheetBytes = marksheet.getBytes();

			byte[] aadharcardBytes = aadharcard.getBytes();
//--------------converted to files to bytes------
			
			
			
			// byte[] cancelledChequeBytes = cancelledCheque.getBytes();

			String path = servletRequest.getServletContext().getRealPath("");
			
			System.out.println("----path----"+path);
			
			path = path.replace("/OnlineForm", "");

			serverPath = path + File.separator + "GsbImages" + File.separator + "Gsb";
			
			System.out.println("----serverPath----"+serverPath);

			File dir = new File(serverPath);
			
			if (!dir.exists())
				dir.mkdirs();
			
			String feePath = serverPath + File.separator + "Fee_" + studentFormDetailsDTO.getMobileNumber() + timestamp.getTime();
			// this.compressByteArray(feeBytes, feePath);

			String originalFilename = fee.getOriginalFilename();
			String extention = this.getExtention(originalFilename);
			feeExtention = extention;
			
			File outputFile = new File(feePath);
			extention = extention.toLowerCase();
			
			/// feePath = feePath + "." + extention;

			if (extention != null) {
				if (extention.equals("png") || extention.equals("jpeg") || extention.equals("jpg")) {
					System.out.println(fee.getOriginalFilename() + "  -    " + extention);
					feeBytes = this.compressImage(fee, compressionFactor);
				} else {
					System.out.println(fee.getOriginalFilename() + "  -    " + extention);

					feeBytes = fee.getBytes();
				}
			}
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(feePath)));
			stream.write(feeBytes);
			stream.close();

			originalFilename = marksheet.getOriginalFilename();
			extention = this.getExtention(originalFilename);
			String markshhetPath = serverPath + File.separator + "Marksheet_" + studentFormDetailsDTO.getMobileNumber()
					+ timestamp.getTime();
			markExtention = extention;

			// this.compressByteArray(marksheetBytes, markshhetPath);
			File outputFile1 = new File(markshhetPath);
			if (extention != null) {
				if (extention.equals("png") || extention.equals("jpeg") || extention.equals("jpg")) {
					System.out.println(marksheet.getOriginalFilename() + "  -    " + extention);
					marksheetBytes = this.compressImage(marksheet, compressionFactor);
				}

				else {
					System.out.println(marksheet.getOriginalFilename() + "  -    " + extention);
					marksheetBytes = marksheet.getBytes();

				}
			}
			BufferedOutputStream streamMarksheet = new BufferedOutputStream(
					new FileOutputStream(new File(markshhetPath)));
			streamMarksheet.write(marksheetBytes);
			streamMarksheet.close();

			originalFilename = certificate.getOriginalFilename();
			extention = this.getExtention(originalFilename);
			String certifiactePath = serverPath + File.separator + "Certificate_" + studentFormDetailsDTO.getMobileNumber()
					+ timestamp.getTime();
			// this.compressByteArray(certificateBytes, certifiactePath);
			certExtention = extention;

			System.out.println("certificate certExtention =========             " + certExtention);
			File outputFile2 = new File(certifiactePath);
			if (extention != null) {

				if (extention.equals("png") || extention.equals("jpeg") || extention.equals("jpg")) {
					System.out.println(certificate.getOriginalFilename() + "  -    " + extention);
					System.out.println("certExtention  iffffffff");

					certificateBytes = this.compressImage(certificate, compressionFactor);
				} else {
					System.out.println("certExtention  elseeee");
					System.out.println(certificate.getOriginalFilename() + "  -    " + extention);
					certificateBytes = certificate.getBytes();
				}
			}
			BufferedOutputStream streamCertifiacte = new BufferedOutputStream(
					new FileOutputStream(new File(certifiactePath)));
			streamCertifiacte.write(certificateBytes);
			streamCertifiacte.close();

			originalFilename = aadharcard.getOriginalFilename();
			extention = this.getExtention(originalFilename);
			aadharExtention = extention;
			String aadharcardPath = serverPath + File.separator + "AadharCard_" + studentFormDetailsDTO.getMobileNumber()
					+ timestamp.getTime();
			File outputFile3 = new File(aadharcardPath);
			if (extention != null) {
				if (extention.equals("png") || extention.equals("jpeg") || extention.equals("jpg")) {
					System.out.println(aadharcard.getOriginalFilename() + "  -    " + extention);

					aadharcardBytes = this.compressImage(aadharcard, compressionFactor);
				}

				else {
					System.out.println(aadharcard.getOriginalFilename() + "  -    " + extention);

					aadharcardBytes = aadharcard.getBytes();
				}
			} // this .compressByteArray(aadharcardBytes, aadharcardPath);

			BufferedOutputStream streamaadharcard = new BufferedOutputStream(
					new FileOutputStream(new File(aadharcardPath)));
			streamaadharcard.write(aadharcardBytes);
			streamaadharcard.close();

			// get Image Path
			String uri = servletRequest.getRequestURI();
			String url1 = servletRequest.getRequestURL().toString();
			url1 = url1.replace(uri, "");

			String feeReciptImagePath = url1 + File.separator + "GsbImages" + File.separator + "Gsb" + File.separator
					+ "Fee_" + studentFormDetailsDTO.getMobileNumber() + timestamp.getTime();

			String marksheetReciptImagePath = url1 + File.separator + "GsbImages" + File.separator + "Gsb"
					+ File.separator + "Marksheet_" + studentFormDetailsDTO.getMobileNumber() + timestamp.getTime();

			String certificateImagePath = url1 + File.separator + "GsbImages" + File.separator + "Gsb" + File.separator
					+ "Certificate_" + studentFormDetailsDTO.getMobileNumber() + timestamp.getTime();

			String aadharcardImagePath = url1 + File.separator + "GsbImages" + File.separator + "Gsb" + File.separator
					+ "AadharCard_" + studentFormDetailsDTO.getMobileNumber() + timestamp.getTime();

//			String CancelledChequePath = url1 + File.separator + "GsbImages" + File.separator + "Gsb" + File.separator
//					+ "CancelledCheque_" + stud.getMobileNo() + timestamp.getTime();

			System.out.println("feeReciptImagePath ===  " + feeReciptImagePath);

			System.out.println("marksheetReciptImagePath ===  " + marksheetReciptImagePath);

			System.out.println("certificateImagePath ===  " + certificateImagePath);

			System.out.println("aadharcardImagePath ===  " + aadharcardImagePath);

			Image image = new Image();
			image.setStudent(hotelsd);
			image.setInsertion_date(new Date());
			image.setFeeReceptPath(feeReciptImagePath);
			image.setMarksheetPath(marksheetReciptImagePath);
			// image.setCancelledChequePath(CancelledChequePath);
			System.out.println("----------------     " + certExtention);
			System.out.println("--------+++++++++--------     " + certificateImagePath);

//			if (certExtention.equals("pdf"))
//				certificateImagePath = certificateImagePath + ".pdf";

			image.setCertificatePath(certificateImagePath);
			image.setAadharcardPath(aadharcardImagePath);
			//// system.out.println(image);
			images.save(image);
			studentRepo.save(stud);
			System.out.println("updating the Online_login  " + studentFormDetailsDTO.getMobileNumber());

			for (int ij = 0; ij < log.size(); ij++) {
				Login l = log.get(ij);
				l.setSubmit(true);
				loginRepo.save(l);
			}
			System.out.println("updated the Online_login" + studentFormDetailsDTO.getMobileNumber());
			
			
			sampleResponseDto.setId(1);
			sampleResponseDto.setMessage("Record saved successfuly");
			sampleResponseDto.setStatus("Success");
			sampleResponseDto.setStatusCode(200);
			return sampleResponseDto;
	}
	
	
	public RegistrationListDto getRegistrationList(String shollershipyear,int page,int size)
	  {
		RegistrationListDto listDto = new RegistrationListDto();
		  Pageable pageable = PageRequest.of(page, size);
		  Page<OnlineNewStudent> list = newStudentRepository.getRigisterStudentData(shollershipyear,pageable);
		  
	      if(list!=null)
	      {
	    	  listDto.setId(1);  
	    	  listDto.setStatus("Success");
	    	  listDto.setStatusCode(200);
	    	  listDto.setMessage("Record found");
	    	  listDto.setRegistrationList(list);
			  return listDto;  
	      } else
	      {  
	    	  listDto.setId(-1);
	    	  listDto.setStatus("Failed");
	    	  listDto.setStatusCode(204);
	    	  listDto.setMessage("Record(s) not found");
			  return listDto;
	      
	      }
	      
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
	
	public byte[] compressPDF(byte[] inputPDFData, double compressionFactor) throws IOException {
		try (PDDocument document = PDDocument.load(inputPDFData)) {
			// Create a new ByteArrayOutputStream to store the compressed PDF data
			ByteArrayOutputStream compressedStream = new ByteArrayOutputStream();

			// Apply compression settings
			document.setAllSecurityToBeRemoved(true);
			PDDocumentCatalog catalog = document.getDocumentCatalog();
			catalog.setVersion("1.5");

			// Save the document with compression
			document.save(compressedStream);

			// Return the compressed PDF data as a byte array
			return compressedStream.toByteArray();
		}
	}

	public byte[] compressImage(MultipartFile inputImage, double compressionFactor) throws IOException {
		// Get the InputStream from the MultipartFile
		InputStream inputImageStream = inputImage.getInputStream();

		// Load the original image from the InputStream
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		Builder<? extends InputStream> builder = Thumbnails.of(inputImageStream).scale(compressionFactor);

		// Determine the output format based on the input image's content type
		String contentType = inputImage.getContentType();
		String outputFormat = "jpeg"; // Default to JPEG

		if (contentType != null) {
			if (contentType.equalsIgnoreCase("image/jpeg") || contentType.equalsIgnoreCase("image/jpg")) {
				outputFormat = "jpeg";
			} else if (contentType.equalsIgnoreCase("image/png")) {
				outputFormat = "png";
			}
		}
		System.out.println("output format ==    " + outputFormat);
		builder.outputFormat(outputFormat);

		builder.toOutputStream(outputStream);

		return outputStream.toByteArray();
	}

	public byte[] compressImagewithoutimgformat(MultipartFile inputImage, double compressionFactor) throws IOException {
		// Get the InputStream from the MultipartFile
		InputStream inputImageStream = inputImage.getInputStream();

		// Load the original image from the InputStream
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//	    Thumbnails.of(inputImageStream)
//	            .scale(compressionFactor)
//	            .toOutputStream(outputStream);
		Builder<? extends InputStream> builder = Thumbnails.of(inputImageStream).scale(compressionFactor);
		String contentType = inputImage.getContentType();
		if (contentType != null) {
			System.out.println("contentType  ------------   " + contentType);
			if (contentType.equalsIgnoreCase("image/jpeg") || contentType.equalsIgnoreCase("image/jpg")) {
				builder.outputFormat("jpeg");
			} else if (contentType.equalsIgnoreCase("image/png")) {
				builder.outputFormat("png");
			} else if (inputImage.getContentType() != null
					&& inputImage.getContentType().equalsIgnoreCase("image/jpeg")) {
				builder.outputFormat("jpeg");
			}
		}
		builder.toOutputStream(outputStream);
		// Return the compressed image as a byte array
		return outputStream.toByteArray();
	}

	public void compressImage1(MultipartFile inputImage, File outputFile, double compressionFactor) throws IOException {
		// Get the InputStream from the MultipartFile
		InputStream inputImageStream = inputImage.getInputStream();

		// Load the original image from the InputStream
		Thumbnails.of(inputImageStream)
				// Specify the desired compression factor (0.1 means 10% of the original size)
				.scale(compressionFactor)
				// Write the compressed image to the output file
				.toFile(outputFile);
	}

	public static void compressByteArray(byte[] inputBytes, String outputFilePath) throws IOException {
		try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(inputBytes);
				FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath);
				GZIPOutputStream gzipOutputStream = new GZIPOutputStream(fileOutputStream)) {

			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = byteArrayInputStream.read(buffer)) > 0) {
				gzipOutputStream.write(buffer, 0, bytesRead);
			}
		}
	}

	
	
	public String getExtention(String originalFilename) {
		String extention = "";
		int lastIndexOf = originalFilename != null ? originalFilename.lastIndexOf('.') : 0;
		if (lastIndexOf > 0) {
			extention = originalFilename.substring(lastIndexOf + 1);
			extention = extention.toLowerCase();
		}
		return extention;
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

	public SampleResponseDto review(JSONObject json) {
		
	SampleResponseDto 	sampleResponseDto = new SampleResponseDto();
	Integer studentId = Integer.parseInt(json.get("studentId").toString());
	String status	=json.get("Status").toString();
	String remarks	=json.get("remarks").toString();
	User user=userRepo.findByUsername(getCurrentUser());
	Student student=studentRepo.findById(studentId).get();
	
	student.setStatus(status);
	student.setUpdatedBy(user.getFirstName());
	student.setRemarks(remarks);
	student.setUpdatedDate(new Date());
	Student update = studentRepo.save(student);
	
	OnlineStatusHistory statusHistory = new OnlineStatusHistory();
	statusHistory.setStatus(status);
	statusHistory.setRemarks(remarks);
	statusHistory.setStudent(student);
	statusHistory.setUser(user);
	statusHistory.setCreatedDate(new Date());
	statusHistory.setCreatedBy(user.getFirstName());
	statusHistoryRepository.save(statusHistory);
		
	if(update!=null)
	  {
		  sampleResponseDto.setId(1);
		  sampleResponseDto.setStatus("Success");
		  sampleResponseDto.setStatusCode(200);
		  sampleResponseDto.setMessage("Status has been updated Successfully");
		  return sampleResponseDto;
	  }
	  else
		{
		  sampleResponseDto.setId(-1);
		  sampleResponseDto.setStatus("Failed");
		  sampleResponseDto.setStatusCode(204);
		  sampleResponseDto.setMessage("There is an error. Please contact development team.");
		  return sampleResponseDto;
		}	
	}
	
	private String getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

	public ReviewHistoryListDto getReviewHistoryList(Integer studentId) {
		ReviewHistoryListDto reviewHistoryListDto = new ReviewHistoryListDto();
		 List<ReviewHistoryDto> historyDtos = new ArrayList<ReviewHistoryDto>();
		 List<OnlineStatusHistory> statusHistoryList = statusHistoryRepository.getReviewHistoryList(studentId);
		 
		  for(OnlineStatusHistory history:statusHistoryList)
		  {
			  ReviewHistoryDto dto = new ReviewHistoryDto();
			  if(history.getStudent()!=null) {
				  dto.setStudent(history.getStudent().getId());
			  }
			  if(history.getUser()!=null) {
				  dto.setUser(history.getUser().getUserId());
				  dto.setReviewedBy(history.getUser().getFirstName());
			  }
			dto.setStatusHistoryId(history.getId());
			dto.setStatus(history.getStatus());
			dto.setRemarks(history.getRemarks());
			dto.setCreatedBy(history.getCreatedBy());
			dto.setCreatedDatetime(history.getCreatedDate());  
			historyDtos.add(dto);
		  }
		  if(statusHistoryList.size()!=0)
		  {
			  reviewHistoryListDto.setId(1);  
			  reviewHistoryListDto.setStatus("Success");
			  reviewHistoryListDto.setStatusCode(200);
			  reviewHistoryListDto.setMessage("Record found");
			  reviewHistoryListDto.setStatusHistoryList(historyDtos);
			  return reviewHistoryListDto;
		  }
		  else
		  {
			  reviewHistoryListDto.setId(-1);
			  reviewHistoryListDto.setStatus("Failed");
			  reviewHistoryListDto.setStatusCode(204);
			  reviewHistoryListDto.setMessage("Review history not available");
			  return reviewHistoryListDto;
		  }  
	}
}
