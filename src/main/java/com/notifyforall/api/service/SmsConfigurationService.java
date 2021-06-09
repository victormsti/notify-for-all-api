package com.notifyforall.api.service;

import com.notifyforall.api.config.context.UserContext;
import com.notifyforall.api.config.exceptions.RestException;
import com.notifyforall.api.dto.SmsConfigurationRequest;
import com.notifyforall.api.dto.SmsConfigurationResponse;
import com.notifyforall.api.mapper.SmsUserAppConfigurationMapper;
import com.notifyforall.api.model.SmsUserAppConfiguration;
import com.notifyforall.api.repository.AppRepository;
import com.notifyforall.api.repository.SmsConfigurationRepository;
import com.notifyforall.api.service.utils.SystemMessages;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SmsConfigurationService {

    private final SmsConfigurationRepository smsConfigurationRepository;
    private final AppRepository appRepository;
    private final SmsUserAppConfigurationMapper mapper;


    public SmsConfigurationService(SmsConfigurationRepository smsConfigurationRepository,
                                   AppRepository appRepository,
                                   SmsUserAppConfigurationMapper mapper) {
        this.mapper = mapper;
        this.smsConfigurationRepository = smsConfigurationRepository;
        this.appRepository = appRepository;
    }


    public SmsConfigurationResponse registerNewSmsConfiguration(Long idApp, SmsConfigurationRequest request){
        SmsUserAppConfiguration smsUserAppConfiguration = mapper.fromRequest(request);

        smsUserAppConfiguration.setApp(appRepository.findByIdAndUserAppId(idApp, UserContext.getInstance().getUser().getId()).get());
        return mapper.toResponse(smsConfigurationRepository.save(smsUserAppConfiguration));
    }

    public SmsConfigurationResponse getSmsConfigurationByIdAndIdAppAndUserId(Long idSmsConfiguration, Long idApp){
        Optional<SmsUserAppConfiguration> optSmsUserAppConfiguration = smsConfigurationRepository
                .findByIdAndAppIdAndAppUserAppId(idSmsConfiguration, idApp, UserContext.getInstance().getUser().getId());

        if (optSmsUserAppConfiguration.isEmpty()){
            throw new RestException(HttpStatus.BAD_REQUEST,String.format(SystemMessages.SMS_NOT_FOUND_BY_ID_AND_ID_APP,
                    idSmsConfiguration, idApp));
        }

        return mapper.toResponse(optSmsUserAppConfiguration.get());
    }
}
