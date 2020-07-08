package com.exampl.controller;/**
 * @title: ArMessageController
 * @projectName mybatis-plus-demo
 * @description:  一是实体需要继承Model类，
 *     二是必须存在对应的原始mapper并继承baseMapper并且可以使用的前提下，才能使用此 AR 模式。
 * @author HuangJian
 * @date 2020-07-08
 */

import com.exampl.beans.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *@description: ActiveRecord模式
 *@author: huangJian
 *@create: 2020-07-08
 */
@Controller
@RequestMapping("/arMessage")
public class ArMessageController {

    @RequestMapping("/save")
    @ResponseBody
    public boolean save(String t,String c){
        Message message = new Message();
        message.setTitle(t);
        message.setContent(c);
        boolean result = message.insert();
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public boolean update(String t,String c,String id){
        Message message =new Message();
        message.setTitle(t);
        message.setContent(c);
        message.setId(Integer.parseInt(id));
        boolean b = message.updateById();
        return b;
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public boolean deleteById(String id){
        Message message = new Message();
        message.setId(Integer.parseInt(id));
        //方式一
        boolean b = message.deleteById();
        //方式二
        //message.deleteById(id);
        return b;
    }

    @RequestMapping("/getById")
    @ResponseBody
    public Message getById(String id){
        Message message = new Message();
        message.setId(Integer.parseInt(id));
        //方式一
        Message messageNew = message.selectById();
        //方式二
        //Message messageNew = message.selectById(id);
        return messageNew;
    }


}
