package spring.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Fene4ka_ on 18.11.2016.
 */
@Controller
public class ClientController {

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String getIndexPage(){
        return "index";
    }

    @RequestMapping(value = {"/sign-in"}, method = RequestMethod.GET)
    public String getSignInPage(){
        return "sign_in";
    }

    @RequestMapping(value = {"/sign-up"}, method = RequestMethod.GET)
    public String getSignUpPage(){
        return "sign_up";
    }
}
