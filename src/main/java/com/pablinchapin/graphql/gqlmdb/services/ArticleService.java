/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.graphql.gqlmdb.services;

import com.pablinchapin.graphql.gqlmdb.models.Article;
import java.util.List;

/**
 *
 * @author pvargas
 */
public interface ArticleService {

    List<Article> findAllUserArticles(List<String> userId);
    
}
