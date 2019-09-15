package edu.xalead.cms.tools;

import edu.xalead.cms.service.factory.PropertyFactory;

public class SystemContext {
    private static PropertyFactory factory=null;
    public static PropertyFactory getFactory(){
        return factory;
    }
    public static void setFactory(PropertyFactory f){
        factory=f;
    }
}
