package com.GSBScholarship_API.repository;

import com.GSBScholarship_API.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer>{

}
