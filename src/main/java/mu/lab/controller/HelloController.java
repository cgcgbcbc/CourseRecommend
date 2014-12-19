package mu.lab.controller;

import mu.lab.service.ICourseRecommendation;
import mu.lab.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloController {

	@Qualifier("courseRecommendation")
	@Autowired
	ICourseRecommendation recommendationService;

	@Qualifier("simpleCourseRecommendation")
	@Autowired
	ICourseRecommendation simpleRecommendationService;

	@Autowired
	IUserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}
}