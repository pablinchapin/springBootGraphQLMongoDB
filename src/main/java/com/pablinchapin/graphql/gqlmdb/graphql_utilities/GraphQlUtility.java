/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.graphql.gqlmdb.graphql_utilities;

import com.pablinchapin.graphql.gqlmdb.dataFetchers.AllUsersDataFetcher;
import com.pablinchapin.graphql.gqlmdb.dataFetchers.ArticlesDataFetcher;
import com.pablinchapin.graphql.gqlmdb.dataFetchers.UserDataFetcher;
import graphql.GraphQL;
import static graphql.GraphQL.newGraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import java.io.File;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author pvargas
 */

@Component
public class GraphQlUtility {
    
    @Value("classpath:schemas.graphqls")
    private Resource schemaResource;
    private GraphQL graphQL;
    private AllUsersDataFetcher allUsersDataFetcher;
    private UserDataFetcher  userDataFetcher;
    private ArticlesDataFetcher articlesDataFetcher;
    
    
    
    
    
    @Autowired
    GraphQlUtility(AllUsersDataFetcher allUsersDataFetcher, UserDataFetcher  userDataFetcher, ArticlesDataFetcher articlesDataFetcher) throws IOException{
        this.allUsersDataFetcher = allUsersDataFetcher;
        this.userDataFetcher = userDataFetcher;
        this.articlesDataFetcher = articlesDataFetcher;
    }
    
    @PostConstruct
    public GraphQL createGraphQlObject() throws IOException{
        File schemas = schemaResource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemas);
        RuntimeWiring wiring = buildRuntimeWiring();
        
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        
        return newGraphQL(schema).build();
    
    }

    public RuntimeWiring buildRuntimeWiring() {
        return newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("users", allUsersDataFetcher)
                        .dataFetcher("user", userDataFetcher))
                .type("User", typeWiring -> typeWiring
                        .dataFetcher("articles", articlesDataFetcher)
                        .dataFetcher("friends", allUsersDataFetcher)
                ).build();
    }
    
    
    
    
}