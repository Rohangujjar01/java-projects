package com.GSBScholarship_API.repository;

import com.GSBScholarship_API.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;



@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
	
	@Query(value="SELECT * FROM online_student e WHERE " +
            "(:scholarshipYear IS NULL OR e.Scholarship_year = :scholarshipYear) AND " +
            "(:course IS NULL OR e.course = :course) AND " +
            "(:sclass IS NULL OR e.sclass = :sclass) AND " +
            "(:status IS NULL OR e.status = :status)",nativeQuery = true)
    List<Student> findByDynamicSearch(
            @Param("scholarshipYear") String scholarshipYear,
            @Param("course") String course,
            @Param("sclass") String sclass,
            @Param("status") String status );
	
	
	
	@Query(value="select  distinct Scholarship_year from online_student where Scholarship_year is not null",nativeQuery = true)
	List<String> getYearList();
	
	
	
	Student findByMobileNo(String mobileNo);

	List<Student> findByCourse(String course);

	List<Student> findBySyear(String syear);

	List<Student> findBySclass(String sclass);

	List<Student> findByStatus(String status);

	Student findById(int sd);



	@Query(value = "SELECT first_name FROM online_new_student WHERE mobile_no = :mobileNo and aadhar=:aadhar", nativeQuery = true)
	List<String> getNewMobileNoRecordedNames(@Param("mobileNo") String mobileNo, @Param("aadhar") String aadhar);

	@Transactional
	@Modifying	
	@Query(value = "delete    from online_login  where schol_year=:year and name=:aadhar and submit =0 ", nativeQuery = true)
	int deleteLogin(@Param("year") String year, @Param("aadhar") String aadhar);
	

	@Query(value = "select count(*)  from online_login  where schol_year=:year and name=:aadhar and submit =0 ", nativeQuery = true)
	int getLoginCount(@Param("year") String year, @Param("aadhar") String aadhar);
	
	
	@Query(value = "select  id from online_login  where mobile_no = :mobileNo and schol_year=:year and name=:aadhar and submit =1 ", nativeQuery = true)
	List<Integer> getLoginRecords(@Param("mobileNo") String mobileNo,@Param("year") String year, @Param("aadhar") String aadhar);
	
	@Query(value ="SELECT * FROM online_login  WHERE mobile_no = :mobileNo AND name = :aadhar", nativeQuery = true)
	List<Student> getMobileNoRecords(@Param("mobileNo") String mobileNo, @Param("aadhar") String aadhar);
	
	@Query(value ="select max(id) from online_student",nativeQuery = true)
	Integer getMaxId();
	
	
	 @Query(value = "SELECT MONTHNAME(CreatedDateTime) AS month_name, COUNT(*) AS row_count " +
             "FROM online_student " +
             "WHERE Scholarship_year = :scholarshipYear " +
             "GROUP BY MONTH(CreatedDateTime), MONTHNAME(CreatedDateTime) " +
             "ORDER BY MONTH(CreatedDateTime)", nativeQuery = true)
      List<Object[]> getMonthlyStudentCount(@Param("scholarshipYear") String scholarshipYear);
	
      @Query(value = "SELECT gender, course, COUNT(*) AS record_count " +
              "FROM online_student " +
              "WHERE Scholarship_year = :scholarshipYear " +
              "GROUP BY gender, course " +
              "ORDER BY gender, course", nativeQuery = true)
      List<Object[]> getGenderCourseCount(@Param("scholarshipYear") String scholarshipYear);
      
      @Query(value = "SELECT course, COUNT(*) AS record_count, " +
              "ROUND((COUNT(*) * 100.0 / (SELECT COUNT(*) FROM tth.online_student)), 2) AS percentage " +
              "FROM online_student " +
              "WHERE Scholarship_year = :scholarshipYear " +
              "GROUP BY course " +
              "ORDER BY course", nativeQuery = true)
        List<Object[]> getCourseRecordPercentage(@Param("scholarshipYear") String scholarshipYear);
	
        @Query(value = "SELECT gender, COUNT(*) AS record_count, " +
                "ROUND((COUNT(*) * 100.0 / (SELECT COUNT(*) FROM tth.online_student)), 2) AS percentage " +
                "FROM online_student " +
                "WHERE Scholarship_year = :scholarshipYear " +
                "GROUP BY gender " +
                "ORDER BY gender", nativeQuery = true)
        List<Object[]> getGenderRecordPercentage(@Param("scholarshipYear") String scholarshipYear);
        
        @Query(value = "SELECT course AS Course, " +
                "COUNT(CASE WHEN status = 'In Review' THEN 1 END) AS 'In Review', " +
                "COUNT(CASE WHEN status = 'Accepted' THEN 1 END) AS Accepted, " +
                "COUNT(CASE WHEN status = 'Rejected' THEN 1 END) AS Rejected, " +
                "COUNT(CASE WHEN status = 'PCT Sponsored Student' THEN 1 END) AS 'PCT Sponsored Student', " +
                "COUNT(CASE WHEN status = 'Individual Sponsored Student' THEN 1 END) AS 'Individual Sponsored Student', " +
                "COUNT(CASE WHEN status = 'To be considered' THEN 1 END) AS 'To be considered' " +
                "FROM online_student " +
                "WHERE Scholarship_year = :scholarshipYear " +
                "GROUP BY course " +
                "ORDER BY course", nativeQuery = true)
       List<Object[]> getCourseStatusCounts(@Param("scholarshipYear") String scholarshipYear);
       
       @Query(value = "SELECT status AS 'Application status', " +
               "COUNT(*) AS record_count, " +
               "ROUND((COUNT(*) * 100.0 / (SELECT COUNT(*) FROM tth.online_student WHERE Scholarship_year = :scholarshipYear)), 2) AS percentage " +
               "FROM online_student " +
               "WHERE Scholarship_year = :scholarshipYear " +
               "GROUP BY status " +
               "ORDER BY status", nativeQuery = true)
      List<Object[]> getApplicationStatusCounts(@Param("scholarshipYear") String scholarshipYear);
      
      @Query(value = "SELECT count(*) FROM tth.online_student WHERE Scholarship_year = :scholarshipYear", nativeQuery = true)
      long getStudentCountByScholarshipYear(@Param("scholarshipYear") String scholarshipYear);
}

	
	
	
	

