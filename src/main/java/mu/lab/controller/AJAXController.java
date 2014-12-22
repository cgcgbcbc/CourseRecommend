package mu.lab.controller;

/**
 * Created by zhaoxin on 14-12-19.
 */
import mu.lab.model.Course;
import mu.lab.model.Score;
import mu.lab.model.Student;
import mu.lab.repo.CourseRepository;
import mu.lab.repo.StudentRepository;
import mu.lab.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ajax")
public class AJAXController {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseService courseService;
    @Autowired
    IUserService userService;
    @Autowired
    SimpleCourseRecommendation simpleCourseRecommendation;
    @Autowired
    CourseRecommendation courseRecommendation;
    @Autowired
    ScoreService scoreService;

    @RequestMapping(method = RequestMethod.GET,produces = "text/plain;charset=utf-8")
    @ResponseBody
    @Transactional
    public Object ajax(@RequestParam("action")String action, @RequestParam("page") Integer page,@RequestParam("username")String Username,HttpServletRequest request){
        if(action.equals("allcourses")) {
            Iterable<Course> courses = courseService.getCourses(page);
            String result = "";
            for (Course course : courses) {
                result += course.courseName +";"+course.courseId+ "\n";
            }

            return result;
        }
        else if(action.equals("mycourse")){
            System.out.println(Username.hashCode());
            Student student=userService.getRandomStudent(Math.abs(Username.hashCode()));
            String result="";
            for(Score s: student.scoreSet){
                result+=s.getCourse().courseName+";"+s.getScore()+";"+s.getCourse().courseId +"\n";
            }
            System.out.println(student.getId());
            System.out.println(result);
            return result;
        }
        return "";
    }

    @RequestMapping(method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
    @ResponseBody
    @Transactional
    public Object ajaxRec(@RequestParam("courses")String courses){
        String[] strs=courses.split(":");
        String result="";
        List<Score> scores=new ArrayList<Score>();
        for(int i=0; i < 1; i++){String[] l=strs[i].split(";");
            scores.add(scoreService.createFakeScore(l[0], Integer.valueOf(l[1])));
        }
        Iterable<Course> courses1=simpleCourseRecommendation.getRecommendCourseBasedOnMockScores(scores);
        Iterable<Course> courses2=courseRecommendation.getRecommendCourseBasedOnMockScores(scores);
        for(Course course:courses1){
            result+=course.courseName+";";
        }
        result+=":";
        for(Course course:courses2){
            result+=course.courseName+";";
        }
        System.out.println(result);
        return result;
    }
}