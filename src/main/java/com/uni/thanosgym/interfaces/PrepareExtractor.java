package com.uni.thanosgym.interfaces;

import java.sql.PreparedStatement;
import java.sql.SQLException;


@FunctionalInterface
public interface PrepareExtractor<T> {
    T extractData(PreparedStatement ps) throws SQLException;
}

