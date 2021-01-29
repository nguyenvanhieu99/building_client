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

import building.model.BuildingEmployee;

@Controller
@RequestMapping(value = "/building_employee")
public class BuildingEmployeeController {

	private RestTemplate rest = new RestTemplate();
	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	@GetMapping()
	public String homeBuildingEmployee(Model model) {
		List<BuildingEmployee> listBuildingEmployee = 
				Arrays.asList(rest.getForObject("http://localhost:8080/building_employee", BuildingEmployee[].class));
		model.addAttribute("listBuildingEmployee", listBuildingEmployee);
		return "building_employee/home";
	}
	
	@GetMapping("/edit/{id}")
	public String editBuildingEmployee(Model model, @PathVariable("id") long id) {
		model.addAttribute("id", id);
		model.addAttribute("employee", new BuildingEmployee());
		return "building_employee/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String editBuildingEmployee2(@PathVariable("id") long id, @ModelAttribute BuildingEmployee buildingEmployee,
			@RequestParam("dob") String dob) {

		try {
			Date date = formatter.parse(dob);
			
			buildingEmployee.setDateOfBirth(date);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		 
		rest.postForObject("http://localhost:8080/building_employee", buildingEmployee, BuildingEmployee.class);
		return "redirect:/building_employee";
	}
	
	@GetMapping("/add")
	public String addBuildingEmployee(Model model) {
		model.addAttribute("employee", new BuildingEmployee());
		return "building_employee/add";
	}
	
	@PostMapping("/add")
	public String addBuildingEmployee2(@ModelAttribute BuildingEmployee buildingEmployee,
			@RequestParam("dob") String dob) {
		try {
			Date date = formatter.parse(dob);
			buildingEmployee.setDateOfBirth(date);	
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		 
		rest.postForObject("http://localhost:8080/building_employee", buildingEmployee, BuildingEmployee.class);
		return "redirect:/building_employee";
	}
	
	@GetMapping(value="/delete/{id}")
	public String deleteBuildingEmployee(@PathVariable("id") long id) {
		rest.delete("http://localhost:8080/building_employee/{id}", id);
		return "redirect:/building_employee";
	}
	
	
	
	
}
