package de.vignold.vignoldmanagement.controller;

import de.vignold.vignoldmanagement.dao.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class HomeController
{
    private final UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/")
    public String home()
    {
        return "redirect:/swagger-ui/";
    }

}
