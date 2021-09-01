package fi.bcy569.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FirstController {

	@RequestMapping("index")
	@ResponseBody
	public String getIndex() {
		// TODO Auto-generated constructor stub
		return "This is the main page";
	}

	@RequestMapping("contact")
	@ResponseBody
	public String getContact() {
		// TODO Auto-generated constructor stub
		return "This is the contact page";
	}
	
	@RequestMapping("hello")
	@ResponseBody
	public String getHello(@RequestParam(name="location", required=false, defaultValue="Pasila") String location,
			@RequestParam(name="name", required=false, defaultValue="SWD4TA020-3008") String name) {
		// TODO Auto-generated constructor stub
		return "Welcome to the " + location + " " + name + "!";
	}


}
