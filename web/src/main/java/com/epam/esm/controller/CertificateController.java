package com.epam.esm.controller;

import com.epam.esm.hateoas.CertificateModelAssembler;
import com.epam.esm.criteria_info.CertificateCriteriaInfo;
import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.CertificateUpdateDto;
import com.epam.esm.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * a class which performs CRUD operations on a resource called "certificate"
 */
@RestController
@RequestMapping(value = "/certificates")
@RequiredArgsConstructor
@Validated
public class CertificateController {

    private final CertificateService certificateService;

    private final CertificateModelAssembler certificateModelAssembler;
    private final PagedResourcesAssembler<CertificateDto> pagedResourcesAssembler;

    public static final String AUTHORITY_READ = "hasAuthority('certificate:read')";
    public static final String AUTHORITY_WRITE = "hasAuthority('certificate:write')";

    /**
     * a method which realizes REST's READ operation of all resources
     *
     * @param criteriaInfo - object with information about certificate to search
     * @return a pagedModel of CertificatesDTO, which represents a resource "certificates" from database with links
     */
    @GetMapping()
    public ResponseEntity<PagedModel<CertificateDto> > find(Pageable pageable, @Valid CertificateCriteriaInfo criteriaInfo) {
        Page<CertificateDto> certificates = certificateService.find(pageable, criteriaInfo);
        PagedModel<CertificateDto> pagedModel = pagedResourcesAssembler.toModel(certificates, certificateModelAssembler);
        return ResponseEntity.ok(pagedModel);
    }

    /**
     * a method which realizes REST's CREATE operation
     *
     * @param certificateDTO an object which represents a resource "certificates" that should be created
     *                       in the database
     * @return a responseEntity with status code and target resource certificate from database
     * with links
     */
    @PostMapping
    @PreAuthorize(AUTHORITY_WRITE)
    public ResponseEntity<CertificateDto> create(@RequestBody @Valid CertificateDto certificateDTO) {
        CertificateDto certificateDTOCreated = certificateService.create(certificateDTO);
        certificateModelAssembler.toModel(certificateDTOCreated);
        return new ResponseEntity<>(certificateDTOCreated, HttpStatus.CREATED);
    }

    /**
     * a method which realizes REST's DELETE operation of a specific resource with ID stored in a request path
     *
     * @param id an identification number of a resource which should be deleted
     * @return an object which represents Http response of DELETE operation
     */
    @DeleteMapping("/{id}")
    @PreAuthorize(AUTHORITY_WRITE)
    public ResponseEntity<RepresentationModel> delete(@PathVariable @Min(1) long id) {
        RepresentationModel representationModel = new RepresentationModel();
        certificateModelAssembler.appendGenericCertificateHateoasActions(representationModel);
        if (certificateService.delete(id)) {
            return new ResponseEntity<>(representationModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(representationModel, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * a method which realizes REST's UPDATE operation of a specific resource with ID stored in a request path
     * and CertificateUpdateDto
     *
     * @param certificateUpdateDto contains information for updating certificate in data base
     * @return an object which represents Http response of certificate with links
     */
    @PatchMapping("/{id}")
    @PreAuthorize(AUTHORITY_WRITE)
    public ResponseEntity<CertificateDto> update(@PathVariable("id") @Min(1)  Long id, @Valid @RequestBody CertificateUpdateDto certificateUpdateDto) {
        CertificateDto certificate = certificateService.update(certificateUpdateDto, id);
        certificateModelAssembler.toModel(certificate);
        return ResponseEntity.ok(certificate);
    }

    /**
     * a method which realizes REST's READ operation of a specific resource with id stored in a request path
     *
     * @param id an identification number of a requested resource
     * @return an ResponseEntity with CertificateDTO with links
     */
    @GetMapping("/{id}")
    @PreAuthorize(AUTHORITY_READ)
    public ResponseEntity<CertificateDto> find(@PathVariable("id")
                                                   @Min(1) long id) {
        CertificateDto certificate = certificateService.findById(id);
        certificateModelAssembler.toModel(certificate);
        return ResponseEntity.ok(certificate);
    }
}
