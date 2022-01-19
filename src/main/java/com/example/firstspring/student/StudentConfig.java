package com.example.firstspring.student;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;


@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner CommandLineRunner(
            StudentRepository repository) {
        return args -> {
            Student Mariem = new Student(
                    "Mariem",
                    "Mariem@gmail.com",
                    LocalDate.of(2000, JANUARY, 5)

            );
            Student Alex = new Student(
                    "Alex",
                    "Alex@gmail.com",
                    LocalDate.of(2000, FEBRUARY, 5)
            );
            repository.saveAll(
                    List.of(Mariem, Alex)
            );
        };
    }
}
