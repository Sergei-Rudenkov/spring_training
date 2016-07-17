package ua.epam.spring.hometask.web_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergei-rudenkov on 15.7.16.
 */

@Controller
public class UserController {

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String users(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("userList", userDao.getAll());
        return "user";
    }

    @RequestMapping(value = "/user_pdf", method = RequestMethod.GET, produces = "application/pdf")
    public ModelAndView usersPdf(@ModelAttribute("model") ModelMap model) {
        return new ModelAndView("pdfView", "usersList", userDao.getAll());
    }
}