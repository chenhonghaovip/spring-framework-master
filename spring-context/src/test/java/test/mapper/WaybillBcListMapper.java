package test.mapper;


import org.apache.ibatis.annotations.Param;
import test.dto.WaybillBcList;

import java.util.List;

/**
 * @author CHH
 */
public interface WaybillBcListMapper {
    int deleteByPrimaryKey(String wId);

    int insert(WaybillBcList record);

    int insertSelective(WaybillBcList record);

    WaybillBcList selectByPrimaryKey(String wId);

    int updateByPrimaryKeySelective(WaybillBcList record);

    int updateByPrimaryKey(WaybillBcList record);

    List<WaybillBcList> selectAllOrder(@Param("date") String date, @Param("orderType") Integer orderType);
}