package com.GSBScholarship_API.repository;

import com.GSBScholarship_API.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer>{

	@Query(value = "select status from online_status order by sort_order asc",nativeQuery = true)
	List<String> getStatusList();
}
