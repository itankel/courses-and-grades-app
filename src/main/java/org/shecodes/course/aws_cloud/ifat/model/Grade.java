package org.shecodes.course.aws_cloud.ifat.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity(name="Grade")
@Table(name="grades")
@Data
@Slf4j
@NoArgsConstructor
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    private Integer gradeVal;

    //@ManyToOne annotation is associated with the Course class variable.
    //@JoinColumn annotation references the mapped column.
    @ManyToOne
    @JoinColumn(name = "course_id",
            referencedColumnName = "courseId",
            nullable = false)
    @JsonBackReference
    private Course course;

    public Grade(Integer gradeVal, Course course) {
        this.gradeVal = gradeVal;
        this.course = course;
    }


    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", gradeVal=" + gradeVal +
                ", course name =" + course.getCourseName() +
                ", course id =" + course.getCourseId() +
                '}';
    }
}

