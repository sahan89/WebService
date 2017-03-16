package com.sahan.emp.controller;

import com.sahan.emp.EmployeeListVO;
import com.sahan.emp.EmployeeVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmployeeRestController {
    //Local storage of employees for demo; You will use database here 

    private static EmployeeListVO employees = new EmployeeListVO();

    //add some employees here
    public EmployeeRestController() {
        EmployeeVO empOne = new EmployeeVO(1, "Nimal", "Jayasingha", "nimalj@gmail.com");
        EmployeeVO empTwo = new EmployeeVO(2, "Gihan", "Siriwardhana", "gihans@yahoo.com");
        EmployeeVO empThree = new EmployeeVO(3, "Kamal", "Withanage", "kamalw@gmail.com");

        employees.getEmpList().add(empOne);
        employees.getEmpList().add(empTwo);
        employees.getEmpList().add(empThree);
    }

    //Utility methods for getting employee by id
    private EmployeeVO getEmployeeById(int id) {
        for (int i = 0; i < employees.getEmpList().size(); i++) {
            EmployeeVO employeeVO = new EmployeeVO();

            employeeVO = (EmployeeVO) employees.getEmpList().get(i);

            if (employeeVO.getId() == id) {
                return employeeVO;
            }
        }
        return null;
    }

    /**
     * HTTP GET - Get all employees
     *
     */
    @RequestMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<EmployeeListVO> getAllEmployeesJSON() {
        return new ResponseEntity<EmployeeListVO>(employees, HttpStatus.OK);
    }

    /**
     * HTTP POST - Create new Employee
     *
     */
    @RequestMapping(value = "/employees", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeVO employee) {
        employee.setId(employees.getEmpList().size() + 1);
        employees.getEmpList().add(employee);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    /**
     * HTTP PUT - Update employee
     *
     */
    @RequestMapping(value = "/employees/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public ResponseEntity<EmployeeVO> updateEmployee(@PathVariable("id") int id, @RequestBody EmployeeVO employee) {
        EmployeeVO emp = getEmployeeById(id);
        if (emp != null) {
            emp.setFname(employee.getFname());
            emp.setLname(employee.getLname());
            emp.setEmail(employee.getEmail());
            return new ResponseEntity<EmployeeVO>(emp, HttpStatus.OK);
        }
        return new ResponseEntity<EmployeeVO>(HttpStatus.NOT_FOUND);
    }

    /**
     * HTTP DELETE - Delete employee
     *
     */
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id) {
        EmployeeVO employee = getEmployeeById(id);
        if (employee != null) {
            employees.getEmpList().remove(employee);
            return new ResponseEntity<String>(HttpStatus.OK);
        }
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
}
