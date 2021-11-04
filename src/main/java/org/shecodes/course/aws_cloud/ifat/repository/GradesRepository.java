package org.shecodes.course.aws_cloud.ifat.repository;

import org.shecodes.course.aws_cloud.ifat.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface GradesRepository extends JpaRepository<Grade,Long> {
}
