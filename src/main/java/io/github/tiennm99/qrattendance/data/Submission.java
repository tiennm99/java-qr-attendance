package io.github.tiennm99.qrattendance.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public final class Submission {
  private final String studentId;
  private final double latitude;
  private final double longitude;
  private final String ip;
  private final String userAgent;
  private final String duplicatedWith;
  private final boolean tooFarFromServer;
}
