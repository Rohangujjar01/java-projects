package com.GSBScholarship_API.repository;

import com.GSBScholarship_API.entity.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepo extends CrudRepository<City, Long> {

	@Query(value="select * from cities where city_state=?1 ",nativeQuery=true)
	Iterable<City> getCity(String state);


	
}
