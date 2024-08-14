package io.github.tiennm99.qrattendance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class QRAttendanceApplication {

  public static void main(String[] args) {
    SpringApplication.run(QRAttendanceApplication.class, args);
  }
}
