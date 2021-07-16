package com.zongs365.service.swagger.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;

import java.util.Map;

@ConfigurationProperties(prefix = "swagger")
@Data
@Component
@RefreshScope
public class SwaggerProperties {
    /**
     * 是否启用 swagger
     */
    private Boolean enabled;
    private String title;
    private String desc;
    private String version;
    private String termsOfServiceUrl;
    private String license;
    private String licenseUrl;
    private String basePackage;
    private String groupName;
    private String contactName;
    private String contactUrl;
    private String contactEmail;
    private String authorizationKey;

    private Map<String, SwaggerResource> modules;

}
