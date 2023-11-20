package hello.thymeleaf.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
@RequiredArgsConstructor
public class BasicController {

    private final RestTemplate restTemplate;

    @GetMapping("text-basic")
    public String textBasic(Model model) {
        model.addAttribute ("data", "Hello Spring!");
        return "basic/text-basic";
    }

    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute ("data", "Hello <b>Spring!</b>");
        return "basic/text-unescaped";
    }

    @GetMapping("/variable")
    public String variable(Model model) {
        User userA = new User ("userA", 10);
        User userB = new User ("userB", 10);

        List<User> list = new ArrayList<> ();
        list.add (userA);
        list.add (userB);

        Map<String, User> map = new HashMap<> ();
        map.put ("userA", userA);
        map.put ("userB", userB);

        model.addAttribute ("user", userA);
        model.addAttribute ("users", list);
        model.addAttribute ("userMap", map);

        return "basic/variable";
    }

    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession session) {
        session.setAttribute ("sessionData", "Hello Session");
        return "basic/basic-objects";
    }

    @GetMapping("/link")
    public String date(Model model) {
        model.addAttribute ("param1", "data1");
        model.addAttribute ("param2", "data2");
        return "basic/link";
    }

    @GetMapping("/date")
    public String link(Model model) {
        model.addAttribute ("localDateTime", LocalDateTime.now ());
        return "basic/date";
    }

    @GetMapping("/literal")
    public String literal(Model model) {
        model.addAttribute ("data", "Spring!");
        return "basic/literal";
    }

    @GetMapping("/operation")
    public String operation(Model model) {
        return "basic/operation";
    }

    @GetMapping("/attribute")
    public String attribute(Model model) {
        return "basic/attribute";
    }

    @GetMapping("/each")
    public String each(Model model) {
        addUsers (model);
        return "basic/each";
    }

    @GetMapping("/condition")
    public String condition(Model model) {
        addUsers (model);
        return "basic/condition";
    }

    @GetMapping("/test")
    @ResponseBody
    public ResponseEntity<vo> test() throws InterruptedException {
        Thread.sleep (10000);
        vo vo = new vo ();
        vo.setA ("gg");
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }

    @GetMapping("/test2")
    @ResponseBody
    public void test2() {
        AlramRestAPI.callAuthApi ("http://ec2-15-165-18-189.ap-northeast-2.compute.amazonaws.com:8080/basic/test", restTemplate);
    }

    private void addUsers(Model model) {
        List<User> list = new ArrayList<> ();
        list.add (new User ("userA", 10));
        list.add (new User ("userB", 20));
        list.add (new User ("userC", 30));

        model.addAttribute ("users", list);
    }


    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "Hello " + data;
        }
    }


    @Data
    @AllArgsConstructor
    static class User {
        private String username;
        private int age;
    }
}
