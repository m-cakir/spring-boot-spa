package com.mcakir.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {

    private final Security security = new Security();

    private final User user = new User();

    @Data
    public static class Security {

        private String oauth2ResourceId;

        private String oauth2ResourceJwtKeyValue;
    }

    @Data
    public static class User {

        private String username;

        private String password;
    }
}
