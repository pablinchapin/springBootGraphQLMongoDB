/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.graphql.gqlmdb.dataFetchers;

import com.pablinchapin.graphql.gqlmdb.models.Article;
import com.pablinchapin.graphql.gqlmdb.models.User;
import com.pablinchapin.graphql.gqlmdb.services.ArticleService;
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
public class ArticlesDataFetcher implements DataFetcher<List<Article>>{
    
    private final ArticleService articleService;
    
    @Autowired
    ArticlesDataFetcher(ArticleService articleService){
        this.articleService = articleService;
    }

    @Override
    public List<Article> get(DataFetchingEnvironment dfe) {
        User user = dfe.getSource();
        
        List<String> articleIds = new ArrayList<>();
        
        if(user != null){
            articleIds = user.getArticlesIds();
        }
        
        List<Article> articles = articleService.findAllUserArticles(articleIds);
        
        return articles;
    }
    
    
    
}
