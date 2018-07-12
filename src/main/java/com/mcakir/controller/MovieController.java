package com.mcakir.controller;

import com.mcakir.controller.spec.MovieSpec;
import com.mcakir.data.DataResponse;
import com.mcakir.data.MovieDTO;
import com.mcakir.domain.Movie;
import com.mcakir.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping
    public ResponseEntity<DataResponse<Movie>> search(MovieSpec spec, @PageableDefault(page = 0, size = 10) Pageable pageable) {

        Page<Movie> page = movieRepository.findAll(spec, pageable);

        return ResponseEntity.ok(new DataResponse<>(page));
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody MovieDTO dto) {

        Movie model = new Movie();
        model.setImdbId(dto.getImdbId());
        model.setName(dto.getName());
        model.setPoster(dto.getPoster());
        model.setReleaseDate(dto.getReleaseDate());

        Movie movie = movieRepository.save(model);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(movie.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> retrieve(@PathVariable Optional<Movie> movieOptional) {

        if (!movieOptional.isPresent())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(movieOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@Valid @RequestBody MovieDTO dto, @PathVariable("id") Optional<Movie> movieOptional) {

        if (!movieOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Movie movie = movieOptional.get();
        movie.setImdbId(dto.getImdbId());
        movie.setName(dto.getName());
        movie.setPoster(dto.getPoster());
        movie.setReleaseDate(dto.getReleaseDate());

        movieRepository.save(movie);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Optional<Movie> movieOptional) {

        if (!movieOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }

        movieRepository.delete(movieOptional.get());

        return ResponseEntity.noContent().build();
    }

}
