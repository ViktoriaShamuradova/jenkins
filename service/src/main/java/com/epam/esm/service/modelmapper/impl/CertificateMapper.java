package com.epam.esm.service.modelmapper.impl;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Certificate;
import com.epam.esm.service.modelmapper.AbstractModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

@Component
public class CertificateMapper extends AbstractModelMapper<CertificateDto, Certificate> {

    private final TagMapper tagMapper;

    public CertificateMapper(TagMapper tagMapper, ModelMapper modelMapper) {
        super(Certificate.class, CertificateDto.class, modelMapper);
        this.tagMapper = tagMapper;
    }

    @Override
    public CertificateDto toDTO(Certificate entity) {
        CertificateDto certificateDto = Objects.isNull(entity) ? null : super.getMapper().map(entity, CertificateDto.class);
        if (entity != null) {
            Set<TagDto> tags = entity.getTags()
                    .stream()
                    .map(tagMapper::toDTO)
                    .collect(Collectors.toSet());
            certificateDto.setTags(tags);
        }
        return certificateDto;
    }
}
