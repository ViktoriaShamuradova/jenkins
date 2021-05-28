package com.epam.esm.persistence.impl;

import com.epam.esm.criteria_info.CertificateCriteriaInfo;
import com.epam.esm.entity.Certificate;
import com.epam.esm.persistence.CertificateDao;
import com.epam.esm.persistence.dataspecification.CertificateSpecification;
import com.epam.esm.persistence.repository.CertificateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CertificateDaoImpl implements CertificateDao {

    private final CertificateRepository certificateRepository;

    @Override
    public Page<Certificate> findAll(CertificateCriteriaInfo criteriaInfo, Pageable pageable) {
        CertificateSpecification specification = new CertificateSpecification(criteriaInfo);
        return certificateRepository.findAll(specification, pageable);
    }

    @Override
    public Optional<Certificate> findById(Long id) {
        return certificateRepository.findById(id);
    }

    @Override
    public Certificate save(Certificate certificate) {
        return certificateRepository.save(certificate);
    }

    @Override
    public void delete(Certificate certificate) {
        certificateRepository.delete(certificate);
    }
}
