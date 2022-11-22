package com.pitechplus.selfstudy.model.dao.specification.criteria;

import com.pitechplus.selfstudy.model.dao.specification.utils.Operation;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder(toBuilder = true)
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchCriteria {

    String key;

    Operation operation;

    Object value;

}
