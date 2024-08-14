package io.github.tiennm99.qrattendance.ngrok;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class NgrokInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    var requestUrl = request.getRequestURL().toString();
    if (requestUrl.contains("ngrok")) {
      response.sendRedirect("/access-denied");
      return false;
    }
    return true;
  }
}
