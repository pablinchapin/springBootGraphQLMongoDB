/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.graphql.gqlmdb.repositories;

import com.pablinchapin.graphql.gqlmdb.models.User;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author pvargas
 */
public interface UserRepository extends PagingAndSortingRepository<User, ObjectId>{
    
    List<User> findByIdIn(List<String> ids);
    
}