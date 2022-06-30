package com.hai.autocreateyaml.utils;


/**
 * @author YinHaiJun
 * @program autoCreateYaml
 * @description 存放配置文本
 * @create 2022-06-15 21:42
 **/
public class ApplicationStr {

    public static final String APPLICATION_YAML = "server:\n" +
            "  port: 8080  #项目的端口号\n" +
            "\n" +
            "#配置项目名称\n" +
            "spring:\n" +
            "  application:\n" +
            "    name: ProjectName  #项目的名称\n" +
            "  #配置数据源\n" +
            "  datasource:\n" +
            "    driver-class-name: com.mysql.cj.jdbc.Driver  #mysql8需要添加cj\n" +
            "    url: jdbc:mysql://localhost:3306/数据库名称?serverTimezone=Asia/Shanghai&characterEncoding=utf-8&useSSL=false\n" +
            "    username: root\n" +
            "    password: 数据库的密码\n" +
            "    type: com.alibaba.druid.pool.DruidDataSource  #采用druid连接池\n" +
            "\n" +
            "#配置mybatis\n" +
            "mybatis:\n" +
            "  config-location: classpath:mapper/*.xml  #mapper xml文件位置\n" +
            "  configuration:\n" +
            "    map-underscore-to-camel-case: true  #驼峰转下划线（java中属性字段和数据库字段映射）\n" +
            "    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl  #打印sql\n" +
            "\n" +
            "#打印日志\n" +
            "logging:\n" +
            "  level:\n" +
            "    com.example.demo.dao: debug  #路径指的是mybatis对应的方法接口所在的包";
}
