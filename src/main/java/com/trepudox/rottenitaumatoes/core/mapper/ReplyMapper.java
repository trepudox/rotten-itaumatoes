package com.trepudox.rottenitaumatoes.core.mapper;

import com.trepudox.rottenitaumatoes.dataprovider.dto.ReplyDTO;
import com.trepudox.rottenitaumatoes.dataprovider.model.ReplyModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ReplyMapper {

    ReplyMapper INSTANCE = Mappers.getMapper(ReplyMapper.class);

    ReplyDTO replyModelToReplyDTO(ReplyModel replyModel);

    List<ReplyDTO> replyModelListToReplyDTOList(List<ReplyModel> replyModel);

}
