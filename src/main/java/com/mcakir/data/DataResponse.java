package com.mcakir.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class DataResponse<T> {

    private long totalElements;

    private List<T> data = new ArrayList<>();

    public DataResponse(Page<T> page) {
        setTotalElements(page.getTotalElements());
        setData(page.getContent());
    }
}
