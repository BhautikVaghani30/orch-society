package com.core.township.society;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.core.township.society", "com.core.township.common"})
//@SpringBootApplication
public class SocietyApplication {

  public static void main(String[] args) {
    SpringApplication.run(SocietyApplication.class, args);
  }
}
