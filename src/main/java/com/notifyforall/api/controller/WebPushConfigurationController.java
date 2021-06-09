package com.notifyforall.api.controller;

import com.notifyforall.api.dto.WebPushConfigurationRequest;
import com.notifyforall.api.dto.WebPushConfigurationResponse;
import com.notifyforall.api.service.WebPushConfigurationService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/web-push-configurations")
@Api(tags = "Web Push Configurations")
public class WebPushConfigurationController {

    private final WebPushConfigurationService webPushConfigurationService;

    public WebPushConfigurationController(WebPushConfigurationService webPushConfigurationService) {
        this.webPushConfigurationService = webPushConfigurationService;
    }

    @GetMapping("{id}/apps/{idApp}")
    public ResponseEntity<WebPushConfigurationResponse> getWebPushConfigurationByIdAndIdAppAndUserId(@PathVariable Long id, @PathVariable Long idApp){
        return ResponseEntity.ok().body(webPushConfigurationService.getWebPushConfigurationByIdAndIdAppAndUserId(id, idApp));
    }

    @PostMapping("records")
    public ResponseEntity<WebPushConfigurationResponse> registerNewWebPushConfiguration(@RequestBody WebPushConfigurationRequest request){
        return ResponseEntity.ok().body(webPushConfigurationService.registerNewWebPushConfiguration(request));
    }

}
