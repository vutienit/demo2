package tien.example.demo2.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoResource {

    @GetMapping("/test-demo")
    public String testDemo() {
        return "test demo OK";
    }
}
