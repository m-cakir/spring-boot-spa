package com.mcakir.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @PrePersist
    protected void onPersist(){
        if(this.createTime == null){
            this.createTime = new Date();
        }
        this.updateTime = this.createTime;
    }

    @PreUpdate
    protected void onUpdate(){
        this.updateTime = new Date();
    }
}
