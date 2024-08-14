package io.github.tiennm99.qrattendance.configuration;

import io.github.tiennm99.qrattendance.ngrok.NgrokInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class WebConfig implements WebMvcConfigurer {

  private final NgrokInterceptor ngrokInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(ngrokInterceptor)
        .addPathPatterns(
            "/",
            "/ask-server-location",
            "/set-server-location",
            "/manage",
            "/submissions",
            "/download-submissions");
  }
}
