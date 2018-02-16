/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.graphql.gqlmdb.services;

import com.pablinchapin.graphql.gqlmdb.models.Article;
import com.pablinchapin.graphql.gqlmdb.repositories.ArticleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pvargas
 */
@Service
public class ArticleServiceImplementation implements ArticleService{
    
    private final ArticleRepository articleRepository;
    
    @Autowired
    ArticleServiceImplementation(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }
    
    @Override
    public List<Article> findAllUserArticles(List<String> ids){
        return articleRepository.findByIdIn(ids);
    }
    
}
