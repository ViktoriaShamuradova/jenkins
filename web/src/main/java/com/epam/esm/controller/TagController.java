package com.epam.esm.controller;

import com.epam.esm.hateoas.TagDtoModelAssembler;
import com.epam.esm.criteria_info.TagCriteriaInfo;
import com.epam.esm.dto.TagDto;
import com.epam.esm.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * a class which performs REST's CRUD operations on a resource called "Tag"
 */
@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
@Validated
public class TagController {

    private final TagService tagService;

    private final PagedResourcesAssembler<TagDto> pagedResourcesAssembler;
    private final TagDtoModelAssembler tagModelAssembler;


    public static final String AUTHORITY_READ = "hasAuthority('tag:read')";
    public static final String AUTHORITY_WRITE = "hasAuthority('tag:write')";

    /**
     * a method which realizes REST's READ operation of all resources
     *
     * @param pageable     - map witch contains information about pagination
     * @param criteriaInfo - object with information about tag to search
     * @return a pagedModel of TagDTO with links, which represents a resource "tag" from database with links
     */
    @PreAuthorize(AUTHORITY_READ)
    @GetMapping
    public ResponseEntity<PagedModel<TagDto>> find(Pageable pageable, @Valid TagCriteriaInfo criteriaInfo) {
        Page<TagDto> tags = tagService.find(pageable, criteriaInfo);
        PagedModel<TagDto> pagedModel = pagedResourcesAssembler.toModel(tags, tagModelAssembler);
        return ResponseEntity.ok(pagedModel);    }

    /**
     * a method which realizes REST's CREATE operation
     *
     * @param tag an object which represents a resource "tags" that must be created
     *            in the data source
     * @return an object which represents Http response of CREATE operation
     */
    @PreAuthorize(AUTHORITY_WRITE)
    @PostMapping
    public ResponseEntity<TagDto> create(@RequestBody @Valid TagDto tag) {
        TagDto tagNew = tagService.create(tag);
        tagModelAssembler.toModel(tagNew);
        return new ResponseEntity<>(tagNew, HttpStatus.CREATED);
    }

    /**
     * a method which realizes REST's DELETE operation of a specific resource with ID stored in a request path
     *
     * @param tagName an identification number of a resource which should be deleted
     * @return an object which represents Http response of DELETE operation
     */
    @PreAuthorize(AUTHORITY_WRITE)
    @DeleteMapping("/{tagName}")
    public ResponseEntity<RepresentationModel> delete(@PathVariable @NotBlank @Size(max = 45) String tagName) {
        RepresentationModel representationModel = new RepresentationModel();
        tagModelAssembler.appendGenericTagHateoasActions(representationModel);
        if (tagService.delete(tagName)) {
            return new ResponseEntity<>(representationModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(representationModel, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * a method which find the most popular tag in user with the highest cost of all orders.
     *
     * @return a pagedModel of Tag, which represents a resource "tags" from data base
     */
    @PreAuthorize(AUTHORITY_READ)
    @GetMapping("/most-used")
    public ResponseEntity<PagedModel<TagDto> > findMostUsedTag() {
        List<TagDto> tags = tagService.getMostUsedTagOfUserWithHighestCostOfOrders();
        Page<TagDto> page = new PageImpl<>(tags);
        PagedModel<TagDto> pagedModel = pagedResourcesAssembler.toModel(page, tagModelAssembler);

        return ResponseEntity.ok(pagedModel);
    }

    /**
     * a method which realizes REST's READ operation of a specific resource with id stored in a request path
     *
     * @param id an identification number of a requested resource
     * @return an ResponseEntity with TagDto with links
     */
    @GetMapping("/{id}")
    @PreAuthorize(AUTHORITY_READ)
    public ResponseEntity<TagDto> find(@PathVariable("id")
                                               @Min(1) long id) {
        TagDto tagDto = tagService.findById(id);
        tagModelAssembler.toModel(tagDto);
        return ResponseEntity.ok(tagDto);
    }
}
