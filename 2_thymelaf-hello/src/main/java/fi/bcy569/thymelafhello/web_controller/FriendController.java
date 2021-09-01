package fi.bcy569.thymelafhello.web_controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fi.bcy569.thymelafhello.domain_class.Friend;


@Controller
public class FriendController {

	List<Friend> friends = new ArrayList<>();
	
		@GetMapping("index")
		public String getIndex(Model model){

			model.addAttribute("friends", friends);
			
			//FIXME Lomake tarvitsee olion, jonka POST vaiheessa täyttää, mutta
			// nyt listalle tulee joka toiselle riville tyhjä olio
			model.addAttribute("friend", new Friend());
			
			return "friend";
		}
				
		
		@PostMapping("index")
		public String setIndex(@ModelAttribute Friend friend,
				Model model) {
						
			friends.add(friend);
		
			model.addAttribute("friends", friends);
			
			return "redirect:/index";
		}
	
}
