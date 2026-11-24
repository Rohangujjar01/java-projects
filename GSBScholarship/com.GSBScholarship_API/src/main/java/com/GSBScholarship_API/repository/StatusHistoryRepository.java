package com.GSBScholarship_API.repository;

import com.GSBScholarship_API.entity.OnlineStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository                                                                               
public interface StatusHistoryRepository extends JpaRepository<OnlineStatusHistory, Integer> {

	@Query(value="select * from online_status_history where student_id=:studendId order by id desc",nativeQuery = true)
	List<OnlineStatusHistory>  getReviewHistoryList(@Param("studendId") Integer studendId);	
}
