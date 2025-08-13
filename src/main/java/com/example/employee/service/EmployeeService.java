package com.example.employee.service;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.entity.Employee;
import com.example.employee.repo.EmployeeRepo;
import com.example.employee.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String saveEmployee(EmployeeDto employeeDto) {
        if (employeeRepo.existsById(employeeDto.getEId())){
            return VarList.RSP_DUPLICATED;
        }else {
            employeeRepo.save(modelMapper.map(employeeDto, Employee.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updateEmployee(EmployeeDto employeeDto) {
        if (employeeRepo.existsById(employeeDto.getEId())){
            employeeRepo.save(modelMapper.map(employeeDto, Employee.class));
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<EmployeeDto> getAllEmployees() {
       List<Employee> employeeList = employeeRepo.findAll();
       return modelMapper.map(employeeList, new TypeToken<ArrayList<EmployeeDto>>() {}.getType());
    }

    public EmployeeDto searchEmployee(int eId) {
        if (employeeRepo.existsById(eId)){
           Employee employee = employeeRepo.findById(eId).orElse(null);
           return modelMapper.map(employee,EmployeeDto.class);
        }else {
            return null;
        }
    }

}
