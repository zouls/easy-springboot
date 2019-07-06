package com.zouls.cassandramulti.entity.gps;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Table("gps_position")
public class GpsPositionEntity implements Serializable {

    @PrimaryKey
    private String vin;
    private BigDecimal longitude;
    private BigDecimal latitude;
    @Column("create_time")
    private LocalDateTime createTime;
}