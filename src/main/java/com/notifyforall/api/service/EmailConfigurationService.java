package com.notifyforall.api.service;

import com.notifyforall.api.config.context.UserContext;
import com.notifyforall.api.config.exceptions.RestException;
import com.notifyforall.api.dto.EmailConfigurationRequest;
import com.notifyforall.api.dto.EmailConfigurationResponse;
import com.notifyforall.api.mapper.EmailUserAppConfigurationMapper;
import com.notifyforall.api.model.EmailUserAppConfiguration;
import com.notifyforall.api.repository.AppRepository;
import com.notifyforall.api.repository.EmailConfigurationRepository;
import com.notifyforall.api.service.utils.SystemMessages;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailConfigurationService {

    private final EmailConfigurationRepository emailConfigurationRepository;
    private final AppRepository appRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailUserAppConfigurationMapper mapper;


    public EmailConfigurationService(EmailConfigurationRepository emailConfigurationRepository,
                                     AppRepository appRepository,
                                     PasswordEncoder passwordEncoder,
                                     EmailUserAppConfigurationMapper mapper) {
        this.emailConfigurationRepository = emailConfigurationRepository;
        this.appRepository = appRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }


    public EmailConfigurationResponse registerNewEmailConfiguration(Long idApp, EmailConfigurationRequest request){
        EmailUserAppConfiguration emailUserAppConfiguration = mapper.fromRequest(request);
        emailUserAppConfiguration.setPasswordEmail(passwordEncoder.encode(request.getPasswordEmail()));

        emailUserAppConfiguration.setApp(appRepository.findByIdAndUserAppId(idApp, UserContext.getInstance().getUser().getId()).get());
        return mapper.toResponse(emailConfigurationRepository.save(emailUserAppConfiguration));
    }

    public EmailConfigurationResponse getEmailConfigurationByIdAndIdAppAndUserId(Long idEmailConfiguration, Long idApp){
        Optional<EmailUserAppConfiguration> optEmailUserAppConfiguration = emailConfigurationRepository
                .findByIdAndAppIdAndAppUserAppId(idEmailConfiguration, idApp, UserContext.getInstance().getUser().getId());

        if (optEmailUserAppConfiguration.isEmpty()){
            throw new RestException(HttpStatus.BAD_REQUEST,String.format(SystemMessages.EMAIL_NOT_FOUND_BY_ID_AND_ID_APP,
                    idEmailConfiguration, idApp));
        }

        return mapper.toResponse(optEmailUserAppConfiguration.get());
    }

}
