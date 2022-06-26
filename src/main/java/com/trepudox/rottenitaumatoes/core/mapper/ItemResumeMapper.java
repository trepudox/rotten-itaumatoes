package com.trepudox.rottenitaumatoes.core.mapper;

import com.trepudox.rottenitaumatoes.dataprovider.dto.ItemResumeDTO;
import com.trepudox.rottenitaumatoes.dataprovider.dto.omdb.OMDBItemResumeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ItemResumeMapper {

    ItemResumeMapper INSTANCE = Mappers.getMapper(ItemResumeMapper.class);

    ItemResumeDTO omdbItemResumeToItemResumeDTO(OMDBItemResumeDTO omdbItemResumeDTO);

    List<ItemResumeDTO> omdbItemResumeToItemResumeDTO(List<OMDBItemResumeDTO> omdbItemResumeDTOList);

}
