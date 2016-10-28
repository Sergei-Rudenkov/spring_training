package com.epam.springdata_example.repository;

import com.epam.springdata_example.model.UserModel;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sergei_rudenkov on 10/11/16.
 */
public interface MyRepository extends CrudRepository<UserModel, String> {
    Iterable<UserModel> findПожалуйстаByFirstnameAndPassword(String firstname, String password);
}