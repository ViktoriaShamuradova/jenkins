package com.epam.esm.service;

import com.epam.esm.criteria_info.CriteriaInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<T, ID, CRITERIA extends CriteriaInfo> {

    T create(T t);

    T findById(ID id);

    boolean delete(ID id);

    T update(T t);

    Page<T> find(Pageable pageable, CRITERIA criteriaInfo);

}
