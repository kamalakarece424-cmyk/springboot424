package com.exapmle.rio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Employee;
import com.example.demo.EmployeeRepository;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {
	@Autowired
	private EmployeeRepository repo;
	
	@GetMapping("/allemployees")
	public List<Employee> getEmployees(){
		return repo.findAll();
	}
	
	@PostMapping("/addemployee")
	public Employee addEmployee(@RequestBody Employee employee) {
		return repo.save(employee);
	}
	
	@GetMapping("/updatedsalary/{name}/{percent}")
	public Object updateSalary(@PathVariable String name, @PathVariable Integer percent) {
		Optional<Employee> e=repo.findByName(name);
		if(e.isPresent()) {
			Employee employeepresent=e.get();
			Integer salary=employeepresent.getSalary();
			Integer us=salary+(salary*percent)/100;
			employeepresent.setSalary(us);
			repo.save(employeepresent);
			return employeepresent.getSalary();
		}else
			return "Employee not found";
	}

	
}
	

	


