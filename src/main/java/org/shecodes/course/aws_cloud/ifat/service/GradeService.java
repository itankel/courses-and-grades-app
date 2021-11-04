package org.shecodes.course.aws_cloud.ifat.service;

import lombok.extern.slf4j.Slf4j;
import org.shecodes.course.aws_cloud.ifat.model.Course;
import org.shecodes.course.aws_cloud.ifat.model.Grade;
import org.shecodes.course.aws_cloud.ifat.model.dto.GradeDTO;
import org.shecodes.course.aws_cloud.ifat.repository.CoursesRepository;
import org.shecodes.course.aws_cloud.ifat.repository.GradesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class GradeService {
    @Autowired
    private GradesRepository gradesRepository;
    @Autowired
    private CoursesRepository courseRepository;

    public List<Grade> getAllGrades() {
        return gradesRepository.findAll();
    }

    public Grade addGrade(GradeDTO gradeDTO) {
        if (gradeDTO.getGradeVal()<=0 || gradeDTO.getCourseId() <=0){
            log.error(" Bad Grade request"+gradeDTO);
            return new Grade();
        }
        // make sure to find the courseId that the grade refers to
        Optional<Course> courseFound = courseRepository.findById(gradeDTO.getCourseId());
        // if course in DB then
        // create new  Grade with the foundCourse
        // and call the private addGrade
        // add the grade to grade repository
        // update the foundCourse with the new grade and add count
        Grade newGrade=new Grade(gradeDTO.getGradeVal(),courseFound.get());
        // add the grade to grade repository
        gradesRepository.save(newGrade);
        // update the foundCourse with the new grade and add count
        newGrade.getCourse().addGrade(newGrade);
        //update the repository with the changed course
        courseRepository.save(newGrade.getCourse());
        return newGrade;

    }

}
