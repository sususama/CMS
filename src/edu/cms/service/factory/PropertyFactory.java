package edu.xalead.cms.service.factory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class PropertyFactory {
    //存储根据factory.properties文件中配置的键=类名对，实例化的键=对象的集合
    private HashMap<String,Object> beans = new HashMap<>();
    //创建属性集合对象
    Properties prop = new Properties();
    public PropertyFactory(){

    }
    //创建工厂后，调用init()方法初始化prop属生集合及beans实例集合
    public PropertyFactory init(){
        try {
            //获取属性配置文件的输入流
            InputStream input = this.getClass().getClassLoader().getResourceAsStream("factory.properties");

            //根据属性配置文件的输入把属性中的键值加载进属性集合对象中
            prop.load(input);

            Enumeration<String> names = (Enumeration<String>)prop.propertyNames();
            while(names.hasMoreElements()){
                String key = names.nextElement();
                //根据键取出属性文件中的值
                String servicename = prop.getProperty(key);
                //根据配置的类获得反射类
                Class c = Class.forName(servicename);
                //用反射类的默认构造实例化配置的对象
                beans.put(key,c.newInstance());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
    /**
     * 这是个创建Bean的工厂方法
     * @param key 属性文件中键的值
     * @return
     */
    public Object getBean(String key){
        //根据key到实例集合中取实例
        Object o = beans.get(key);
        try {
            //因为创建实例有顺序，有可能我们取这个实例时，这个实例还未来得及创建出来
            if(o == null){
                //所以我们还要再在属性集合中看看有没有对应这个链的配置
                String beanName = prop.getProperty(key);
                //，如果有，就说明还没来及实例化它
                if(beanName != null && !"".equals(beanName)){
                    //所以如果有这个属性配置，我们就再实例化
                    o = Class.forName(beanName).newInstance();
                    //放入实例集合
                    beans.put(key,o);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    /**
     * 这是个创建Bean的工厂方法
     * @param key 属性文件中键的值
     * @return
     */
    public Object setBean(String key,Object obj){
        //根据key从实例集合中取实例
        return beans.put(key,obj);
    }
}