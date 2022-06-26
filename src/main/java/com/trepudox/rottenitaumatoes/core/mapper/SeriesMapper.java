package com.trepudox.rottenitaumatoes.core.mapper;

import com.trepudox.rottenitaumatoes.dataprovider.dto.SeriesDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.omdb.OMDBSeriesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SeriesMapper {

    SeriesMapper INSTANCE = Mappers.getMapper(SeriesMapper.class);

    SeriesDTO omdbSeriesToSeriesDTO(OMDBSeriesDTO omdbSeries);

}
