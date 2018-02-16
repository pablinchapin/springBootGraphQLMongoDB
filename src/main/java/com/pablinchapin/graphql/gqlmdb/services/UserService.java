/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.graphql.gqlmdb.services;

import com.pablinchapin.graphql.gqlmdb.models.User;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author pvargas
 */
public interface UserService {
    
    List<User> findAllUsers();
    User findOneById(ObjectId id);
    List<User> findByIdIn(List<String>ids);
    
}
