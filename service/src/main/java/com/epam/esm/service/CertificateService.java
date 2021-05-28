package com.epam.esm.service;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.CertificateUpdateDto;
import com.epam.esm.criteria_info.CertificateCriteriaInfo;

public interface CertificateService extends CrudService<CertificateDto, Long, CertificateCriteriaInfo> {

    CertificateDto update(CertificateUpdateDto certificateUpdateDto, Long id);

}

