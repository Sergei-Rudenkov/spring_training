package ua.epam.spring.hometask.web_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.domain.Event;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Created by sergei-rudenkov on 15.7.16.
 */

@Controller
public class EventController {
    @Autowired
    EventDao eventDao;

//    @RequestMapping(value = "/event", method = RequestMethod.GET)
//    public String users(@ModelAttribute("model") ModelMap model) {
//        model.addAttribute("eventList", eventDao.getAll());
//        return "event";
//    }

    @RequestMapping(value="/event", method = RequestMethod.GET, produces="application/xml")
    public @ResponseBody
    Event getCoffeeInXML() {
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(Event.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        try {
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return eventDao.getAll().stream().filter(event -> event.getName().equals("grand_concert")).findFirst().get();
    }
}
