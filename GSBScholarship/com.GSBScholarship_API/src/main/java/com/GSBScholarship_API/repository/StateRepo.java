package com.GSBScholarship_API.repository;

import com.GSBScholarship_API.entity.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepo extends CrudRepository<State, Long> {
	
}
