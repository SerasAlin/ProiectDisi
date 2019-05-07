package com.ps.frontend.gateway.impl;

import com.ps.common.dto.EmailIDTO;
import com.ps.frontend.conf.RestProperties;
import com.ps.frontend.gateway.EmailGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
@Component
public class EmailGatewayImpl implements EmailGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailGateway.class);
    private final String URL = "/email";

    private final RestProperties restProperties;

    @Autowired
    public EmailGatewayImpl(RestProperties restProperties) {
        this.restProperties = restProperties;
    }

    @Override
    public EmailIDTO findById(Long id) {
        LOGGER.info("Executing findById method, id=" + id);
        String url = restProperties.getUrl() + URL + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        EmailIDTO address = restTemplate.getForObject(url, EmailIDTO.class);
        return address;
    }



    @Override
    public List<EmailIDTO> findAll() {
        LOGGER.info("Executing findAll method");
        String url = restProperties.getUrl() + URL + "/list";
        RestTemplate restTemplate = new RestTemplate();
        EmailIDTO[] response = restTemplate.getForObject(url, EmailIDTO[].class);
        return Arrays.asList(response);
    }

    @Override
    public Long save(EmailIDTO addressDTO) {
        LOGGER.info("Executing save method");
        String url = restProperties.getUrl() + URL + "/save";
        HttpEntity<Object> httpEntity = new HttpEntity<>(addressDTO);
        RestTemplate restTemplate = new RestTemplate();
        Long response = restTemplate.postForObject(url, httpEntity, Long.class);
        return response;
    }
}
