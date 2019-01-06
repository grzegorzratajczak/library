package pl.grzex.library.primary;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value = {"/",""}, method = RequestMethod.GET)
    public String getIndex(){
        return "index.html";
    }
}
