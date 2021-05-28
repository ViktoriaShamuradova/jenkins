package com.epam.esm.service.modelmapper;

import com.epam.esm.dto.EntityDto;
import com.epam.esm.entity.Entity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Objects;

@RequiredArgsConstructor
@Data
public abstract class AbstractModelMapper<DTO extends EntityDto, ENTITY extends Entity>
        implements GenericMapper<DTO, ENTITY> {

    private final ModelMapper mapper;
    private Class<ENTITY> entityClass;
    private Class<DTO> dtoClass;

    public AbstractModelMapper(Class<ENTITY> entityClass, Class<DTO> dtoClass, ModelMapper mapper) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
        this.mapper=mapper;
    }

    @Override
    public ENTITY toEntity(DTO dto) {
        return Objects.isNull(dto)
                ? null
                : mapper.map(dto, entityClass);
    }
    @Override
    public DTO toDTO(ENTITY entity) {
        return Objects.isNull(entity)
                ? null
                : mapper.map(entity, dtoClass);
    }
}
