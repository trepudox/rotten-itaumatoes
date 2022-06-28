package com.trepudox.rottenitaumatoes.core.mapper;

import com.trepudox.rottenitaumatoes.dataprovider.dto.ReviewWithQuoteDTO;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReviewWithQuoteModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ReviewWithQuoteMapper {

    ReviewWithQuoteMapper INSTANCE = Mappers.getMapper(ReviewWithQuoteMapper.class);

    ReviewWithQuoteDTO reviewWithQuoteModelToReviewWithQuoteDTO(ReviewWithQuoteModel reviewWithQuoteModel);

    List<ReviewWithQuoteDTO> reviewWithQuoteModelListToReviewWithQuoteDTOList(List<ReviewWithQuoteModel> reviewWithQuoteModel);

}
