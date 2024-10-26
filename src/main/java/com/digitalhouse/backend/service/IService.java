package com.digitalhouse.backend.service;

import com.digitalhouse.backend.exception.EntityAlreadyExistsException;
import com.digitalhouse.backend.exception.ResourceNotFoundException;

import java.util.List;

public interface IService<T> {
    List<T> findAll();

    T findById(Long id) throws ResourceNotFoundException;

    T save(T t) throws ResourceNotFoundException, EntityAlreadyExistsException;

    T update(T t) throws ResourceNotFoundException, EntityAlreadyExistsException;

    T delete(Long id) throws ResourceNotFoundException;
}
