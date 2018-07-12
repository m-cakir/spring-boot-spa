package com.mcakir.repository;

import com.mcakir.model.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository <T extends BaseModel> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

}
