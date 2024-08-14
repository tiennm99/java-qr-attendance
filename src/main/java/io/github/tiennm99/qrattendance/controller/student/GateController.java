package io.github.tiennm99.qrattendance.controller.student;

import io.github.tiennm99.qrattendance.data.ApplicationData;
import io.github.tiennm99.qrattendance.util.DistanceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class GateController {

  private final ApplicationData data;

  @GetMapping("/gate")
  public String gate() {
    return "gate";
  }

  @PostMapping("/check-location")
  @ResponseBody
  public String checkLocation(@RequestParam double latitude, @RequestParam double longitude) {
    double serverLatitude = data.getServerLocation().getLeft();
    double serverLongitude = data.getServerLocation().getRight();
    double distance =
        DistanceUtil.calculateDistance(latitude, longitude, serverLatitude, serverLongitude);

    if (distance > 1.0) {
      return "rejected";
    } else {
      return "accepted";
    }
  }
}
