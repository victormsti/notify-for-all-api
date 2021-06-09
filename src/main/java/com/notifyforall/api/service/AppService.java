package com.notifyforall.api.service;

import com.notifyforall.api.config.context.UserContext;
import com.notifyforall.api.config.exceptions.RestException;
import com.notifyforall.api.dto.AppRequest;
import com.notifyforall.api.dto.AppResponse;
import com.notifyforall.api.dto.EnableOrDisableNotificationRequest;
import com.notifyforall.api.mapper.AppMapper;
import com.notifyforall.api.model.App;
import com.notifyforall.api.model.EmailUserAppConfiguration;
import com.notifyforall.api.model.UserApp;
import com.notifyforall.api.repository.AppRepository;
import com.notifyforall.api.service.utils.SystemMessages;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppService {

    private final AppRepository appRepository;
    private final AppMapper mapper;


    public AppService(AppRepository appRepository,
                      AppMapper mapper) {
        this.appRepository = appRepository;
        this.mapper = mapper;
    }

    public AppResponse findAppById(Long id) {

        Optional<App> foundApp = appRepository.findByIdAndUserAppId(id, UserContext.getInstance().getUser().getId());
        if(foundApp.isEmpty()){
            throw new RestException(HttpStatus.BAD_REQUEST,String.format(SystemMessages.APP_NOT_FOUND_BY_ID, id));
        }

        return mapper.toResponse(foundApp.get());
    }

    public AppResponse registerApp(AppRequest request){
        UserApp requestUserApp = UserContext.getInstance().getUser();

        checkIfAppExistsByNameIdentifierAndUserId(request.getIdentifierName(), requestUserApp.getId());

        App app = mapper.fromRequest(request);
        app.setUserApp(requestUserApp);
        return mapper.toResponse(appRepository.save(app));
    }

    public void enableOrDisableWebPushNotification(Long idApp, EnableOrDisableNotificationRequest request){
        UserApp requestUserApp = UserContext.getInstance().getUser();

        App app = getAppByIdAppAndUserId(idApp, requestUserApp.getId());
        app.setEnableNotificationWebPush(request.getEnable());
        appRepository.save(app);

    }

    public void enableOrDisableEmailNotification(Long idApp, EnableOrDisableNotificationRequest request){
        UserApp requestUserApp = UserContext.getInstance().getUser();

        App app = getAppByIdAppAndUserId(idApp, requestUserApp.getId());
        app.setEnableNotificationEmail(request.getEnable());
        appRepository.save(app);

    }

    public void enableOrDisableSmsNotification(Long idApp, EnableOrDisableNotificationRequest request){
        UserApp requestUserApp = UserContext.getInstance().getUser();

        App app = getAppByIdAppAndUserId(idApp, requestUserApp.getId());
        app.setEnableNotificationSms(request.getEnable());
        appRepository.save(app);

    }

    private void checkIfAppExistsByNameIdentifierAndUserId(String identifierName, Long idUser){
        if(appRepository.findByIdentifierNameAndUserAppId(identifierName, idUser).isPresent()){
            throw new RestException(HttpStatus.CONFLICT,String.format(SystemMessages.APP_ALREADY_EXISTS_BY_IDENTIFIER_NAME,
                    identifierName));
        }
    }

    private App getAppByIdAppAndUserId(Long idApp, Long idUser){
        Optional<App> foundApp = appRepository.findByIdAndUserAppId(idApp, idUser);

        if(foundApp.isEmpty()){
            throw new RestException(HttpStatus.BAD_REQUEST,String.format(SystemMessages.APP_NOT_FOUND_BY_ID, idApp));
        }

        return foundApp.get();

    }


}
