package ua.epam.spring.hometask.web_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.epam.spring.hometask.dao.EventDao;

/**
 * Created by sergei-rudenkov on 15.7.16.
 */

@Controller
public class EventController {
    @Autowired
    EventDao eventDao;

    @RequestMapping(value = "/event", method = RequestMethod.GET)
    public String users(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("eventList", eventDao.getAll());
        return "event";
    }
}
