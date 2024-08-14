package io.github.tiennm99.qrattendance.controller.student;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ViewController {

  @GetMapping("/view")
  public String view() {
    return "view";
  }
}
