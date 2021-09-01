package fi.bcy569.web_controller_rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {

	@RequestMapping("index")
	@ResponseBody
	public String getIndex(@RequestParam(name="title", required=false) String title,
			@RequestParam(name="auihor", required=false) String auihor,
			@RequestParam(name="year", required=false) String year,
			@RequestParam(name="isbn", required=false) String isbn,
			@RequestParam(name="price", required=false) String price,
			Model model)
	{
		
//		model.addAttribute("books", books);
		return "Hellurei!";
		
	}
	
}

