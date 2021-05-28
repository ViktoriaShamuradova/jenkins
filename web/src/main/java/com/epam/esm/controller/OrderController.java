package com.epam.esm.controller;

import com.epam.esm.hateoas.OrderModelAssembler;
import com.epam.esm.criteria_info.OrderCriteriaInfo;
import com.epam.esm.dto.Cart;
import com.epam.esm.dto.OrderDto;
import com.epam.esm.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * a class which performs GET, CREATE operations on a resource called "order"
 */
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final OrderService orderService;

    private final PagedResourcesAssembler<OrderDto> pagedResourcesAssembler;
    private final OrderModelAssembler orderModelAssembler;

    public static final String AUTHORITY_READ = "hasAuthority('order:read')";
    public static final String AUTHORITY_WRITE = "hasAuthority('order:write')";


    /**
     * a method which realizes REST's CREATE operation
     *
     * @param cart an object which contains data for the formation of a new order
     *             and saving the order in the database
     * @return a responseEntity with status code and target resource order, which represents a resource "order" from database
     * with links
     */
    @PostMapping()
    @PreAuthorize(AUTHORITY_WRITE)
    public ResponseEntity<OrderDto> create(@RequestBody @Valid Cart cart) {
        OrderDto order = orderService.create(cart);
        orderModelAssembler.toModel(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    /**
     * a method which realizes REST's READ operation of all resources
     *
     * @param pageable          - object witch contains information about pagination
     * @param orderCriteriaInfo - object with information about order to search
     * @return a collection of OrderDTO, which represents a resource "order" from database
     */
    @GetMapping
    @PreAuthorize(AUTHORITY_READ)
    public ResponseEntity<PagedModel<OrderDto>> find(Pageable pageable, @Valid OrderCriteriaInfo orderCriteriaInfo) {
        Page<OrderDto> orders = orderService.find(pageable, orderCriteriaInfo);
        PagedModel<OrderDto> pagedModel = pagedResourcesAssembler.toModel(orders, orderModelAssembler);
        return ResponseEntity.ok(pagedModel);
    }

    /**
     * a method which realizes REST's READ operation of a specific resource with id stored in a request path
     *
     * @param id an identification number of a requested resource
     * @return an ResponseEntity with OrderDTO wich contains links
     */
    @GetMapping("/{id}")
    @PreAuthorize(AUTHORITY_READ)
    public ResponseEntity<OrderDto> find(@PathVariable @Min(1) long id) {
        OrderDto order = orderService.findById(id);
        orderModelAssembler.toModel(order);
        return ResponseEntity.ok(order);
    }
}
