package com.sxx.config;

/**
 * web.xml
 *  <servlet>
 *       <servlet-name>SxxServlet</servlet-name>
 *       <servlet-class>com.sxx.servlet.SxxServlet</servlet-class>
 *  </servlet>
 *   <servlet-mapping>
 *       <servlet-name>SxxServlet</servlet-name>
 *       <url-pattern>/sxx</url-pattern>
 *  </servlet-mapping>
 */
public class ServletConfig {
    private String name;//类名称
    private String urlMapping;
    private String clazz;//类路径

    public ServletConfig(String name,String urlMapping,String clazz){
        this.name = name;
        this.urlMapping = urlMapping;
        this.clazz = clazz;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlMapping() {
        return urlMapping;
    }

    public void setUrlMapping(String urlMapping) {
        this.urlMapping = urlMapping;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
