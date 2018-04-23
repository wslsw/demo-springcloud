    项目是基于springboot1.5.10.RELEASE、springcloud Edgware.SR3构建的一个demo，在网关、服务注册中心基础上实现了基本的curd操作、调用链日志等服务治理功能。

本地运行环境：
    ide需装好lombok插件，本地提前启动redis-server，加载代码后修改数据库配置为实际地址，maven运行生成jar包成功后，依次先启动服务server-euraka，再启动其它服务，顺序可以随意。

ide工具

    eclipse/idea

涉及技术栈

    spring cloud hystrix
    spring cloud turbine
    spring cloud eureka
    spring cloud sleuth/zipkin
    spring cloud config
    spring cloud feign
    spring cloud zuul
    spring boot admin
    spring boot
    swagger2
    druid
    mybatis/mapper
    mysql/oracle
    redis
    lombok
    docker
    thymeleaf
    layui
    ...

项目结构和端口规划
    
    common

    server-euraka     8501
    server-config     8502
    server-zuul       8080
    server-sleuth     8504
    server-admin      8505
    server-turbine    8506
  
    service-portal-api
    service-portal    8101
    web-portal        8201

    core-user
    service-user-api
    service-user      8102
    web-user          8202

    core-role
    service-role-api
    service-role      8103
    web-role          8203