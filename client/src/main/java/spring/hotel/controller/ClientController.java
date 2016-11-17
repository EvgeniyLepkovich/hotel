package spring.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.hotel.common.persistance.exception.DaoException;

/**
 * Created by Fene4ka_ on 18.11.2016.
 */
@Controller
public class ClientController {
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView defaultPage() throws DaoException {
        ModelAndView model = new ModelAndView();
        model.setViewName("index-page");
        return model;
    }
}
