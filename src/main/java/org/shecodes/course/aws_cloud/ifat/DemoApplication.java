package org.shecodes.course.aws_cloud.ifat;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.shecodes.course.aws_cloud.ifat.model.Course;
import org.shecodes.course.aws_cloud.ifat.model.Grade;
import org.shecodes.course.aws_cloud.ifat.model.dto.GradeDTO;
import org.shecodes.course.aws_cloud.ifat.repository.CoursesRepository;
import org.shecodes.course.aws_cloud.ifat.repository.GradesRepository;
import org.shecodes.course.aws_cloud.ifat.service.CoursesService;
import org.shecodes.course.aws_cloud.ifat.service.GradeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.Repository;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@Slf4j
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	// for initiation
	@Bean
	CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Let's inspect the beans provided by Spring Boot:");
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

			CoursesRepository coursesRepository= (CoursesRepository) ctx.getBean("coursesRepository");

			Faker faker = new Faker();
			String courseGrade = faker.name().title();
			Course course = new Course("Clean Code",faker.company().url());

			coursesRepository.save(course);
			coursesRepository.findAll();
			Grade grade1= new Grade( faker.number().numberBetween(0,100),course);
			Grade grade2= new Grade( faker.number().numberBetween(0,100),course);
			Grade grade3= new Grade( faker.number().numberBetween(0,100),course);
			GradesRepository gradesRepository= (GradesRepository) ctx.getBean("gradesRepository");


			List<Course> retVal = coursesRepository.findAll();
			List<Grade> retValG = gradesRepository.findAll();
			log.debug("findall after grade 1 " + retVal.size());
			retValG = gradesRepository.findAll();
			log.debug("findall after grade 2 " + retValG.size());
			retValG = gradesRepository.findAll();
			log.debug("findall after grade 3 " + retValG.size());

			Course course2 = new Course("Think and Grow Rich ",faker.company().url());
			coursesRepository.save(course2);

			CoursesService coursesService = (CoursesService) ctx.getBean("coursesService");
			GradeService gradeService = (GradeService) ctx.getBean("gradeService");
		};
	}

}
