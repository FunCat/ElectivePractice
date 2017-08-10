package com.epam.electives.dao;

import com.epam.electives.dto.GetEntityRequest;
import com.epam.electives.dto.PageDto;
import com.epam.electives.model.Course;
import com.epam.electives.model.Group;
import com.epam.electives.model.UserProfile;

import java.util.List;

/**
 * Created by rusamaha on 7/19/17.
 */
public interface CourseDao {

    List<Course> findAll();

    Course findCourseById(Long id);

    void saveOrUpdate(Course course);

    PageDto<Course> findParts(GetEntityRequest request);

    PageDto<Course> findByTeacher(GetEntityRequest request, UserProfile userProfile);

    PageDto<Course> getActiveCoursesByTeacher(GetEntityRequest request, Long teacherId);

    List<Course> findN(int n);

    List<UserProfile> findStudentsByCourse(Course course);

    PageDto<Course> findCoursesByStudent (GetEntityRequest request, UserProfile userProfile);

    PageDto<Course> getCoursesByTag (int columSorting, boolean desc, String tag, GetEntityRequest request);

    PageDto<Group> getPartOfStudentsByCourse (GetEntityRequest request, Long id);


}
