package com.notifyforall.api.mapper;

import com.notifyforall.api.dto.EmailConfigurationRequest;
import com.notifyforall.api.dto.EmailConfigurationResponse;
import com.notifyforall.api.model.EmailUserAppConfiguration;
import org.mapstruct.*;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface EmailUserAppConfigurationMapper {

    EmailConfigurationResponse toResponse(EmailUserAppConfiguration emailUserAppConfiguration);

    @InheritInverseConfiguration
    EmailUserAppConfiguration fromRequest(EmailConfigurationRequest request);

    EmailUserAppConfiguration copy(EmailUserAppConfiguration copy, @MappingTarget EmailUserAppConfiguration target);

}
