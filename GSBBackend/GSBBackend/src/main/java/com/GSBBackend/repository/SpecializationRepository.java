package com.GSBBackend.repository;

import com.GSBBackend.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {


	@Query(value = "SELECT * FROM medical_specializations ORDER BY specialization ASC", nativeQuery = true)
	List<Specialization> getAllSpecializationList();
	

}
