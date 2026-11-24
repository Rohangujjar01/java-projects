package com.GSBBackend.repository;

import com.GSBBackend.model.StatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusHistoryRepository extends JpaRepository<StatusHistory, Integer>{

	@Query(value = "SELECT * FROM status_history where profile_id= :profileId ORDER BY status_history_id DESC", nativeQuery = true)
	List<StatusHistory> getReviewHistoryList(@Param("profileId")Integer profileId );
		
}
