package com.trepudox.rottenitaumatoes.core.mapper;

import com.trepudox.rottenitaumatoes.dataprovider.dto.EpisodeDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.omdb.OMDBEpisodeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EpisodeMapper {

    EpisodeMapper INSTANCE = Mappers.getMapper(EpisodeMapper.class);

    EpisodeDTO omdbEpisodeToEpisodeDTO(OMDBEpisodeDTO omdbEpisode);

}
