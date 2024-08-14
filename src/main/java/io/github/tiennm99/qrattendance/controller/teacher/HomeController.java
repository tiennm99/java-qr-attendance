package io.github.tiennm99.qrattendance.controller.teacher;

import io.github.tiennm99.qrattendance.data.ApplicationData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class HomeController {

  private final ApplicationData data;

  @GetMapping("/")
  public String home() {
    if (data.getServerLocation() == null) {
      return "redirect:/ask-server-location";
    } else {
      return "redirect:/manage";
    }
  }
}
