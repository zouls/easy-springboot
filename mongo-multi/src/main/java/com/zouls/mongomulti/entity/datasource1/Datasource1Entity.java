package com.zouls.mongomulti.entity.datasource1;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(collection = "datasource1")
public class Datasource1Entity implements Serializable {
    @Id
    private String id;
    private String any_field;
    private Date etl_created_at;
}