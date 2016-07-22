package ua.epam.spring.hometask.web_controller;

import au.com.bytecode.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * Created by sergei-rudenkov on 15.7.16.
 */

@Controller
public class UserController {

    // FIXME: 5%: 3. For operations that return one or several entites as a result (e.g. getUserByEmail, getUsersByName, getBookedTickets) implement simple views rendered via Velocity template engine. Use InternalResourceViewResolver for view resolving procedure.

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String users(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("userList", userDao.getAll());
        return "user";
    }

    // produces is not what you need here
    @RequestMapping(value = "/user_pdf", method = RequestMethod.GET, produces = "application/pdf")
    public ModelAndView usersPdf(@ModelAttribute("model") ModelMap model) {
        return new ModelAndView("pdfView", "usersList", userDao.getAll());
    }

    @RequestMapping(value = "/user_upload", method = RequestMethod.POST, produces = "application/pdf")
    // FIXME: 3% Implement batch loading of users and events into system. what about events?
    public ModelAndView usersUploadMultipart(@RequestParam("name") String name, @RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {
        if (!multipartFile.isEmpty()) {
            String rootPath = request.getSession().getServletContext().getRealPath("/");
            File dir = new File(rootPath + File.separator);

            File serverFile = new File(dir.getAbsolutePath() + File.separator + multipartFile.getOriginalFilename());

            // FIXME: 2% why just not multipartFile.transferTo(serverFile); ? :)
            try {
                try (InputStream is = multipartFile.getInputStream();
                     BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                    int i;
                    while ((i = is.read()) != -1) {
                        stream.write(i);
                    }
                    stream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            String[] nextLine;
            try {

                try (FileReader fileReader = new FileReader(serverFile);
                     CSVReader reader = new CSVReader(fileReader)) {
                    // FIXME: 1% please format your code
                    {
                        nextLine = reader.readNext();
                            User newUser = new User();
                            newUser.setFirstName(nextLine[0]);
                            newUser.setLastName(nextLine[1]);
                            userDao.put(newUser);
                        }while(reader.readNext() != null);
                    }
            } catch (IOException e) {
                System.out.println("error while reading csv and put to db : " + e.getMessage());
            }
        }
        return new ModelAndView("pdfView", "usersList", userDao.getAll());
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {
        ModelAndView model = new ModelAndView("error_screen");
        // FIXME: 2% you need to print exception message here
        model.addObject("error", "this is Exception.class");
        return model;
    }
}