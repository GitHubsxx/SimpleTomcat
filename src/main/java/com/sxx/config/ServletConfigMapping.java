package com.sxx.config;


import java.util.ArrayList;
import java.util.List;
/**
 * 注册servlet
 */
public class ServletConfigMapping {
    private static List<ServletConfig> configs = new ArrayList<ServletConfig>();

    static {
        configs.add(
                new ServletConfig("SxxServlet","/sxx","com.sxx.servlet.SxxServlet")
        );
    }
    public static List<ServletConfig> getConfigs(){
        return configs;
    }
}
