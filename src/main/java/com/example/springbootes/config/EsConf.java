package com.example.springbootes.config;
//
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.RestClients;
//
//@Configuration
//public class EsConf {
//
//    //localhost:9200 写在配置文件中就可以了
////    @Bean
////    RestHighLevelClient elasticsearchClient() {
////        ClientConfiguration configuration = ClientConfiguration.builder()
////                .connectedTo("47.103.133.170:9200")
////                //.withConnectTimeout(Duration.ofSeconds(5))
////                //.withSocketTimeout(Duration.ofSeconds(3))
////                //.useSsl()
////                //.withDefaultHeaders(defaultHeaders)
////                //.withBasicAuth(username, password)
////                // ... other options
////                .build();
////        return RestClients.create(configuration).rest();
////    }
//
//}