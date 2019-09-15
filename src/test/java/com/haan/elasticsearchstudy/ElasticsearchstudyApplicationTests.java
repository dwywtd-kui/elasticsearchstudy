package com.haan.elasticsearchstudy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haan.elasticsearchstudy.entity.User;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchstudyApplicationTests {

    @Autowired
    private TransportClient client;
    @Test
    public void addDoc1() {
        String json = "{" +
                "\"user\":\"kimchy\"," +
                "\"age\":\"22\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        IndexResponse indexResponse = client.prepareIndex("index_test", "type_index")
                .setSource(json, XContentType.JSON)
                .get();
    }

    @Test
    public void addDoc2() {
        Map<String,Object> map =new HashMap<>();
        map.put("user","libai");
        map.put("age",23);
        map.put("message","hello map");

        IndexResponse indexResponse = client.prepareIndex("index_test", "type_index")
                .setSource(map, XContentType.JSON)
                .get();
    }
    @Test
    public void addDoc3() throws JsonProcessingException {
        User user =new User("zhangliang",21,"hello javabean");
        ObjectMapper mapper =new ObjectMapper();
        String s = mapper.writeValueAsString(user);
        IndexResponse indexResponse = client.prepareIndex("index_test", "type_index")
                .setSource(s,XContentType.JSON)
                .get();
    }

    @Test
    public void  deletDoc(){
        DeleteResponse deleteResponse = client.prepareDelete("index_test", "type_index", "OTUINW0BGY9H9s-fp1lZ")
                .get();
    }
}
