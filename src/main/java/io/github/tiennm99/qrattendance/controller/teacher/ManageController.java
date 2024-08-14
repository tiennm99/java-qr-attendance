package io.github.tiennm99.qrattendance.controller.teacher;

import io.github.tiennm99.qrattendance.data.ApplicationData;
import io.github.tiennm99.qrattendance.data.Submission;
import io.github.tiennm99.qrattendance.service.QRCodeService;
import io.github.tiennm99.qrattendance.service.SubmissionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ManageController {
  private final ApplicationData data;
  private final QRCodeService qrCodeService;
  private final SubmissionService submissionService;

  @GetMapping("/manage")
  public String manage(Model model) {
    var qrCode = qrCodeService.generateQRCode(data.getNgrokUrl() + "/gate");
    var submissions = data.getSubmissions();
    model.addAttribute("qrCode", qrCode);
    model.addAttribute("submissions", submissions);
    return "manage";
  }

  @GetMapping("/submissions")
  @ResponseBody
  public List<Submission> getSubmissions() {
    return data.getSubmissions();
  }

  @PostMapping("/export-submissions")
  @ResponseBody
  public String exportSubmissions() {
    submissionService.exportSubmissions();
    return "Submissions exported";
  }
}
