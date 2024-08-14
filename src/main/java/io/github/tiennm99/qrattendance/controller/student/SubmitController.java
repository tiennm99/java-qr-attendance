package io.github.tiennm99.qrattendance.controller.student;

import io.github.tiennm99.qrattendance.service.SubmissionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SubmitController {

  private final SubmissionService submissionService;

  @PostMapping("/submit")
  @ResponseBody
  public String submit(
      @RequestParam String studentId,
      @RequestParam double latitude,
      @RequestParam double longitude,
      @RequestHeader("X-Forwarded-For") String ip,
      HttpServletRequest request) {
    String userAgent = request.getHeader("User-Agent");
    submissionService.addSubmission(studentId, latitude, longitude, ip, userAgent);
    return "Submission received";
  }
}
