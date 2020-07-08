package com.exampl;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;


//@MapperScan(value = {"com.baomidou.mybatisplus.core.mapper","com.exampl.mapper"})
@MapperScan(value = {"com.baomidou.mybatisplus.core.mapper","com.exampl.mapper"})
@SpringBootApplication
public class MybatisPlusDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusDemoApplication.class, args);
    }
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
