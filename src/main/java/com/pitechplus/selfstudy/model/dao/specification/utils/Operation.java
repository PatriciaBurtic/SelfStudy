package com.pitechplus.selfstudy.model.dao.specification.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Operation {
    GT(">"),
    GE(">="),
    LT("<"),
    LE("<="),
    E("="),
    NE("!="),
    LIKE(":");

    final String defaultValue;
}
