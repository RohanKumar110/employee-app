package dev.rohankumar.employeeapp.service;

import dev.rohankumar.employeeapp.model.Employee;
import dev.rohankumar.employeeapp.repository.EmployeeRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> find() {
        return employeeRepository.findAll();
    }

    public Employee find(Integer id) {
        return employeeRepository.findById(id).get();
    }

    public Page<Employee> find(int pageNo, int pageSize) {
        if (pageNo < 1)
            pageNo = 1;
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return employeeRepository.findAll(pageable);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }
}