package mu.lab.controller;

/**
 * Created by zhaoxin on 14-12-19.
 */
import mu.lab.repo.CourseRepository;
import mu.lab.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ArrayList;
@Controller
@RequestMapping("/ajax")
public class AJAXController {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Object ajax(HttpServletRequest request){
        List<String> list = new ArrayList<String>();
        list.add("hello!");
        return "hello";
    }
}