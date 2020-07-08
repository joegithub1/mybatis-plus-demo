package com.exampl.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exampl.beans.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author HuangJian
 * @since 2020-07-08
 */
public interface MessageMapper extends BaseMapper<Message> {

    List<Message> selectMyAll(@Param(Constants.WRAPPER) Wrapper<Message> queryWrapper);

    Page<Message> selectMyPage(Page<Message> page,@Param(Constants.WRAPPER) Wrapper<Message> queryWrapper);


}
