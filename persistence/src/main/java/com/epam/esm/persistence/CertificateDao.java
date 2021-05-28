package com.epam.esm.persistence;

import com.epam.esm.criteria_info.CertificateCriteriaInfo;
import com.epam.esm.entity.Certificate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CertificateDao {
    Page<Certificate> findAll(CertificateCriteriaInfo criteriaInfo, Pageable pageable);

    Optional<Certificate> findById(Long id);

    Certificate save(Certificate certificate);

    void delete(Certificate certificate);
}
