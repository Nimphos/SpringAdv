package beans.security.controllers;

import beans.security.models.UserSecurity;
import beans.security.models.UserSecurityImpl;
import beans.security.services.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private UserSecurityService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView handleLogin(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();

        model.addObject("error", "");
        model.addObject("message", "");

        if (error != null) {
            model.addObject("error", "Invalid username or password!");
        }
        if (logout != null) {
            model.addObject("message", "You have been logged out successfully.");
        }
        model.setViewName("login");

        return model;
    }

    @RequestMapping(value = "/restricted")
    public String handleForbiddenPage() {
        return "restricted";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.GET)
    public ModelAndView inputUserEmail() {
        ModelAndView model = new ModelAndView();
        UserSecurity userSecurity = new UserSecurity();
        /*userSecurity.setName("");
        userSecurity.setEmail("");
        userSecurity.setBirthday(LocalDate.now());
        userSecurity.setPassword("");*/
        model.addObject("newUser", userSecurity);
        model.setViewName("registerUser");
        //model.setViewName("testRegisterUser");
        return model;
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public ModelAndView getUserByEmail(HttpServletRequest request, UserSecurity newUser) throws Exception {
        newUser.setId(-1);
        newUser.setBirthday(LocalDate.now());
        String validate = generateString(verifyInputData(newUser));
        if(validate!=null)
            throw new Exception(validate);

        if(!userService.createUser(new UserSecurityImpl(newUser)))
            throw new Exception("Error create user");

        /*if(!userService.createUser(new UserSecurityImpl(newUser.getName(), newUser.getEmail(), newUser.getBirthday(), newUser.getRole(), newUser.getPassword())))
            throw new Exception("Error create user");*/

        return new ModelAndView("message", "messages", Arrays.asList("User "+newUser.getName()+" - created!"));

    }

    List<String> verifyInputData(UserSecurity newUser)
    {
        List<String> messages = new ArrayList<>();
        if(newUser==null)
            messages.add("Error Input Data");
        else
        {
            if(newUser.getEmail()==null)
                messages.add("Email must be filled");
            if(newUser.getPassword()==null)
                messages.add("Password must be filled");
            if(newUser.getRole()==null)
                messages.add("Role must be filled");
        }
        return messages;
    }
    String generateString(List<String> messages)
    {
        if(messages==null || messages.size()==0)
            return null;

        StringBuilder sb = new StringBuilder();
        for(String mes : messages)
            sb.append("<br/>").append(mes);

        return sb.toString();
    }
}
