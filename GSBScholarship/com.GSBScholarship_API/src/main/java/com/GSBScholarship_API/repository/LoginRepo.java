package com.GSBScholarship_API.repository;

import com.GSBScholarship_API.entity.Login;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;




@Repository
public interface LoginRepo extends JpaRepository<Login, Integer>{

	Login findByMobileNo(String mobileNo);
	Login findByName(String name);
	Login findByOtp(int otp);
	
	@Query(value = "select * from online_login  where schol_year=:syear and name=:aadhar ",nativeQuery = true)
	public List<Login> getLoginRecoredOnAadhar(@Param("syear") String syear,@Param("aadhar") String aadhar); 
	
	
	@Modifying
	@Transactional
    @Query(value = "DELETE FROM online_login WHERE schol_year = :year AND name = :aadhar AND submit = 0",nativeQuery = true)
	public int deleteLogin(
        @Param("year") String scholYear,
        @Param("aadhar") String aadhar
    );
	
	@Query(value = "SELECT FROM online_login WHERE  schol_year = :scholYear AND name = :aadhar",nativeQuery = true)
	List<Login> getLoginRecordsOnAadhar(@Param("scholYear") String scholYear, @Param("aadhar") String aadhar);
	
	@Query(value ="SELECT * FROM online_login  WHERE mobile_no = :mobileNo AND schol_year = :scholYear AND name = :name",nativeQuery = true)
	    List<Login> getLoginRecords(@Param("mobileNo") String mobileNo, 
	                                @Param("scholYear") String scholYear, 
	                                @Param("name") String name);
}
