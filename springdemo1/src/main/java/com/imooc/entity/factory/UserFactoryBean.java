package com.imooc.entity.factory;

import com.imooc.entity.User;
import org.springframework.beans.factory.FactoryBean;


/**
 * 实现FactoryBean接口并实现接口中的方法
 */
public class UserFactoryBean implements FactoryBean<User> {

    @Override
    public User getObject() throws Exception {
        return new User();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

}
