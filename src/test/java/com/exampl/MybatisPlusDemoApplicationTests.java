package com.exampl;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;
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
