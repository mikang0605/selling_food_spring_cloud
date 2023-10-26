package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.CardReplace;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICardReplaceDao extends BaseMapper<CardReplace> {
    List<CardReplace> getCardReplaceTable(@Param("currentPage") int currentPage,
                                          @Param("pageSize") int pageSize,
                                          @Param("userid") Integer userid,
                                          @Param("departCode") String departCode);

    int getCardReplaceTableNum(@Param("userid") Integer userid,
                               @Param("departCode") String departCode);
}
