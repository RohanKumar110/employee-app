package dev.rohankumar.employeeapp.controller;

import dev.rohankumar.employeeapp.model.Employee;
import dev.rohankumar.employeeapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String index(Model model) {
        return findPaginated(1, model);
    }

    @GetMapping("/employee/page/{pageNo}")
    public String findPaginated(@PathVariable int pageNo, Model model) {
        int pageSize = 5;
        Page<Employee> page = employeeService.find(pageNo, pageSize);
        model.addAttribute("employees", page.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalElements", page.getTotalElements());
        return "index";
    }

    @GetMapping("/employee/new")
    public String newForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "new";
    }

    @GetMapping("/employee/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        model.addAttribute("employee", employeeService.find(id));
        return "edit";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);
        return "redirect:/";
    }

    @PostMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        employeeService.delete(id);
        return "redirect:/";
    }
}
