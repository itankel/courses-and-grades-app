package org.shecodes.course.aws_cloud.ifat.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@ToString
@AllArgsConstructor
public class GradeDTO{

    private Long courseId;
    private Integer gradeVal;
}
