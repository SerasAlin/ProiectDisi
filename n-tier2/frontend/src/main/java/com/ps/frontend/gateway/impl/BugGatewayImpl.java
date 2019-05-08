package com.ps.frontend.gateway.impl;

import com.ps.common.dto.BugDTO;
import com.ps.common.dto.UserDTO;
import com.ps.frontend.conf.RestProperties;
import com.ps.frontend.gateway.BugGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class BugGatewayImpl implements BugGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(BugGateway.class);
    private final String URL = "/bug";

    private final RestProperties restProperties;

    @Autowired
    public BugGatewayImpl(RestProperties restProperties) {
        this.restProperties = restProperties;
    }



    @Override
    public BugDTO findById(Long id) {
        LOGGER.info("Executing findById method, id=" + id);
        String url = restProperties.getUrl() + URL + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        BugDTO bug = restTemplate.getForObject(url, BugDTO.class);
        return bug;
    }

    @Override
    public List<BugDTO> findAll() {
        LOGGER.info("Executing findAll method");
        String url = restProperties.getUrl() + URL + "/list";
        RestTemplate restTemplate = new RestTemplate();
        BugDTO[] response = restTemplate.getForObject(url, BugDTO[].class);
        return Arrays.asList(response);
    }

    @Override
    public Long save(BugDTO bugDTO) {
        LOGGER.info("Executing save method");
        String url = restProperties.getUrl() + URL + "/save";
        HttpEntity<Object> httpEntity = new HttpEntity<>(bugDTO);
        RestTemplate restTemplate = new RestTemplate();
        Long response = restTemplate.postForObject(url, httpEntity, Long.class);
        return response;
    }

    @Override
    public Long update(Long id, BugDTO bugDTO) {
        LOGGER.info("Executing save method");
        String url = restProperties.getUrl() + URL + "/"+ id+ "/edit";
        HttpEntity<Object> httpEntity = new HttpEntity<>(bugDTO);
        RestTemplate restTemplate = new RestTemplate();
        Long response = restTemplate.postForObject(url, httpEntity, Long.class);
        return response;
    }

}