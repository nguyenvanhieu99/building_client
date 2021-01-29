package building.controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import building.model.Service;

@Controller
@RequestMapping(value = "/service")
public class ServiceController {
	private RestTemplate rest = new RestTemplate();
	private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	@GetMapping()
	public String homeService(Model model) {
		List<Service> listService = Arrays.asList(rest.getForObject("http://localhost:8080/service", Service[].class));
		model.addAttribute("listService", listService);
		return "service/home";
	}
	
	@GetMapping("/edit/{id}")
	public String editService(Model model, @PathVariable("id") long id) {
		model.addAttribute("id", id);
		model.addAttribute("service", new Service());
		return "service/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String editService2(@PathVariable("id") long id, @ModelAttribute Service service) {
		
		rest.postForObject("http://localhost:8080/service", service, Service.class);
		return "redirect:/service";
	}
	
	
	@GetMapping("/add")
	public String addService(Model model) {
		model.addAttribute("service", new Service());
		return "service/add";
	}
	
	@PostMapping("/add")
	public String addService2(@ModelAttribute Service service) {
		rest.postForObject("http://localhost:8080/service", service, Service.class);
		return "redirect:/service";		
	}
	
	@GetMapping(value = "/delete/{id}")
	public String deleteService(@PathVariable("id") long id) {
		rest.delete("http://localhost:8080/service/{id}", id);
		
		return "redirect:/service";
		
	}
	
}
