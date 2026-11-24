package com.GSBScholarship_API.repository;

import com.GSBScholarship_API.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepo extends CrudRepository<Category, Long> {



	
}
