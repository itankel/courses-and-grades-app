package org.shecodes.course.aws_cloud.ifat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
public class CourseImage{
    private String courseName;
    private String imageFormat;
    private String courseImage;
}
