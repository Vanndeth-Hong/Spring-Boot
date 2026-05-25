package co.istad.ite.spring.app.annotationbean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AnnotationBeanController {
    // 1. Declare rereference dependency
    // keyword final refers to the required dependency
    private final MyBean beans1;
    private final MyBean beans2;

    // Optional d
    private MyBean beans3;

    @Autowired
    public void setBeans3(@Qualifier() MyBean beans3) {
        this.beans3 = beans3;
    }


    // Inject Dependency (Required)
    public AnnotationBeanController(@Qualifier("cambodia") MyBean beans1,
                                    @Qualifier("usa") MyBean beans2) {
        this.beans1 = beans1;
        this.beans2 = beans2;
    }
    @GetMapping("/beans")
    public Map<String, Object> getBean() {
        beans1.setName("Vanndeth");
        beans2.setName("Vannda");
        beans3.setName("Vanno");
        return Map.of(
                "name", beans1,
                "name2", beans2,
                "name3", beans3);
    }
}
