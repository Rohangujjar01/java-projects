package com.GSBBackend.service;

import com.GSBBackend.dto.*;
import com.GSBBackend.model.Profile;
import com.GSBBackend.model.Specialization;
import com.GSBBackend.model.StatusHistory;
import com.GSBBackend.model.User;
import com.GSBBackend.repository.ProfileRepository;
import com.GSBBackend.repository.SpecializationRepository;
import com.GSBBackend.repository.StatusHistoryRepository;
import com.GSBBackend.repository.UserRepo;
import jakarta.mail.MessagingException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private StatusHistoryRepository historyRepository;
	
	@Autowired
	private SpecializationRepository specializationRepository;
	
	@Autowired
    private EmailService emailService;
	
	
  public  SampleResponseDto review(JSONObject json) throws MessagingException
  {
	  SampleResponseDto sampleResponseDto = new SampleResponseDto();
	  User user =userRepo.findByEmail(getCurrentUser());
	  
	    Integer profileId= Integer.parseInt(json.get("ProfileId").toString());
		String status   = json.get("Status").toString();
		String comments =json.get("Comments").toString();
		
	    Profile profile = profileRepository.findById(profileId).get();
	   String previousStatus= profile.getStatus();
	    profile.setImaIda(json.get("IMA_IDA").toString());
		profile.setTitle(json.get("title").toString());
		profile.setCity(json.get("city").toString());
		profile.setClinicAddress(json.get("clinicAddress").toString());
		profile.setDescription(json.get("description").toString());
		profile.setEmail(json.get("email").toString());
		profile.setSpecialization(json.get("specialization").toString());
		profile.setFacebook(json.get("facebook").toString());
		profile.setGender(json.get("gender").toString());
		profile.setLinkedin(json.get("linkedin").toString());
		profile.setMobileNumber(json.get("mobileNumber").toString());
		profile.setName(json.get("name").toString());
		profile.setProfilePhoto(json.get("profilePhoto").toString());
		profile.setTotalExperience(json.get("totalExperience").toString());
		profile.setWebsite(json.get("website").toString());
		profile.setSunday(json.get("sunday").toString());
		profile.setMonday(json.get("monday").toString());
		profile.setTuesday(json.get("tuesday").toString());
		profile.setWednesday(json.get("wednesday").toString());
		profile.setThursday(json.get("thursday").toString());
		profile.setFriday(json.get("friday").toString());
		profile.setSaturday(json.get("saturday").toString());
		profile.setPincode(json.get("pincode").toString());
		profile.setComments(comments);
		profile.setStatus(status);
		profile.setUpdatedBy(user.getFirstName());
		profile.setUpdatedDatetime(new Date());
		Profile update =profileRepository.save(profile);
		if(!previousStatus.equals(status))
		{
	  StatusHistory history = new StatusHistory();
	  history.setStatus(status);
	  history.setComments(comments);
	  history.setProfile(profile);
	  history.setUser(user);
	  history.setCreatedBy(user.getFirstName());
	  history.setCreatedDatetime(new Date());
	  historyRepository.save(history);
	  String email=profile.getEmail();
	  Context context = new Context();
	  String subject=null;
	  String template=null;
	  if(status.equals("Rejected")) {
		  subject="GSB Sabha Mumbai and GSBS Medical Trust - Status - Rejected";
		  template="rejected-email-template";
		  context.setVariable("reason",comments);
		  
	  }
	  else if(status.equals("Accepted")) {
		  subject="GSB Sabha Mumbai and GSBS Medical Trust - Status - Approved";
		  template="approved-email-template";
	  }
	  if(status.equals("Rejected")||status.equals("Accepted")) {
		  emailService.sendEmailWithTemplate(email,subject,template, context);
		  }
	}
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
  
  public SpecializationListDTO getSpecializationList()
  {
	  SpecializationListDTO specializationListDTO = new SpecializationListDTO();
	  
	 List<Specialization> specializationsList= specializationRepository.getAllSpecializationList();
	  
	  if(specializationsList.size()!=0)
	  {
		  specializationListDTO.setId(1);  
		  specializationListDTO.setStatus("Success");
		  specializationListDTO.setStatusCode(200);
		  specializationListDTO.setMessage("Record(s) found");
		  specializationListDTO.setSpecializationList(specializationsList);
		  return specializationListDTO;
	  }
	  else
	  {
		  specializationListDTO.setId(-1);
		  specializationListDTO.setStatus("Failed");
		  specializationListDTO.setStatusCode(204);
		  specializationListDTO.setMessage("Record not found");
		  return specializationListDTO;
	  }
	  
	  
  }
  
  
  public SampleResponseDto addProfile(JSONObject json) throws MessagingException
  {
	  SampleResponseDto sampleResponseDto = new SampleResponseDto();
	  Profile profile = new Profile();
	  Profile existsProfile=profileRepository.findByImaIda(json.get("IMA_IDA").toString());
	  if(existsProfile!=null)
	  {
		  sampleResponseDto.setId(-1);
		  sampleResponseDto.setStatus("Failed");
		  sampleResponseDto.setStatusCode(204);
		  sampleResponseDto.setMessage("Profile with IMA IDA already exists");
		  return sampleResponseDto;
		  
	  }
		profile.setImaIda(json.get("IMA_IDA").toString());
		profile.setTitle(json.get("title").toString());
		profile.setCity(json.get("city").toString());
		profile.setClinicAddress(json.get("clinicAddress").toString());
		profile.setDescription(json.get("description").toString());
		profile.setEmail(json.get("email").toString());
		profile.setSpecialization(json.get("specialization").toString());
		profile.setFacebook(json.get("facebook").toString());
		profile.setGender(json.get("gender").toString());
		profile.setLinkedin(json.get("linkedin").toString());
		profile.setMobileNumber(json.get("mobileNumber").toString());
		profile.setName(json.get("name").toString());
		profile.setProfilePhoto(json.get("profilePhoto").toString());
		profile.setTotalExperience(json.get("totalExperience").toString());
		profile.setWebsite(json.get("website").toString());
		profile.setSunday(json.get("sunday").toString());
		profile.setMonday(json.get("monday").toString());
		profile.setTuesday(json.get("tuesday").toString());
		profile.setWednesday(json.get("wednesday").toString());
		profile.setThursday(json.get("thursday").toString());
		profile.setFriday(json.get("friday").toString());
		profile.setSaturday(json.get("saturday").toString());
		profile.setCreatedBy(json.get("name").toString());
		profile.setPincode(json.get("pincode").toString());
		profile.setCreatedDatetime(new Date());
		profile.setStatus("In Review");
		Profile newProfile = profileRepository.save(profile);
		StatusHistory history = new StatusHistory();
		  history.setStatus("In Review");
		  history.setProfile(newProfile);
		  history.setCreatedDatetime(new Date());
		  history.setCreatedBy(newProfile.getName());
		  historyRepository.save(history);
		
		  if(newProfile!=null)
		  {
			  sampleResponseDto.setId(1);
			  sampleResponseDto.setStatus("Success");
			  sampleResponseDto.setStatusCode(200);
			  sampleResponseDto.setMessage("Profile data has been saved Successfully");
			  Context context = new Context();
			  emailService.sendEmailWithTemplate(json.get("email").toString(),"GSB Sabha Mumbai and GSBS Medical Trust - Status - In Review","inReview-email-template", context);
			  return sampleResponseDto;
		  }else
		{
			  sampleResponseDto.setId(-1);
			  sampleResponseDto.setStatus("Failed");
			  sampleResponseDto.setStatusCode(204);
			  sampleResponseDto.setMessage("There is an error. Please contact Admin.");
			  return sampleResponseDto;
		}
		  
		  
  }
  
  public ReviewHistoryListDto getReviewHistoryList(Integer profileId)
  {
	  ReviewHistoryListDto reviewHistoryListDto = new ReviewHistoryListDto();
	 List<ReviewHistoryDto> historyDtos = new ArrayList<ReviewHistoryDto>();
	 List<StatusHistory> statusHistoryList = historyRepository.getReviewHistoryList(profileId);
	  for(StatusHistory history:statusHistoryList)
	  {
		  ReviewHistoryDto dto = new ReviewHistoryDto();
		  if(history.getProfile()!=null) {
			  dto.setProfile(history.getProfile().getId());
		  }
		  if(history.getUser()!=null) {
			  dto.setUser(history.getUser().getUserId());
			  dto.setReviewedBy(history.getUser().getEmail());
		  }
		dto.setStatusHistoryId(history.getStatusHistoryId());
		dto.setComments(history.getComments());
		dto.setStatus(history.getStatus());
		dto.setCreatedBy(history.getCreatedBy());
		dto.setCreatedDatetime(history.getCreatedDatetime());  
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
 
  public ProfileListDto getList(String specialization,String status,int page,int size)
  {
	  ProfileListDto profileListDto = new ProfileListDto();
	  Pageable pageable = PageRequest.of(page, size);
      Page<Profile> profiles = profileRepository.findAllBySpecialization(specialization,status,pageable);
	  
      if(profiles!=null)
      {
    	  profileListDto.setId(1);  
    	  profileListDto.setStatus("Success");
    	  profileListDto.setStatusCode(200);
    	  profileListDto.setMessage("Record found");
    	  profileListDto.setProfiles(profiles);
		  return profileListDto;  
      } else
      {  
      
    	  profileListDto.setId(-1);
    	  profileListDto.setStatus("Failed");
    	  profileListDto.setStatusCode(204);
    	  profileListDto.setMessage("Record(s) not found");
		  return profileListDto;
      
      }
      
  }

  private String getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}
	
}
