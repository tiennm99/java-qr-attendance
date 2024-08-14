package io.github.tiennm99.qrattendance;

import com.vaadin.flow.spring.annotation.EnableVaadin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.component.page.AppShellConfigurator;

@SpringBootApplication
@EnableVaadin
public class QRAttendanceApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(QRAttendanceApplication.class, args);
    }
}
