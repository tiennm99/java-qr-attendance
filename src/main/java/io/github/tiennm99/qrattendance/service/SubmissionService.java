package io.github.tiennm99.qrattendance.service;

import io.github.tiennm99.qrattendance.data.ApplicationData;
import io.github.tiennm99.qrattendance.data.Submission;
import io.github.tiennm99.qrattendance.util.DistanceUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
@Slf4j
public class SubmissionService {
  private final ApplicationData data;

  public void addSubmission(
      String studentId, double latitude, double longitude, String ip, String userAgent) {
    var duplicatedWith = "";
    for (Submission submission : data.getSubmissions()) {
      if (submission.getUserAgent().equals(userAgent)) {
        duplicatedWith = submission.getStudentId();
        break;
      }
    }

    double serverLatitude = data.getServerLocation().getLeft();
    double serverLongitude = data.getServerLocation().getRight();
    double distance =
        DistanceUtil.calculateDistance(latitude, longitude, serverLatitude, serverLongitude);

    boolean tooFarFromServer = distance > 1.0;

    var newSubmission =
        new Submission(
            studentId, latitude, longitude, ip, userAgent, duplicatedWith, tooFarFromServer);

    data.getSubmissions().add(newSubmission);

    log.info(
        "New submission: {}, {}, {}, {}, {}, {}, {}",
        studentId,
        latitude,
        longitude,
        ip,
        userAgent,
        duplicatedWith,
        tooFarFromServer);
  }

  public void exportSubmissions() {
    List<Submission> submissions = data.getSubmissions();
    try (Workbook workbook = new XSSFWorkbook()) {
      Sheet sheet = workbook.createSheet("Submissions");
      Row headerRow = sheet.createRow(0);
      String[] headers = {
        "Student ID",
        "Latitude",
        "Longitude",
        "IP",
        "User Agent",
        "Duplicated With",
        "Too Far From Server"
      };
      for (int i = 0; i < headers.length; i++) {
        Cell cell = headerRow.createCell(i);
        cell.setCellValue(headers[i]);
      }

      CellStyle redStyle = workbook.createCellStyle();
      redStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
      redStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

      int rowNum = 1;
      for (Submission submission : submissions) {
        Row row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(submission.getStudentId());
        row.createCell(1).setCellValue(submission.getLatitude());
        row.createCell(2).setCellValue(submission.getLongitude());
        row.createCell(3).setCellValue(submission.getIp());
        row.createCell(4).setCellValue(submission.getUserAgent());
        row.createCell(5).setCellValue(submission.getDuplicatedWith());
        row.createCell(6).setCellValue(submission.isTooFarFromServer());

        if (!submission.getDuplicatedWith().isEmpty() || submission.isTooFarFromServer()) {
          for (int i = 0; i < headers.length; i++) {
            row.getCell(i).setCellStyle(redStyle);
          }
        }
      }

      File exportFile = new File("submissions.xlsx");
      try (FileOutputStream fileOut = new FileOutputStream(exportFile)) {
        workbook.write(fileOut);
      }
    } catch (IOException e) {
      log.error("Error exporting submissions", e);
    }
  }
}
