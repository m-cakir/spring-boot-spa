package com.mcakir.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Movie extends BaseModel {

    @Column(unique = true, nullable = false)
    private String imdbId;

    @Column(nullable = false)
    private String name;

    private String poster;

    @Temporal(TemporalType.DATE)
    private Date releaseDate;

}
