package com.notifyforall.api.service;

import com.notifyforall.api.config.context.UserContext;
import com.notifyforall.api.config.exceptions.RestException;
import com.notifyforall.api.dto.WebPushConfigurationRequest;
import com.notifyforall.api.dto.WebPushConfigurationResponse;
import com.notifyforall.api.mapper.WebPushUserAppConfigurationMapper;
import com.notifyforall.api.model.UserApp;
import com.notifyforall.api.model.WebPushUserAppConfiguration;
import com.notifyforall.api.repository.AppRepository;
import com.notifyforall.api.repository.WebPushConfigurationRepository;
import com.notifyforall.api.service.utils.SystemMessages;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WebPushConfigurationService {

    private final WebPushConfigurationRepository webPushConfigurationRepository;
    private final AppRepository appRepository;
    private final WebPushUserAppConfigurationMapper webPushUserAppConfigurationMapper;


    public WebPushConfigurationService(WebPushConfigurationRepository webPushConfigurationRepository,
                                       AppRepository appRepository,
                                       WebPushUserAppConfigurationMapper webPushUserAppConfigurationMapper) {
        this.webPushConfigurationRepository = webPushConfigurationRepository;
        this.appRepository = appRepository;
        this.webPushUserAppConfigurationMapper = webPushUserAppConfigurationMapper;
    }


    public WebPushConfigurationResponse registerNewWebPushConfiguration(WebPushConfigurationRequest request){
        UserApp requestUserApp = UserContext.getInstance().getUser();

        WebPushUserAppConfiguration webPushUserAppConfiguration =
                webPushUserAppConfigurationMapper.fromRequest(request);

        webPushUserAppConfiguration.setApp(appRepository.findByIdentifierNameAndUserAppId(
                request.getApp().getIdentifierName(), requestUserApp.getId()).get());

        return webPushUserAppConfigurationMapper.toResponse(webPushConfigurationRepository.save(webPushUserAppConfiguration));
    }

    public WebPushConfigurationResponse getWebPushConfigurationByIdAndIdAppAndUserId(Long idWebPushConfiguration, Long idApp){
        UserApp requestUserApp = UserContext.getInstance().getUser();

        Optional<WebPushUserAppConfiguration> optWebPushUserAppConfiguration = webPushConfigurationRepository
                .findByIdAndAppIdAndAppUserAppId(idWebPushConfiguration, idApp, requestUserApp.getId());

        if (optWebPushUserAppConfiguration.isEmpty()){
            throw new RestException(HttpStatus.BAD_REQUEST,String.format(SystemMessages.WEB_PUSH_NOT_FOUND_BY_ID_AND_ID_APP,
                    idWebPushConfiguration, idApp));
        }

        return webPushUserAppConfigurationMapper.toResponse(optWebPushUserAppConfiguration.get());
    }
}
