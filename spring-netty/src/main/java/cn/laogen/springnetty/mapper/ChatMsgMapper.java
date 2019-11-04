package cn.laogen.springnetty.mapper;

import cn.laogen.springnetty.entity.ChatMsg;
import cn.laogen.springnetty.entity.ChatMsgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChatMsgMapper {
    int countByExample(ChatMsgExample example);

    int deleteByExample(ChatMsgExample example);

    int deleteByPrimaryKey(String id);

    int insert(ChatMsg record);

    int insertSelective(ChatMsg record);

    List<ChatMsg> selectByExample(ChatMsgExample example);

    ChatMsg selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ChatMsg record, @Param("example") ChatMsgExample example);

    int updateByExample(@Param("record") ChatMsg record, @Param("example") ChatMsgExample example);

    int updateByPrimaryKeySelective(ChatMsg record);

    int updateByPrimaryKey(ChatMsg record);
}