package co.istad.ite.spring.app.annotationbean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClassBeanConfig {
    @Bean(name = "cambodia")
    public MyBean myBean1() {
        MyBean myBean = new MyBean();
        myBean.setName("Cambodia");
        return myBean;
    }
    @Bean(name = "usa")
    public MyBean myBean2() {
        MyBean myBean = new MyBean();
        myBean.setName("USA");
        return myBean;
    }
}
