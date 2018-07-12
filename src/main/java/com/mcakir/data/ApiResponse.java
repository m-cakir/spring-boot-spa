package com.mcakir.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ApiResponse {

    int status;

    String message;

    String description;

    @Singular
    List<String> errors;
}
