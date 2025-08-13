package com.example.employee.controller;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.dto.ResponseDto;
import com.example.employee.entity.Employee;
import com.example.employee.service.EmployeeService;
import com.example.employee.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ResponseDto responseDto;

    @PostMapping(value = "/save")
    public ResponseEntity saveEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            String res = employeeService.saveEmployee(employeeDto);
            if (res.equals("00")){
               responseDto.setCode(VarList.RSP_SUCCESS);
               responseDto.setMessage("Success");
               responseDto.setContent(employeeDto);
               return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);

            } else if (res.equals("06")) {
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Employee already exist");
                responseDto.setContent(employeeDto);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }else {
                responseDto.setCode(VarList.RSP_FAIL);
                responseDto.setMessage("Error");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage() );
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            String res = employeeService.updateEmployee(employeeDto);
            if (res.equals("00")){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(employeeDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);

            } else if (res.equals("01")) {
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Not found");
                responseDto.setContent(employeeDto);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }else {
                responseDto.setCode(VarList.RSP_FAIL);
                responseDto.setMessage("Error");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage() );
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
