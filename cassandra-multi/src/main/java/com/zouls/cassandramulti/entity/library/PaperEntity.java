package com.zouls.cassandramulti.entity.library;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("library")
@Data
public class PaperEntity {
    @PrimaryKey
    private UUID id;

    private String title;
    private String author;
    private String subject;
    private String publisher;
}