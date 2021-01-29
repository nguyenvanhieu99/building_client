package building.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import building.model.CompanyEmployee;

@Controller
@RequestMapping(value = "/company_employee")
public class CompanyEmployeeController {
	private RestTemplate rest = new RestTemplate();
	private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	@GetMapping()
	public String homeCompanyEmployee(Model model) {
		List<CompanyEmployee> listCompanyEmployee = 
				Arrays.asList(rest.getForObject("http://localhost:8080/company_employee", CompanyEmployee[].class));
		
		model.addAttribute("listCompanyEmployee", listCompanyEmployee);
		return "company_employee/home";
	}
	
	@GetMapping("/edit/{id}")
	public String editCompanyEmployee(Model model, @PathVariable("id") long id) {
		model.addAttribute("id", id);
		model.addAttribute("employee", new CompanyEmployee());
		return "company_employee/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String editCompanyEmployee2(@PathVariable("id") long id, @ModelAttribute CompanyEmployee companyEmployee,
			@RequestParam("dob") String dob) {
		
		try {
			Date date = formatter.parse(dob);
			companyEmployee.setDateOfBirth(date);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		rest.postForObject("http://localhost:8080/company_employee", companyEmployee, CompanyEmployee.class);
		return "redirect:/company_employee";
	}
	
	
	@GetMapping("/add")
	public String addCompanyEmployee(Model model) {
		model.addAttribute("employee", new CompanyEmployee());
		return "company_employee/add";
	}
	
	@PostMapping("/add")
	public String addCompanyEmployee2(@ModelAttribute CompanyEmployee companyEmployee, @RequestParam("dob") String dob) {
		try {
			Date date = formatter.parse(dob);
			companyEmployee.setDateOfBirth(date);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		
		rest.postForObject("http://localhost:8080/company_employee", companyEmployee, CompanyEmployee.class);
		return "redirect:/company_employee";		
	}
	
	@GetMapping(value = "/delete/{id}")
	public String deleteCompanyEmployee(@PathVariable("id") long id) {
		rest.delete("http://localhost:8080/company_employee/{id}", id);
		
		return "redirect:/company_employee";
		
	}
	
}
