package com.GSBBackend.repository;

import com.GSBBackend.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status,Integer> {
	
	@Query(value = "SELECT * FROM status order by sort_order asc",nativeQuery = true)
	List<Status> getAllStatus();
	
	@Query(value = "SELECT * FROM status where status != 'All' order by sort_order asc",nativeQuery = true)
	List<Status> getAllStatusForupdate();
	
}
