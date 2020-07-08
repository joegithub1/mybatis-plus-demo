package com.exampl.configuration;/**
 * @title: MybatisPlusConfig
 * @projectName mybatis-plus-demo
 * @description:
 * @author HuangJian
 * @date 2020-07-08
 */

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *@description:
 *@author: huangJian
 *@create: 2020-07-08
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * @Title: paginationInterceptor
     * @Description:  分页插件
     * @return com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
     * @date 2020-07-08
     * @author HuangJian
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
