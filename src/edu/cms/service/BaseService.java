package edu.xalead.cms.service;


import edu.xalead.cms.service.factory.PropertyFactory;
import edu.xalead.cms.tools.SystemContext;

import java.lang.reflect.Method;

public abstract class BaseService {
    public BaseService(){
        //获得属性工厂
        PropertyFactory factory = SystemContext.getFactory();
        //得到当前子类的反射类
        Class c = this.getClass();
        //得到当前子类的所有方法的反射类
        Method[] ms = c.getMethods();
        try {
            //迭代这些方法
            for(Method m : ms){
                //取出方法名
                String methodName = m.getName();
                //判断是否set开头
                if(methodName.startsWith("set")){
                    //如果以set开头则取掉set，后面字符首字母小写如setChannelDao处理为channelDao
                    String key = methodName.substring(3,4).toLowerCase()
                            + methodName.substring(4);
                    //根据key从属性工厂中取出对应的实例如ChannelDaoImpl的实例
                    Object obj = factory.getBean(key);
                    //如果实例不为空
                    if(obj != null){
                        //则根据调用约定调用这个set方法，将实例自动注入对象
                        m.invoke(this,obj);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
