package spring.hotel.controller;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.hotel.common.persistance.exception.DaoException;
import spring.hotel.common.persistance.to.Account;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Fene4ka_ on 18.11.2016.
 */
@Controller
public class ClientController {
//    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
//    public ModelAndView defaultPage() throws DaoException {
//        ModelAndView model = new ModelAndView();
//        model.setViewName("index");
//        return model;
//    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String defaultPage() throws DaoException {
        return "index";
    }

    @RequestMapping({ "/user", "/me" })
    public Map<String, String> user(Principal principal) {

        // access extended attributes provided by the AuthenticatedPrincipal:
        OAuth2Authentication authenticationObject = (OAuth2Authentication) principal;
        Account authenticatedPrincipal = (Account) authenticationObject.getPrincipal();

        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", principal.getName());
        map.put("name", authenticatedPrincipal.getDisplayName());
        map.put("email", authenticatedPrincipal.getMail());
        return map;
    }
}
