package mu.lab.controller;

/**
 * Created by zhaoxin on 14-12-19.
 */
import mu.lab.model.Score;
import mu.lab.repo.CourseRepository;
import mu.lab.repo.StudentRepository;
import mu.lab.service.IUserService;
import mu.lab.service.ScoreService;
import mu.lab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import scala.Int;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@Controller

public class MainPageController {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ScoreService scoreService;

    @Autowired
    IUserService userService;

    @RequestMapping(value="/Login",method = RequestMethod.GET)
    public String printWelcome(@RequestParam("Username")String username ,ModelMap model) {
        model.addAttribute("Username",username);
        return "MainPage";
    }
    @RequestMapping(value = "distance",method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
    @ResponseBody
    @Transactional
    public Object distance(@RequestParam("courses")String courses){
        String[] strs=courses.split(":");
        String result="";
        List<Score> scores=new ArrayList<Score>();
        for(int i=0; i < 1; i++){String[] l=strs[i].split(";");
            scores.add(scoreService.createFakeScore(l[0], Integer.valueOf(l[1])));
        }
        Map<Integer,Integer> map=userService.getDistanceDistribution(scores);
        for(int i=1; i <= 5; i++)
            result+=map.get(i)+":";
        return result;
    }
}