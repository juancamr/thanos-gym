package com.uni.thanosgym.interfaces;

import java.sql.SQLException;

@FunctionalInterface
public interface ConditionsExtractor<T> {
    boolean[] extractConditions() throws SQLException;
}

