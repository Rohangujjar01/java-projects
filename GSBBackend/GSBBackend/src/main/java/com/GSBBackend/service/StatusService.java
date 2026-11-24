package com.GSBBackend.service;

import com.GSBBackend.dto.StatutsDto;
import com.GSBBackend.model.Status;
import com.GSBBackend.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {

	@Autowired
	private StatusRepository statusRepository;	
	
	
public StatutsDto getStatusList()	
{
	
	StatutsDto response = new StatutsDto();
	
	 List<Status> statusList = statusRepository.getAllStatus();

   	
   if(statusList.size()!=0)
   {
	response.setId(1);
	response.setStatus("Success");
	response.setStatusCode(200);
	response.setMessage("Record(s) found");
	response.setStatusList(statusList);
	}
		else
		{
			response.setId(-1);
			response.setStatus("Failed");
			response.setStatusCode(204);
			response.setMessage("Record not found");
		}
	
	return response;
	
}


public StatutsDto statusListForUpdate()	
{
	
	StatutsDto response = new StatutsDto();
	
	 List<Status> statusList = statusRepository.getAllStatusForupdate();

   	
   if(statusList.size()!=0)
   {
	response.setId(1);
	response.setStatus("Success");
	response.setStatusCode(200);
	response.setMessage("Record(s) found");
	response.setStatusList(statusList);
	}
		else
		{
			response.setId(-1);
			response.setStatus("Failed");
			response.setStatusCode(204);
			response.setMessage("Record not found");
		}
	
	return response;
	
}
}
