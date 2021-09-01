package fi.bcy569.thymelafhello.web_controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fi.bcy569.thymelafhello.domain_class.Student;

@Controller
public class HelloController {


		@RequestMapping("hello")
		public String getHello(@RequestParam(name="age", required=false) String age,
				@RequestParam(name="name", required=false) String name,
				Model model) {
			
			if(age !=null | name !=null){
				model.addAttribute("age", age);
				model.addAttribute("name", name);
				return "hello";
			}
			
			List<Student> students = new ArrayList<Student>();
			
			Student s1 = new Student("Juhani", "Siltanen");
			Student s2 = new Student("Elli", "Esimerkki");
			
			students.add(s1);
			students.add(s2);
			
			model.addAttribute("students", students);
			return "hello";

		}

}
