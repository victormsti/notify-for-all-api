package com.notifyforall.api.mapper;

import com.notifyforall.api.dto.WebPushConfigurationRequest;
import com.notifyforall.api.dto.WebPushConfigurationResponse;
import com.notifyforall.api.model.WebPushUserAppConfiguration;
import org.mapstruct.*;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface WebPushUserAppConfigurationMapper {

    WebPushConfigurationResponse toResponse(WebPushUserAppConfiguration webPushUserAppConfiguration);

    @InheritInverseConfiguration
    WebPushUserAppConfiguration fromRequest(WebPushConfigurationRequest request);

    WebPushUserAppConfiguration copy(WebPushUserAppConfiguration copy, @MappingTarget WebPushUserAppConfiguration target);

}
