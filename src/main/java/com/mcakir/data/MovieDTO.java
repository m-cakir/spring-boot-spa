package com.mcakir.data;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class MovieDTO {

    @NotNull
    private String name;

    @NotNull
    private String imdbId;

    @NotNull
    private Date releaseDate;

    private String poster;

}
