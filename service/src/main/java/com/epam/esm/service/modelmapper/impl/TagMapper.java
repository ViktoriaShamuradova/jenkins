package com.epam.esm.service.modelmapper.impl;

import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.modelmapper.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TagMapper extends AbstractModelMapper<TagDto, Tag> {

    public TagMapper(ModelMapper modelMapper) {
        super(Tag.class, TagDto.class, modelMapper);
    }
}
