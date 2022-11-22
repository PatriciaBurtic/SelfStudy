package com.pitechplus.selfstudy.utils;

import com.pitechplus.selfstudy.model.dao.specification.UserSpecification;
import com.pitechplus.selfstudy.model.dao.specification.criteria.SearchCriteria;
import com.pitechplus.selfstudy.model.dao.specification.utils.Operation;

public class UtilsClass {

    public static UserSpecification getUserSpecification(String key, Operation operation, Object object) {
        return new UserSpecification(
                SearchCriteria.builder()
                        .key(key)
                        .operation(operation)
                        .value(object)
                        .build()
        );
    }
}
