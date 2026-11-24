package com.GSBBackend.repository;

import com.GSBBackend.model.Profile;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

String fields = null;

//	@Query(value = "SELECT * FROM profile order by id desc;",nativeQuery = true)
//	Page<Profile> getAllProfileList(Pageable pageable);
	
     @Query(value = "SELECT * FROM profile WHERE (:specialization = 'All' OR specialization = :specialization) and (:status = 'All' OR status = :status) ORDER BY id DESC", nativeQuery = true)
     Page<Profile> findAllBySpecialization(@Param("specialization") String specialization,@Param("status") String status, Pageable pageable);
	 
	 @Query(value="select * from profile where ima_ida=:imaIda",nativeQuery = true)
	 Profile findByImaIda(@Param("imaIda") String imaIda);
	 
	 @Query(value="select :fields from profile",nativeQuery = true)
	 List<Object[]> getProfileBasedOnFields(@Param("fields") String fields);
	 
	 @Query(value="SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'profile' ORDER BY ORDINAL_POSITION",nativeQuery = true)
	 List<String> getFieldsAndViewedValue();
}
