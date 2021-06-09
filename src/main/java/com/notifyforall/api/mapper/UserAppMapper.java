package com.notifyforall.api.mapper;

import com.notifyforall.api.dto.UserAppRequest;
import com.notifyforall.api.dto.UserAppResponse;
import com.notifyforall.api.model.UserApp;
import org.mapstruct.*;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserAppMapper {

    UserAppResponse toResponse(UserApp userApp);

    @InheritInverseConfiguration
    UserApp fromRequest(UserAppRequest request);

    UserApp copy(UserApp copy, @MappingTarget UserApp target);

}
