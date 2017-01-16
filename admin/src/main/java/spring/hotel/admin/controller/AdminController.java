package spring.hotel.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.hotel.common.persistance.exception.DaoException;

/**
 * Created by Fene4ka_ on 13.11.2016.
 */
@Controller
public class AdminController {
    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String adminDefaultPage() throws DaoException {
        return "index";
    }
}
