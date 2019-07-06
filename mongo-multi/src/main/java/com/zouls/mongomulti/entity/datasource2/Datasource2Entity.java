package com.zouls.mongomulti.entity.datasource2;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(collection = "datasource2")
public class Datasource2Entity implements Serializable {
    @Id
    private String id;
    private String any_field;
    private Date etl_created_at;
}