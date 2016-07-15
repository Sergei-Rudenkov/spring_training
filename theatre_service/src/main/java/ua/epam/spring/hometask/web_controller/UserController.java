package ua.epam.spring.hometask.web_controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sergei-rudenkov on 15.7.16.
 */

@Controller
public class UserController {
    /**
     * Static list of users to simulate Database
     */
    private static List<User> userList = new ArrayList<User>();
    

    /**
     * Saves the static list of users in model and renders it
     * via freemarker template.
     *
     * @param model
     * @return The index view (FTL)
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(@ModelAttribute("model") ModelMap model) {
        userList.add(new User("Bill", "Gates"));
        userList.add(new User("Steve", "Jobs"));
        userList.add(new User("Larry", "Page"));
        userList.add(new User("Sergey", "Brin"));
        userList.add(new User("Larry", "Ellison"));
        model.addAttribute("userList", userList);

        return "index";
    }

    /**
     * Add a new user into static user lists and display the
     * same into FTL via redirect
     *
     * @param user
     * @return Redirect to /index page to display user list
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("user") User user) {

        if (null != user && null != user.getFirstname()
                && null != user.getLastname() && !user.getFirstname().isEmpty()
                && !user.getLastname().isEmpty()) {

            synchronized (userList) {
                userList.add(user);
            }

        }

        return "redirect:index.html";
    }

    class User {
        private String firstname;
        private String lastname;

        public User() {
        }

        public User(String firstname, String lastname) {
            this.firstname = firstname;
            this.lastname = lastname;

        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }
    }

}


