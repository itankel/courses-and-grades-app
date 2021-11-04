package org.shecodes.course.aws_cloud.ifat.repository;

import org.shecodes.course.aws_cloud.ifat.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CoursesRepository extends JpaRepository<Course,Long> {
}
