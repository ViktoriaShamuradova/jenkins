package com.epam.esm.service.modelmapper.impl;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CertificateMapperTest {
    @InjectMocks
    private CertificateMapper certificateMapper;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private TagMapper tagMapper;

    @Test
    public void shouldToDto_EntityNotNull() {
        Certificate certificate = new Certificate();
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag());
        certificate.setTags(tags);
        CertificateDto certificateDto = new CertificateDto();

        when(certificateMapper.getMapper().map(any(), any())).thenReturn(certificateDto);
        certificateMapper.toDTO(certificate);
        verify(certificateMapper.getMapper()).map(certificate, CertificateDto.class);
        verify(tagMapper).toDTO(any());
    }
}
