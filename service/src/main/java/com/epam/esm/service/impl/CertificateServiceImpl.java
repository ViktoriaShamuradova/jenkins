package com.epam.esm.service.impl;

import com.epam.esm.criteria_info.CertificateCriteriaInfo;
import com.epam.esm.criteria_info.TagCriteriaInfo;
import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.CertificateUpdateDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.persistence.CertificateDao;
import com.epam.esm.persistence.dataspecification.TagSpecification;
import com.epam.esm.persistence.repository.TagRepository;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.exception.ExceptionCode;
import com.epam.esm.service.exception.NoSuchResourceException;
import com.epam.esm.service.modelmapper.GenericMapper;
import com.epam.esm.service.modelmapper.impl.CertificateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateDao certificateDao;
    private final TagRepository tagDAO;
    private final GenericMapper<CertificateDto, Certificate> mapper;


    @Transactional(readOnly = true)
    @Override
    public CertificateDto findById(Long id) {
        Certificate certificate = certificateDao.findById(id).orElseThrow(() ->
                new NoSuchResourceException(ExceptionCode.NO_SUCH_CERTIFICATE_FOUND.getErrorCode(), "id= " + id));
        return mapper.toDTO(certificate);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CertificateDto> find(Pageable pageable, CertificateCriteriaInfo criteriaInfo) {
        Page<Certificate> all = certificateDao.findAll(criteriaInfo, pageable);
        return all.map(mapper::toDTO);
    }


    @Transactional
    @Override
    public CertificateDto create(CertificateDto certificateDTO) {
        certificateDTO.setCreateDate(Instant.now().truncatedTo(ChronoUnit.MICROS));
        certificateDTO.setUpdateLastDate(Instant.now().truncatedTo(ChronoUnit.MICROS));

        Set<Tag> tags = prepareTags(certificateDTO.getTags());
        Certificate certificate = mapper.toEntity(certificateDTO);
        certificate.setTags(tags);
        certificate.setId(null);

        return mapper.toDTO(certificateDao.save(certificate));
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        Optional<Certificate> certificate = certificateDao.findById(id);
        if (certificate.isPresent()) {
            certificateDao.delete(certificate.get());
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public CertificateDto update(CertificateUpdateDto certificateUpdateDto, Long id) {
        Certificate certificate = certificateDao.findById(id).orElseThrow(() ->
                new NoSuchResourceException(ExceptionCode.NO_SUCH_CERTIFICATE_FOUND.getErrorCode(), "id= " + id));

        if (certificateUpdateDto.getName().isPresent()) {
            certificate.setName(certificateUpdateDto.getName().get());
        }
        if (certificateUpdateDto.getDescription().isPresent()) {
            certificate.setDescription(certificateUpdateDto.getDescription().get());
        }
        if (certificateUpdateDto.getPrice().isPresent()) {
            certificate.setPrice(certificateUpdateDto.getPrice().get());
        }
        if (certificateUpdateDto.getDuration().isPresent()) {
            certificate.setDuration(certificateUpdateDto.getDuration().get());
        }
        if (certificateUpdateDto.getTags().isPresent()) {
            certificate.setTags(prepareTags(certificateUpdateDto.getTags().get()));
        }
        certificate.setUpdateLastDate(Instant.now());

        return mapper.toDTO(certificateDao.save(certificate));
    }

    private Set<Tag> prepareTags(Set<TagDto> tagDTOS) {
        Set<Tag> tags = new HashSet<>();
        tagDTOS.forEach((tagDTO -> {
            Tag tag = new Tag(tagDTO.getName());
            TagCriteriaInfo criteriaInfo = new TagCriteriaInfo();
            criteriaInfo.setName(tag.getName());
            Optional<Tag> tagFromDB = tagDAO.findOne(new TagSpecification(criteriaInfo));
            if (tagFromDB.isPresent()) {
                tag = tagFromDB.get();
            } else {
                tagDAO.save(tag);
            }
            tags.add(tag);
        }));
        return tags;
    }

    @Override
    public CertificateDto update(CertificateDto certificateDTO) {
        throw new UnsupportedOperationException();
    }
}
