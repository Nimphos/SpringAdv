package beans.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test {

    @RequestMapping("/test")
    @ResponseBody
    String home() {
        System.out.println("Hello World!");
        return "Hello World!";
    }

    /*@RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(@ModelAttribute("model") ModelMap model) {

        //model.addAttribute("userList", userList);

        return "index";
    }*/
}
