package com.mcakir.controller.spec;

import com.mcakir.domain.Movie;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@And({
        @Spec(path = "name", params = "name", spec = Like.class),
        @Spec(path = "imdbId", params = "imdbId", spec = Equal.class)
})
public interface MovieSpec extends Specification<Movie> {
}
