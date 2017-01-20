package spring.hotel.controller;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Fene4ka_ on 18.11.2016.
 */
@RestController
public class ClientController {

    @RequestMapping(value = { "/user", "/me"}, method = RequestMethod.GET)
    public Map<String, String> user(Principal principal) {
        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", (String) details.get("name"));
        map.put("email", (String) details.get("email"));
        return map;
    }
}
