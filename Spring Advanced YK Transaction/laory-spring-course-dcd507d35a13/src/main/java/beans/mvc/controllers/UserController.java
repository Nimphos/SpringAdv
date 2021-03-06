package beans.mvc.controllers;

import beans.models.Ticket;
import beans.models.User;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
/*@RequestMapping(value = "/users")*/
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView getAllUsers() {
        ModelAndView model = new ModelAndView();
        List<User> users = userService.getAll();
        model.addObject("users", users);
        model.setViewName("user");
        return model;
    }

    @RequestMapping(value = "/users/{userEmail}", method = RequestMethod.GET)
    public ModelAndView getUserByEmail(@PathVariable String userEmail) {
        ModelAndView model = new ModelAndView();
        User user = userService.getUserByEmail(userEmail);
        model.addObject("user", user);
        model.setViewName("user");
        return model;
    }

    @RequestMapping(value = "/users/list/{name}", method = RequestMethod.GET)
    public ModelAndView getUsersByName(@PathVariable String name) {
        ModelAndView model = new ModelAndView();
        List<User> users = userService.getUsersByName(name);
        model.addObject("users", users);
        model.setViewName("user");
        return model;
    }

    @RequestMapping(value = "/users/{userId}/tickets", method = RequestMethod.GET)
    public ModelAndView getBookedTickets(@PathVariable String userId) {
        ModelAndView model = new ModelAndView();
        User user = userService.getById(Integer.parseInt(userId));
        List<Ticket> tickets = userService.getBookedTickets(user);
        model.addObject("tickets", tickets);
        model.setViewName("ticketsForm");
        return model;
    }

    @RequestMapping(value = "/users/{userId}/tickets", method = RequestMethod.GET,
            headers="accept=application/pdf")
    public ModelAndView getBookedTicketsPdf(@PathVariable String userId) {
        ModelAndView model = new ModelAndView();
        User user = userService.getById(Integer.parseInt(userId));
        List<Ticket> tickets = userService.getBookedTickets(user);
        model.addObject("tickets", tickets);
        model.setViewName("tickets.pdf");
        return model;
    }

    @RequestMapping(value = "/users/pdf", method = RequestMethod.GET)
    public ModelAndView getUsersPdf() {
        ModelAndView model = new ModelAndView();
        List<User> lstUsers = userService.getAll();
        model.addObject("users", lstUsers);
        model.setViewName("users.pdf");
        return model;
    }
}
