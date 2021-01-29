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

import building.model.Company;
import building.model.Company;

@Controller
@RequestMapping(value = "/company")
public class CompanyController {
	private RestTemplate rest = new RestTemplate();
	private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	@GetMapping()
	public String homeCompany(Model model) {
		List<Company> listCompany = Arrays.asList(rest.getForObject("http://localhost:8080/company", Company[].class));
		model.addAttribute("listCompany", listCompany);
		return "company/home";
	}

	@GetMapping("/edit/{id}")
	public String editCompany(Model model, @PathVariable("id") long id) {
		model.addAttribute("id", id);
		model.addAttribute("company", new Company());
		return "company/edit";
	}

	@PostMapping("/edit/{id}")
	public String editCompany2(@PathVariable("id") long id, @ModelAttribute Company company) {

		rest.postForObject("http://localhost:8080/company", company, Company.class);
		return "redirect:/company";
	}

	@GetMapping("/add")
	public String addCompany(Model model) {
		model.addAttribute("company", new Company());
		return "company/add";
	}

	@PostMapping("/add")
	public String addCompany2(@ModelAttribute Company company) {
		rest.postForObject("http://localhost:8080/company", company, Company.class);
		return "redirect:/company";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteCompany(@PathVariable("id") long id) {
		rest.delete("http://localhost:8080/company/{id}", id);

		return "redirect:/company";

	}

}
