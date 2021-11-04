package org.shecodes.course.aws_cloud.ifat.service;


import lombok.extern.slf4j.Slf4j;
import org.shecodes.course.aws_cloud.ifat.config.AppConfiguration;
import org.shecodes.course.aws_cloud.ifat.model.dto.CourseImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class ImagesLoader {
    private static final String POST_COURSE_IMAGE = "/";
    private static final String GET_AS_TEST = "/";

    private WebClient webClient;

    @Autowired
    private AppConfiguration config;

    public ImagesLoader(){
        log.debug("ImagesLoader default constructor ");
    }

    @PostConstruct
    private void init(){
        System.out.println("postConstruct of ImagesLoader");
        log.debug("ImagesLoader init method ");
        webClient = WebClient.create(config.getAwsApiGatewayApiUrl());
        if ( config==null){
            System.out.println("ImagesLoader config == null");
            log.debug("ImagesLoader config == null");
        }else{
            System.out.println("ImagesLoader config not null apiUrl is "+config.getAwsApiGatewayApiUrl());
            log.debug("ImagesLoader config not null apiUrl is "+config.getAwsApiGatewayApiUrl());
        }

    }


    //used by test
    public ImagesLoader(WebClient webClient) {
        this.webClient = webClient;
    }


    public String retrieveGetTest() {
        try {
            return webClient.get()
                    .uri(GET_AS_TEST)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("WebClientResponseException exception error status code {} and body {}",
                    ex.getRawStatusCode(), ex.getResponseBodyAsString());
            log.error(" got WebClientResponseException ", ex);
            throw (ex);
        } catch (Exception ex) {
            log.error(" Exception in retrievePersonById> ", ex);
            throw ex;
        }
    }


    public String loadImage(CourseImageDTO courseImageDTO) {
        try {
            return webClient.post().uri(POST_COURSE_IMAGE)
                    .bodyValue(courseImageDTO)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("WebClientResponseException exception error status code {} and body {}",
                    ex.getRawStatusCode(), ex.getResponseBodyAsString());
            log.error(" got WebClientResponseException ", ex);
            throw (ex);
        } catch (Exception ex) {
            log.error(" Exception in retrievePersonById> ", ex);
            throw ex;
        }
    }


}
