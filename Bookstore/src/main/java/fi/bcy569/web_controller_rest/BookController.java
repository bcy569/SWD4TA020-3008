package fi.bcy569.web_controller_rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fi.bcy569.domain_class_pojo_orm.Book;
import fi.bcy569.domain_class_pojo_orm.interfaces.IBookRepository;
import fi.bcy569.domain_class_pojo_orm.interfaces.ICategoryRepository;

@Controller
public class BookController {

	@Autowired
	private IBookRepository bookRepository;
	
	@Autowired
	private ICategoryRepository categoryRepository;

	@RequestMapping("booklist")
	public String getBookList(Model model) {

		model.addAttribute("books", bookRepository.findAll());
		model.addAttribute("categories", categoryRepository.findAll());
		
		return "booklist";

	}

	@GetMapping("addbook")
	public String getAddBook(Model model) {

		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryRepository.findAll());

		return "addbook";

	}

	@PostMapping("add")
	public String setAdd(@ModelAttribute Book book) {

		bookRepository.save(book);

		return "redirect:/booklist";

	}

	@PostMapping("update")
	public String putUpdate(@ModelAttribute Book book) {

		bookRepository.save(book);

		return "redirect:/booklist";

	}

	@GetMapping("delete/{id}")
	public String deleteBookById(@PathVariable("id") Long id) {

		bookRepository.deleteById(id);

		return "redirect:/booklist";

	}

	@GetMapping("edit/{id}")
	public String editBookById(@PathVariable("id") Long id, Model model) {

		model.addAttribute("book", bookRepository.findById(id));
		model.addAttribute("categories", categoryRepository.findAll());
		return "editbook";

	}

}