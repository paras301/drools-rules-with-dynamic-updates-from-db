package com.company.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.company.vo.Item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MongoRepository {

    @Autowired
    private final MongoTemplate mongoTemplate;
    
    public Item getItemData(Item item) {
    	Query query = new Query();
        query.addCriteria(new Criteria()
        		.where("item_id").is(String.valueOf(item.getItem_id()))
        		.and("date").is(item.getDate())
        		);
        
        return mongoTemplate.findOne(query, Item.class, "items");
    }
}
