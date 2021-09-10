package fi.bcy569.web_controller_rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.bcy569.domain_class_pojo_orm.Book;
import fi.bcy569.domain_class_pojo_orm.interfaces.IBookRepository;

@Controller
public class BookController {

	@Autowired
	private IBookRepository bookRepository;
	
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
		
		List<Book> books = bookRepository.findByTitle("Eemelin seikkailut");
		
		return books.toString();
		
	}
	
}

