package org.shecodes.course.aws_cloud.ifat.control;


import lombok.extern.slf4j.Slf4j;
import org.shecodes.course.aws_cloud.ifat.model.Course;
import org.shecodes.course.aws_cloud.ifat.model.CourseImage;
import org.shecodes.course.aws_cloud.ifat.model.Grade;
import org.shecodes.course.aws_cloud.ifat.model.dto.GradeDTO;
import org.shecodes.course.aws_cloud.ifat.service.CoursesService;
import org.shecodes.course.aws_cloud.ifat.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
@Slf4j
public class AppFlowController {


    @Autowired
    private CoursesService courseService;
    @Autowired
    private GradeService gradeService;

    @GetMapping("/iamup")
    public String iAmUp() {
        return "I am up";
    }

    @GetMapping("/courses")
    public List<Course> getAllCourse() {
        return courseService.getAllCourse();
    }

    @GetMapping("/grades")
    public List<Grade> getAllGrade() {
        return gradeService.getAllGrades();
    }

    @GetMapping(value = "/course/{courseId}")
    public Course getCourse(@PathVariable Long courseId) {
        return courseService.getCourse(courseId);
    }

    @GetMapping(value = "/course/{courseId}/avarage")
    public double getCourseAvarage(@PathVariable Long courseId) {
        return courseService.getCourseAverage(courseId);
    }

    @PostMapping(value = "/course")
    public Course addCourse(@RequestBody CourseImage newCourse) {
        log.debug("Course name to add > " + newCourse.getCourseName());
        return courseService.addCourse(newCourse);
    }

    @PostMapping(value = "/grade")
    public Grade addGrade(@RequestBody GradeDTO newGrade) {
        log.debug("newGrade to add> " + newGrade);
        return gradeService.addGrade(newGrade);

    }
}
