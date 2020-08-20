package com.example.springbootes.entity.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 *
 *     Text,
 *     Integer,
 *     Long,
 *     Date,
 *     Float,
 *     Double,
 *     Boolean,
 *     Object,
 *     Auto,
 *     Nested,
 *     Ip,
 *     Attachment,
 *     Keyword;
 *
 */
@Data
@Document(indexName = "test_video",type = "doc",useServerConfiguration = true,createIndex = false)
public class EsVideo {
    @Id
    private Integer id;

    @Field(type = FieldType.Keyword)
    private String title;

    @Field(type = FieldType.Long)
    private String author_id;

    @Field(type = FieldType.Long)
    private String author_type;

    @Field(type = FieldType.Keyword)
    private String category;

    @Field(type = FieldType.Keyword)
    private String sub_category;

    @Field(type = FieldType.Keyword)
    private List topics;

    @Field(type = FieldType.Keyword)
    private String festival;

    @Field(type = FieldType.Long)
    private String duration;

    @Field(type = FieldType.Long)
    private String quality_score;

    @Field(type = FieldType.Float)
    private Float score;

    @Field(type = FieldType.Keyword)
    private String country;
    @Field(type = FieldType.Keyword)
    private String province;
    @Field(type = FieldType.Keyword)
    private String city;

    @Field(type = FieldType.Long)
    private Long audit_at;
    //@Field(type = FieldType.Date,format = DateFormat.custom,pattern = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
    @Field(type = FieldType.Long)
    private Long created_at;
}
