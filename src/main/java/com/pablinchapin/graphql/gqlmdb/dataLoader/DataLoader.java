/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.graphql.gqlmdb.dataLoader;

import com.pablinchapin.graphql.gqlmdb.models.Article;
import com.pablinchapin.graphql.gqlmdb.models.User;
import com.pablinchapin.graphql.gqlmdb.repositories.ArticleRepository;
import com.pablinchapin.graphql.gqlmdb.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author pvargas
 */
@Component
public class DataLoader {
    
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    
    @Autowired
    DataLoader(UserRepository userRepository, ArticleRepository articleRepository){
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }
    
    @PostConstruct
    private void generateData(){
        List<User> users = new ArrayList<>();
        
        users.add(User.builder().name("Pablo").createdAt(new Date()).age(37).articlesIds(new ArrayList<>()).articlesIds(new ArrayList<>()).build());
        users.add(User.builder().name("Vilma Donado").createdAt(new Date()).age(37).articlesIds(new ArrayList<>()).articlesIds(new ArrayList<>()).build());
        users.add(User.builder().name("Pablo Andres").createdAt(new Date()).age(37).articlesIds(new ArrayList<>()).articlesIds(new ArrayList<>()).build());
        users.add(User.builder().name("Diego Alfonso").createdAt(new Date()).age(37).articlesIds(new ArrayList<>()).articlesIds(new ArrayList<>()).build());
        users.add(User.builder().name("Jose Ignacio").createdAt(new Date()).age(37).articlesIds(new ArrayList<>()).articlesIds(new ArrayList<>()).build());
        
        users = (ArrayList) userRepository.save(users);
        
        List<Article> articles = new ArrayList<>();
        articles.add(Article.builder().title("Java 8 Lambdas").minutesRead(8).authorId(users.get(0).getId().toString()).build());
        articles.add(Article.builder().title("GraphQL Getting Started").minutesRead(10).authorId(users.get(1).getId().toString()).build());
        articles.add(Article.builder().title("Spring Boot + WebSockets").minutesRead(6).authorId(users.get(3).getId().toString()).build());
        articles = (ArrayList) articleRepository.save(articles);
        User me = users.get(0);
        users.get(0).setArticlesIds(Arrays.asList(articles.get(0).getId().toHexString()));
        users.get(0).setArticlesIds(Arrays.asList(articles.get(1).getId().toHexString()));
        users.get(1).setArticlesIds(Arrays.asList(articles.get(2).getId().toHexString()));
        users.get(3).setArticlesIds(Arrays.asList(articles.get(0).getId().toHexString()));
        userRepository.save(users);
        List<String> myFriendsIds = new ArrayList<>();
        for (int i = 1; i<users.size(); i++){
            myFriendsIds.add(users.get(i).getId().toHexString());
        }
        me.setFriendsIds(myFriendsIds);
        userRepository.save(me);
    }
    
}
