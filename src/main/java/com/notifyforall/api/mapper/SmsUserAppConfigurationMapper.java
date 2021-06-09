package com.notifyforall.api.mapper;

import com.notifyforall.api.dto.SmsConfigurationRequest;
import com.notifyforall.api.dto.SmsConfigurationResponse;
import com.notifyforall.api.model.SmsUserAppConfiguration;
import org.mapstruct.*;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface SmsUserAppConfigurationMapper {

    SmsConfigurationResponse toResponse(SmsUserAppConfiguration smsUserAppConfiguration);

    @InheritInverseConfiguration
    SmsUserAppConfiguration fromRequest(SmsConfigurationRequest request);

    SmsUserAppConfiguration copy(SmsUserAppConfiguration copy, @MappingTarget SmsUserAppConfiguration target);

}
