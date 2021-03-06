/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.graphql.gqlmdb.models;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author pvargas
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection="users")
public class User {
    
    private ObjectId id;
    private String name;
    private Integer age;
    private Date createdAt;
    private String nationality;
    private List<String> friendsIds;
    private List<String> articlesIds;
    
    
    
}
