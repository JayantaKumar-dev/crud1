package com.crm1.service;

import com.crm1.entity.Employee;
import com.crm1.exception.ResourceNotFound;
import com.crm1.payload.EmployeeDto;
import com.crm1.repository.EmployeeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
//import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {


    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }
    public EmployeeDto addEmployee(EmployeeDto dto) {
        // Save employee to the database
        Employee employee = mapToEntity(dto);
        Employee emp = employeeRepository.save(employee);
        EmployeeDto employeeDto = mapToDto(emp);
        employeeDto.setDate(new Date());
        return employeeDto;
    }


    public void deleteEmployee(Long id) {
        // Delete employee from the database
        employeeRepository.deleteById(id);
    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto dto) {
        Employee employee = mapToEntity(dto);
        employee.setId(id);
        Employee updateEmployee = employeeRepository.save(employee);
        EmployeeDto employeeDto = mapToDto(updateEmployee);
        return employeeDto;

    }

    public List<EmployeeDto> getEmployee(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable page = PageRequest.of( pageNo, pageSize, sort );
        Page<Employee> all = employeeRepository.findAll(page);
        List<Employee> employees = all.getContent();
        List<EmployeeDto> dto = employees.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return dto;
    }

    EmployeeDto mapToDto(Employee employee){
        EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);
//        EmployeeDto dto = new EmployeeDto();
//        dto.setId(employee.getId());
//        dto.setName(employee.getName());
//        dto.setEmailId(employee.getEmailId());
//        dto.setMobile(employee.getMobile());
        return dto;
    }

    Employee mapToEntity(EmployeeDto dto){
        Employee emp = modelMapper.map(dto, Employee.class);

//        Employee emp = new Employee();
//        emp.setId(dto.getId());
//        emp.setName(dto.getName());
//        emp.setEmailId(dto.getEmailId());
//        emp.setMobile(dto.getMobile());
        return emp;
    }


    public EmployeeDto getEmployeeById(long empId) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(()->new ResourceNotFound("No Record found With the id " + empId));
        EmployeeDto dto = mapToDto(employee);
        return dto;
    }
}
