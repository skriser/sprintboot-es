package com.example.springbootes.controller;

import com.example.springbootes.entity.es.EsVideo;
import com.example.springbootes.entity.mysql.MysqlBlog;
import com.example.springbootes.repository.es.EsBlogRepository;
import com.example.springbootes.repository.mysql.MysqlBlogRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class DataController {
    @Autowired
    private RestHighLevelClient restHighLevelClient;


    private final MysqlBlogRepository mysqlBlogRepository;
    private final EsBlogRepository esBlogRepository;

    public DataController(MysqlBlogRepository mysqlBlogRepository, EsBlogRepository esBlogRepository) {
        this.mysqlBlogRepository = mysqlBlogRepository;
        this.esBlogRepository = esBlogRepository;
    }

    @GetMapping("/blogs")
    public Object blog(){
        return mysqlBlogRepository.queryAll();
    }

    @GetMapping("/get")
    private GetResponse get() throws Exception {
        GetRequest request = new GetRequest().index("test_video").id("5e901ba7adf9f");
        return restHighLevelClient.get(request, RequestOptions.DEFAULT);
    }

    @GetMapping("/get")
    private GetResponse get_one() throws Exception {
        GetRequest request = new GetRequest();

        return restHighLevelClient.get(request, RequestOptions.DEFAULT);
    }


    @GetMapping("/search")
    public Object search(String type, String keyword){
        Map<String, Object> map = new HashMap<>();
//        String type = param.getType();
        StopWatch watch = new StopWatch();
        watch.start();
        if(type.equalsIgnoreCase("mysql")){
            List<MysqlBlog> mysqlBlogs = mysqlBlogRepository.queryBlogs(keyword);
            map.put("list",mysqlBlogs);
        }else if(type.equalsIgnoreCase("es")){
//            BoolQueryBuilder builder = QueryBuilders.boolQuery();

            TermQueryBuilder builder = QueryBuilders.termQuery("category", "");
//            builder.should(QueryBuilders.matchPhraseQuery("title",keyword));
//            builder.should(QueryBuilders.matchPhraseQuery("topics",keyword));
            String s = builder.toString();

            log.info(">>> {} <<<",s);
            Page<EsVideo> esBlogs = (Page<EsVideo>) esBlogRepository.search(builder);
//            Page<EsVideo> esBlogs = (Page<EsVideo>) esBlogRepository.search()
            List<EsVideo> content = esBlogs.getContent();
            map.put("list",content);
        }else {
            return ">>> 不知道 <<<";
        }
        watch.stop();
        long totalTimeMillis = watch.getTotalTimeMillis();
        map.put("duration",totalTimeMillis);
        return map;
    }

    @Data
    private static class Param{
        // String,es
        private String type;
        private String keyword;
    }
}
