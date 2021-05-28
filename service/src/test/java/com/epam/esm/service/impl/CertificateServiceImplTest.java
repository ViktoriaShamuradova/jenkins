package com.epam.esm.service.impl;

import com.epam.esm.criteria_info.CertificateCriteriaInfo;
import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.CertificateUpdateDto;
import com.epam.esm.entity.Certificate;
import com.epam.esm.persistence.CertificateDao;
import com.epam.esm.persistence.repository.TagRepository;
import com.epam.esm.service.exception.NoSuchResourceException;
import com.epam.esm.service.modelmapper.GenericMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class CertificateServiceImplTest {
    @InjectMocks
    private CertificateServiceImpl certificateService;

    @Mock
    private CertificateDao certificateDao;

    @Mock
    private TagRepository tagDAO;

    @Mock
    private GenericMapper<CertificateDto, Certificate> mapper;

    @Test
    public void find_shouldReturnPage() {
        //given
        List<Certificate> certificates = new ArrayList<>();
        Certificate c = createCertificate();
        certificates.add(c);

        CertificateCriteriaInfo criteriaInfo = new CertificateCriteriaInfo();
        Page<Certificate> certificatePage = new PageImpl<>(certificates);
        Pageable pageRequest = PageRequest.of(0, 1);

        when(certificateDao.findAll(any(), any()))
                .thenReturn(certificatePage);
        //when
        certificateService.find(pageRequest, criteriaInfo);
        //then
        verify(certificateDao)
                .findAll(criteriaInfo, pageRequest);
        verify(mapper).toDTO(c);
    }

    @Test
    public void find_shouldReturnEmptyPage() {
        List<Certificate> certificates = new ArrayList<>();

        Page<Certificate> certificatePage = new PageImpl<>(certificates);
        Pageable pageRequest = PageRequest.of(1, 1);


        when(certificateDao.findAll(any(), any()))
                .thenReturn(certificatePage);

        assertThat(certificateService.find(pageRequest, new CertificateCriteriaInfo()))
                .isEmpty();
    }

    @Test
    public void findById_shouldFind() {
        //given
        Certificate c = createCertificate();
        CertificateDto certificateDTO = createCertificateDTO();
        when(certificateDao.findById(anyLong())).thenReturn(Optional.of(c));
        when(mapper.toDTO(c))
                .thenReturn(certificateDTO);
        //when
        certificateService.findById(1L);
        //then
        verify(certificateDao).findById(1L);
        verify(mapper).toDTO(c);
        assertThat(certificateService.findById(1L)
                .equals(certificateDTO));
    }

    @Test
    public void findById_shouldThrownException() {
        when(certificateDao.findById(anyLong())).thenThrow(NoSuchResourceException.class);
        assertThatThrownBy(() -> certificateService.findById(anyLong()))
                .isInstanceOf(NoSuchResourceException.class);
    }

    @Test
    public void update_shouldThrownException() {
        when(certificateDao.findById(anyLong()))
                .thenThrow(NoSuchResourceException.class);
        assertThatThrownBy(() -> certificateService.update(new CertificateUpdateDto(), anyLong()))
                .isInstanceOf(NoSuchResourceException.class);
    }

    @Test
    public void update_shouldUpdate() {
        //given
        CertificateUpdateDto certificateUpdateDto = new CertificateUpdateDto();
        Certificate certificate = createCertificate();
        CertificateDto certificateDTO = createCertificateDTO();
        certificateDTO.setId(certificate.getId());


        when(certificateDao.findById(anyLong()))
                .thenReturn(Optional.of(certificate));
        when(certificateDao.save(any())).thenReturn(certificate);
        when(mapper.toDTO(any()))
                .thenReturn(certificateDTO);

        //when
        certificateService.update(certificateUpdateDto, 1L);
        //then
        verify(certificateDao).findById(1L);
        verify(certificateDao).save(certificate);
        verify(mapper).toDTO(certificate);
    }

    @Test
    public void updateWithParameterCertificateDTO_shouldThrownException() {
        assertThatThrownBy(() -> certificateService.update(new CertificateDto()))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    private Certificate createCertificate() {
        Certificate c = new Certificate();
        c.setId(1L);
        c.setName("Cert");
        c.setDescription("Desc");
        c.setDuration(10);
        c.setCreateDate(Instant.now());
        c.setUpdateLastDate(Instant.now());
        c.setPrice(new BigDecimal(10));
        return c;
    }

    private CertificateDto createCertificateDTO() {
        CertificateDto c = new CertificateDto();
        c.setName("Cert");
        c.setDescription("Desc");
        c.setDuration(10);
        c.setCreateDate(Instant.now());
        c.setUpdateLastDate(Instant.now());
        c.setPrice(new BigDecimal(10));
        return c;
    }

}
