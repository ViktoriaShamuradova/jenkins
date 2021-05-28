package com.epam.esm.service.modelmapper.impl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class TagMapperTest {
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private TagMapper tagMapper;
}
