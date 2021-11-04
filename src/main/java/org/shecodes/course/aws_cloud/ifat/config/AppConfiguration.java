package org.shecodes.course.aws_cloud.ifat.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Data
@Configuration
@EnableConfigurationProperties()
@ConfigurationProperties(prefix = "coursesandgrades")
public class AppConfiguration {
    private String awsApiGatewayApiUrl;

    public AppConfiguration(){
        log.debug("AppConfiguration constructor");
    }
}