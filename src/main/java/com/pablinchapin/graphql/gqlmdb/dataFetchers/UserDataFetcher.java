/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.graphql.gqlmdb.dataFetchers;

import com.pablinchapin.graphql.gqlmdb.models.User;
import com.pablinchapin.graphql.gqlmdb.services.UserService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import java.util.Map;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author pvargas
 */
@Component
public class UserDataFetcher implements DataFetcher<User>{
    
    private final UserService userService;
    
    @Autowired
    UserDataFetcher(UserService userService){
        this.userService = userService;
    }

    @Override
    public User get(DataFetchingEnvironment dfe) {
        Map args = dfe.getArguments();
        User user = userService.findOneById(new ObjectId(String.valueOf(args.get("id"))));
        
        return null;
    }
    
}
