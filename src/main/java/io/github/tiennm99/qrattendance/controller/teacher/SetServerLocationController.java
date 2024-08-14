package io.github.tiennm99.qrattendance.controller.teacher;

import io.github.tiennm99.qrattendance.data.ApplicationData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class SetServerLocationController {

  private final ApplicationData data;

  @PostMapping("/set-server-location")
  public String confirmLocation(@RequestParam("lat") double lat, @RequestParam("lon") double lon) {
    log.info("Location confirmed: {}, {}", lat, lon);
    data.setServerLocation(new ImmutablePair<>(lat, lon));
    return "redirect:/manage";
  }
}
