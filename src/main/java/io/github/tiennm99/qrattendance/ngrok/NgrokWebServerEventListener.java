package io.github.tiennm99.qrattendance.ngrok;

import com.ngrok.Session;
import io.github.tiennm99.qrattendance.configuration.NgrokConfiguration;
import io.github.tiennm99.qrattendance.data.ApplicationData;
import java.net.URI;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class NgrokWebServerEventListener {

  private final NgrokConfiguration ngrokConfiguration;
  private final ApplicationData data;

  @Value("${server.port}")
  int port;

  @SneakyThrows
  @EventListener
  public void onApplicationEvent(final WebServerInitializedEvent event) {
    if (ngrokConfiguration.isEnabled()) {
      var sessionBuilder = Session.withAuthtoken(ngrokConfiguration.getAuthToken());
      try (var session = sessionBuilder.connect()) {
        var url = "http://localhost:" + port;
        var endpoint = session.forwardHttp(session.httpEndpoint(), new URI(url).toURL());
        var ngrokUrl = endpoint.getUrl();
        log.info("url: {}", url);
        log.info("ngrok url: {}", ngrokUrl);
        data.setNgrokUrl(ngrokUrl);
        endpoint.join();
      }
    }
  }
}
