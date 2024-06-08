package playground.spring.boot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyClass {
    @GetMapping("/hello")
    public int hello(){
        return 145169;
    }
}
