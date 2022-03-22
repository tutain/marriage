package com.marriage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hu
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.marriage.dao"})
public class MarriageApplication {

  public static void main(String[] args) {
    SpringApplication.run(MarriageApplication.class, args);
  }
}
