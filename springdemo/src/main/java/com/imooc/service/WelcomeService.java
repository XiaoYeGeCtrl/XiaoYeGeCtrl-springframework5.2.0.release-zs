package com.imooc.service;


/**
 * 设计一个接口, 并进行编写接口的实现, 将这个实现类通过容器来管理起来,
 * 通过我们的容器来获取到该实现类的实例, 然后去执行对应的方法
 */
public interface WelcomeService {
    String sayHello(String name);
}
