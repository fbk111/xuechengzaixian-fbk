package com.fbk;


import com.fbk.content.service.CourseBaseService;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSwagger2Doc
@SpringBootApplication
public class ContentApplication {



    public static void main(String[] args) {
        SpringApplication.run(ContentApplication.class, args);
    }

}
