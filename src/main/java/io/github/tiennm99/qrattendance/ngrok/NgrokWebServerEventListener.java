package io.github.tiennm99.qrattendance.ngrok;

import com.ngrok.Session;
import io.github.tiennm99.qrattendance.config.NgrokConfiguration;
import java.net.URI;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class NgrokWebServerEventListener {

  private final NgrokConfiguration ngrokConfiguration;
  private String url;

  @SneakyThrows
  @EventListener
  public void onApplicationEvent(final WebServerInitializedEvent event) {
    if (ngrokConfiguration.isEnabled()) {
      var sessionBuilder = Session.withAuthtoken(ngrokConfiguration.getAuthToken());
      try (var session = sessionBuilder.connect()) {
        var endpoint =
            session.forwardHttp(session.httpEndpoint(), new URI("http://localhost:8080").toURL());
        url = endpoint.getUrl();
        log.info("ngrok url: {}", url);
        endpoint.join();
      }
    }
  }
}
