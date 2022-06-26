package com.trepudox.rottenitaumatoes.core.mapper;

import com.trepudox.rottenitaumatoes.dataprovider.dto.MovieDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.omdb.OMDBMovieDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovieMapper {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    MovieDTO omdbMovieToMovieDTO(OMDBMovieDTO omdbMovieDTO);

}
