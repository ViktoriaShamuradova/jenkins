package com.epam.esm.service.modelmapper;

import com.epam.esm.dto.EntityDto;
import com.epam.esm.entity.Entity;

public interface GenericMapper<DTO extends EntityDto, ENTITY extends Entity> {
    ENTITY toEntity(DTO dto);

    DTO toDTO(ENTITY entity);
}
