package cl.bahatech.bahagamesbackend.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Slf4j
public class Log4jListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent event) {
        MDC.put("RequestId", String.valueOf(UUID.randomUUID()));
        log.debug("++++++++++++ REQUEST INITIALIZED +++++++++++++++++");

        if (event.getServletRequest() instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) event.getServletRequest();
            String url = httpServletRequest.getRequestURL().toString();

            log.debug("Requested URL = {}", url);
        }
    }

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
        log.debug("-------------REQUEST DESTROYED ------------");
        MDC.clear();
    }

}
