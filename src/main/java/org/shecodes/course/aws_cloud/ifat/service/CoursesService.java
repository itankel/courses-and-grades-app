package org.shecodes.course.aws_cloud.ifat.service;


import lombok.extern.slf4j.Slf4j;
import org.shecodes.course.aws_cloud.ifat.model.Course;
import org.shecodes.course.aws_cloud.ifat.model.CourseImage;
import org.shecodes.course.aws_cloud.ifat.model.Grade;
import org.shecodes.course.aws_cloud.ifat.model.dto.CourseImageDTO;
import org.shecodes.course.aws_cloud.ifat.repository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CoursesService {

    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private ImagesLoader imagesLoader;


    public List<Course> getAllCourse(){
        return coursesRepository.findAll();
    }
    public Course getCourse(Long courseId){
        Course emptyNone = new Course();
        return coursesRepository.findById(courseId).orElse(emptyNone);
    }

    public double getCourseAverage(Long courseId){
        Course emptyNone = new Course();
        Course foundCourse=coursesRepository.findById(courseId).orElse(emptyNone);
        return foundCourse.getGrades().stream().mapToInt(Grade::getGradeVal).average().orElse(0);
    }

    public Course addCourse(CourseImage newCourse){
        if(newCourse.getCourseImage()!=null && newCourse.getCourseImage().length()!=0) {
            // forward the request to the api gateway and wait for the url as a response
            // shall be from S3
            String imageUrl = imagesLoader.loadImage(new CourseImageDTO(newCourse));
            log.debug("imageUrl =" + imageUrl);
            // create new course with the name and url received
            Course course = new Course(newCourse.getCourseName(), imageUrl);
            return coursesRepository.save(course);
        }
        return new Course();
    }

}
