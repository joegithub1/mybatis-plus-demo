package com.exampl.service.impl;

import com.exampl.beans.Message;
import com.exampl.mapper.MessageMapper;
import com.exampl.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HuangJian
 * @since 2020-07-08
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}
