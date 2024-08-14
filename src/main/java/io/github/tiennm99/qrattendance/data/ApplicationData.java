package io.github.tiennm99.qrattendance.data;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@ApplicationScope
@Component
@Data
public class ApplicationData {
  String ngrokUrl;
  Pair<Double, Double> serverLocation;
  List<Submission> submissions = new ArrayList<>();
}
