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
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author pvargas
 */
@Component
public class AllUsersDataFetcher implements DataFetcher<List<User>>{
    
    private final UserService userService;
    
    @Autowired
    AllUsersDataFetcher(UserService userService){
        this.userService = userService;
    }

    @Override
    public List<User> get(DataFetchingEnvironment dfe) {
        User user = dfe.getSource();
        List<User> friends = new ArrayList<>();
        
        if(user != null){
            friends = userService.findByIdIn(user.getFriendsIds());
        }else{
                friends = userService.findAllUsers();
        }
        return friends;
    }
    
}
