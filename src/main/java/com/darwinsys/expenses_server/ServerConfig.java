package com.darwinsys.expenses_server;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
@SuppressWarnings("unused")
public class ServerConfig {

        @Bean
        @SuppressWarnings("unused")
        public ServletWebServerFactory servletContainer(@Value("${server.http.port}") int httpPort) {
            Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
            connector.setPort(httpPort);
            System.out.println("ServletWebServerVactory: added port = " + httpPort);
            TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
            tomcat.addAdditionalTomcatConnectors(connector);
            return tomcat;
        }

}
