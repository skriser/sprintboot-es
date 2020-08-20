package com.example.springbootes.repository.es;

import com.example.springbootes.entity.es.EsVideo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsBlogRepository extends ElasticsearchRepository<EsVideo,Integer> {
}
