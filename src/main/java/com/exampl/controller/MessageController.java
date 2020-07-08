package com.exampl.controller;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exampl.beans.Message;
import com.exampl.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author HuangJian
 * @since 2020-07-08
 */
@Controller
@RequestMapping("/message")
public class MessageController {

    /*@Autowired
    public BaseMapper<Message> baseMapper;
    @RequestMapping("/get")
    @ResponseBody
    public String get(String id){
        Message message = baseMapper.selectById(String.valueOf(id));
        System.out.println(message);
        return null;
    }*/
}

