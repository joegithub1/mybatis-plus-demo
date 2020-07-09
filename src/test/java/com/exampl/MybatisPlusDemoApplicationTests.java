package com.exampl;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.exampl.beans.Message;
import com.exampl.mapper.MessageMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisPlusDemoApplicationTests {

    //项目路径前缀
    public String dir = "E:\\IdeaProjects\\mybatis-plus-demo";
    @Test
    void contextLoads() {
    }

    @Autowired
    public MessageMapper messageMapper;

    @Test
    public void selectOne(){
        List<Message> messageList = messageMapper.selectList(null);
        Assertions.assertEquals(3,messageList.size());
        System.out.println("-------------------");
    }

    @Test
    public void selectMessWrapper(){
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        //column字段是数据库的列名
        queryWrapper.eq("title","新闻3");

        List<Message> messagesList = messageMapper.selectList(queryWrapper);
        messagesList.forEach(System.out::println);
    }

    /**
    * @Title: selectMessWrapperSuper
    * @Description:  返回固定的几个列
    * @return void
    * @date 2020-07-08
    * @author HuangJian
    */
    @Test
    public void selectMessWrapperSuper(){
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        //column字段是数据库的列名
        queryWrapper.select("id","content").eq("title","新闻3");

        List<Message> messagesList = messageMapper.selectList(queryWrapper);
        messagesList.forEach(System.out::println);
    }
    /**
    * @Title: selectMessWrapperSuper2 
    * @Description:  排除 title , id 列
    * @return void
    * @date 2020-07-08 
    * @author HuangJian
    */
    @Test
    public void selectMessWrapperSuper2(){
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        //column字段是数据库的列名
        queryWrapper.select(Message.class,info->info.getColumn().equals("title")&&
                info.getColumn().equals("id")).eq("title","新闻3");

        List<Message> messagesList = messageMapper.selectList(queryWrapper);
        messagesList.forEach(System.out::println);
    }

    @Test
    public void testConditition(){
        String title = "3";
        String content = "";
        condititionMethod(title,content);
    }

    public void condititionMethod(String title,String content){
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
       /* if(StringUtils.isNotBlank(title)){

        }*/
        queryWrapper.like(StringUtils.isNotBlank(title),"title",title);
        queryWrapper.like(StringUtils.isNotBlank(content),"content",content);
        List<Message> messagesList = messageMapper.selectList(queryWrapper);
        messagesList.forEach(System.out::println);
    }

    @Test
    public void testAllEq(){
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();

        Map<String,String> params = new HashMap<>();
        params.put("title","新闻1");
        params.put("content","中国新闻1");
        queryWrapper.allEq(params);
        //queryWrapper.allEq(params,false); 为null 是否查询
        List<Message> messagesList = messageMapper.selectList(queryWrapper);
        messagesList.forEach(System.out::println);
    }
    @Test
    public void testMySql(){
        QueryWrapper<Message> queryWrapper = new QueryWrapper<Message>();
        queryWrapper.like("title","新闻");
        List<Message> messagesList = messageMapper.selectMyAll(null);
        messagesList.forEach(System.out::println);
    }
    @Test
    public void listPage(){
        Page<Message> page = new Page<>(1,2,false);//isSearchCount:true返回总行数，false不返回
        Page<Message> messagePage = messageMapper.selectPage(page, null);

        List<Message> records = messagePage.getRecords();
        records.forEach(System.out::println);

        System.out.println("当前页："+messagePage.getCurrent()+"\t总记录数："+messagePage.getTotal()+"\t每页："+
                messagePage.getSize()+"\t总页数："+messagePage.getPages());
    }
    @Test
    public void listPageMap(){
        Page<Map<String,Object>> page = new Page<>(1,2);
        Page<Map<String, Object>> mapPage = messageMapper.selectMapsPage(page, null);
        List<Map<String, Object>> records = mapPage.getRecords();
        System.out.println(records);
        System.out.println("当前页："+mapPage.getCurrent()+"\t总记录数："+mapPage.getTotal()+"\t每页："+
                mapPage.getSize()+"\t总页数："+mapPage.getPages());
    }
    @Test
    public void listMyPage(){
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        Page<Message> page = new Page<>(1,2,true);//isSearchCount:true返回总行数，false不返回
        //自定义page queryWrapper 不能是null
        Page<Message> messagePage = messageMapper.selectMyPage(page,queryWrapper);

        List<Message> records = messagePage.getRecords();
        records.forEach(System.out::println);

        System.out.println("当前页："+messagePage.getCurrent()+"\t总记录数："+messagePage.getTotal()+"\t每页："+
                messagePage.getSize()+"\t总页数："+messagePage.getPages());
    }
    /**
     *
     * IdType.AUTO 数据库ID自增
     * IdType.INPUT 用户输入ID
     * IdType.ID_WORKER 全局唯一ID，内容为空自动填充（默认配置）
     * IdType.UUID 全局唯一ID，内容为空自动填充
     * MP默认使用的是ID_WORKER，这是MP在Sequence的基础上进行部分优化，用于产生全局唯一ID。
     *
     *
     *
    * @Title: testGenerator
    * @Description:
    * @return void
    * @date 2020-07-07
    * @author HuangJian
    */
    @Test
    void testGenerator(){
        //1. 全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true)
                .setAuthor("HuangJian")//作者
        //生成的路径
        .setOutputDir(dir+"\\src\\main\\java")
                .setFileOverride(true)//是否文件覆盖，如果多次
                .setOpen(false) //是否打开本地目录
                .setServiceName("%sService") //设置生成的service接口名首字母是否为I
                .setIdType(IdType.AUTO) //主键策略
                .setBaseResultMap(true)
                .setBaseColumnList(true);

        //2数据源
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)
                .setUrl("jdbc:mysql://127.0.0.1:3306/imooc2?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC")
                //.setDriverName("com.mysql.jdbc.Driver")
                .setDriverName("com.alibaba.druid.pool.DruidDataSource")
                .setUsername("root")
                .setPassword("root");

        //3.策略配置
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true) // 全局大写命名
                //.setDbColumnUnderline(true) //表名 字段名 是否使用下滑线命名
                .setColumnNaming(NamingStrategy.underline_to_camel)// 配置数据表的字段与实体类的属性名之间映射的策略
                .setNaming(NamingStrategy.underline_to_camel) //数据库表映射到实体的命名策略
                .setEntityLombokModel(true)//配置 lombok 模式
                .setRestControllerStyle(false)//配置 rest 风格的控制器 true（@RestController）  false @Controller
                .setInclude("t_message") //生成的表
                .setTablePrefix("t_"); // 表前缀


        //4.包名策略
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.exampl")//父包名
                .setController("controller")
                .setEntity("beans")
                .setService("service")
                .setMapper("mapper")
                .setXml("mapper");


        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };

        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        //调整xxxMapper.xml的位置
        focList.add(new FileOutConfig("/templates/mapper/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                //返回xxxMapper.xml绝对路径位置
                return dir+"\\src\\main\\resources\\mybatis\\mapper\\" + tableInfo.getXmlName() + ".xml";
            }
        });
        cfg.setFileOutConfigList(focList);

        // 关闭默认 xml 生成，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        // tc.setController(null);
        // tc.setEntity("...");
        // tc.setMapper("...");
        // tc.setXml("...");
        // tc.setService("...");
        // tc.setServiceImpl("...");
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。

        //5.整合配置
        AutoGenerator ag = new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig)
                .setTemplate(tc)
                .setCfg(cfg);
        ag.execute();

    }
}
