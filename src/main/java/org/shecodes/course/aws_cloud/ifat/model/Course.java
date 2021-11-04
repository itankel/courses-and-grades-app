package org.shecodes.course.aws_cloud.ifat.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity(name = "Course")
@Table(name = "courses")
@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    @Column(
            name = "courseId",
           updatable = false
    )
    private Long courseId;
    private String courseName;
    private String courseImageUrl;
    private Integer numGrades;

    //Note: the @OneToMany annotation is used to define the property
    // grades that will be used to map the mappedBy variable.
    // That is why we have a property named “course” in the grade class
    @OneToMany(mappedBy = "course",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    @JsonManagedReference
    private List<Grade> grades = new ArrayList<>();

    public Course(String courseName, String courseImageUrl) {
        this.courseName = courseName;
        this.courseImageUrl = courseImageUrl;
        this.numGrades = 0;
    }

    //is this the right place for it or this is supposed to be in the service ?
    // this is only to be used by the gradeService that must to add itself to the course
    public void addGrade(Grade grade) {
        if (grade.getCourse() != null && courseId == grade.getCourse().getCourseId()) {
            numGrades += 1;
            grades.add(grade);
            log.debug("grades size is " + grades.size() + " where as numGrades is " + numGrades);
        }else{
            log.debug("course is null or not the same as "+this.courseId);
        }
    }


    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseImageUrl='" + courseImageUrl + '\'' +
                ", numGrades=" + numGrades +
                ", grades=" + grades +
                '}';
    }
}
