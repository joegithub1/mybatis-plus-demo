package com.exampl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exampl.beans.Message;
import com.exampl.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @Autowired
    public MessageMapper messageMapper;
    @RequestMapping("/get")
    @ResponseBody
    public Message get(String id){
        //List<Message> messagesList = messageMapper.selectList(null);
        Message message = messageMapper.selectById(id);
        return message;
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public Message findAll(){
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title","新闻");
        List<Message> messagesList = messageMapper.selectMyAll(queryWrapper);
        messagesList.forEach(System.out::println);
        return messagesList.get(0);
    }
}

