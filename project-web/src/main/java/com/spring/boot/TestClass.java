package com.spring.boot;


import java.io.Serializable;

/**
 * Created by Yang on 2018/12/22.
 */
public class TestClass<T extends BB> {

    Object save(T entity) {
        return entity;
    }
}
