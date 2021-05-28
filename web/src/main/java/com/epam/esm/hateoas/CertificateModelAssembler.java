package com.epam.esm.hateoas;

import com.epam.esm.controller.CertificateController;
import com.epam.esm.controller.TagController;
import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.TagDto;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CertificateModelAssembler extends RepresentationModelAssemblerSupport<CertificateDto, CertificateDto> {

    public CertificateModelAssembler() {
        super(CertificateController.class, CertificateDto.class);
    }

    @Override
    public CertificateDto toModel(CertificateDto dto) {
        dto.setTags(toTagDTOs(dto.getTags()));
        appendSelfReference(dto);
        return dto;
    }

    private void appendSelfReference(CertificateDto dto) {
        dto.add(linkTo(methodOn(CertificateController.class)
                .find(dto.getId()))
                .withRel("find by id")
                .withType(HttpMethod.GET.name()));
        dto.add(linkTo(methodOn(CertificateController.class)
                .update(dto.getId(), null))
                .withRel("update a current certificate")
                .withType(HttpMethod.PATCH.name()));
        dto.add(linkTo(methodOn(CertificateController.class)
                .delete(dto.getId()))
                .withRel("delete a current certificate")
                .withType(HttpMethod.DELETE.name()));
    }

    private Set<TagDto> toTagDTOs(Set<TagDto> tags) {
        if (tags.isEmpty())
            return Collections.emptySet();

        return tags.stream()
                .map(tag -> {
                    tag.add(linkTo(
                            methodOn(TagController.class)
                                    .find(tag.getId()))
                            .withSelfRel());
                    return tag;
                }).collect(Collectors.toSet());
    }

    public void appendGenericCertificateHateoasActions(RepresentationModel dto) {
        dto.add(linkTo(CertificateController.class).withRel("get all certificates").withType(HttpMethod.GET.name()));
        dto.add(linkTo(CertificateController.class).withRel("create certificate").withType(HttpMethod.POST.name()));
    }
}
