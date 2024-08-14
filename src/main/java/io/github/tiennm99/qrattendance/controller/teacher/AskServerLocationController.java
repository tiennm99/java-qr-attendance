package io.github.tiennm99.qrattendance.controller.teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AskServerLocationController {
  @GetMapping("/ask-server-location")
  public String askServerLocation() {
    return "ask-server-location";
  }
}
