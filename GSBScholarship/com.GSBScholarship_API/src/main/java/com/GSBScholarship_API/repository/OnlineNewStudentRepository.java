package com.GSBScholarship_API.repository;

import com.GSBScholarship_API.entity.OnlineNewStudent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OnlineNewStudentRepository extends JpaRepository<OnlineNewStudent, Integer> {
    
	@Query(value ="select * from online_new_student  where mobile_no= :number ",nativeQuery = true)
	public List<OnlineNewStudent> getNewMobileNoRecored(@Param("number") String number);
	
	@Query(value ="select * from online_new_student  where aadhar= :aadhar and year= :year",nativeQuery = true)
	public OnlineNewStudent getAadharwithScholershipYear(@Param("aadhar") String aadhar,@Param("year") String year);
	
	@Query(value ="select * from online_new_student  where mobile_no=:number and aadhar=:aadhar",nativeQuery = true)
	public List<OnlineNewStudent> getNewMobileNoRecored(@Param("number") String number,@Param("aadhar") String aadhar);
	
	@Query(value ="select * from online_new_student  where year=:year order by id desc",nativeQuery = true)
	public Page<OnlineNewStudent> getRigisterStudentData(@Param("year") String year,Pageable pageable);
	
}

