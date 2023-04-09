package com.imooc;

import com.imooc.aspect.OutSide;
import com.imooc.controller.HelloController;
import com.imooc.controller.HiController;
import com.imooc.controller.WelcomeController;
import com.imooc.dao.impl.Company;
import com.imooc.entity.User;
import com.imooc.entity.factory.UserFactoryBean;
import com.imooc.introduction.LittleUniverse;
import com.imooc.service.HelloService;
import com.imooc.service.HiService;
import com.imooc.service.WelcomeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.FileSystemXmlApplicationContext;

//@Configuration
//@EnableAspectJAutoProxy
//@Import(OutSide.class)
//@ComponentScan("com.imooc")
public class Entrance {

	public static void main(String[] args) {

		// 通过FileSystemXmlApplicationContext获取bean容器实例
//		String xmlPath = "D:\\workspace-idea\\imooc\\springframework\\ZhuShi\\springframework5.2.0.release-zhushi\\springframework5.2.0.release\\springdemo\\src\\main\\java\\com\\imooc\\service\\WelcomeService.java";
//		ApplicationContext applicationContext = new FileSystemXmlApplicationContext(xmlPath);
//		WelcomeService welcomeService = (WelcomeService) applicationContext.getBean("welcomeService");
//		welcomeService.sayHello("强大的spring框架");


		/**
		 * 获取bean容器的实例
		 */
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Entrance.class);
		HiService hiService = (HiService)applicationContext.getBean("hiServiceImpl");
		hiService.sayHi();
		System.out.println("---------------------------分割线以下执行HelloService-------------------------------");
		HelloService helloService = (HelloService)applicationContext.getBean("helloServiceImpl");
		helloService.sayHello();

	}
}
