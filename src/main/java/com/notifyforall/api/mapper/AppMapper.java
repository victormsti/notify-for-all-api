package com.notifyforall.api.mapper;

import com.notifyforall.api.dto.AppRequest;
import com.notifyforall.api.dto.AppResponse;
import com.notifyforall.api.model.App;
import org.mapstruct.*;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AppMapper {

    @Mappings({
            @Mapping(target="idUserApp", source="userApp.id"),
    })
    AppResponse toResponse(App app);

    @InheritInverseConfiguration
    App fromRequest(AppRequest request);

    App copy(App copy, @MappingTarget App target);

}
