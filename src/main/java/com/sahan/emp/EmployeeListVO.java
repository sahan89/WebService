package com.sahan.emp;


import java.util.ArrayList;
import java.util.List;


public class EmployeeListVO {
    private List<EmployeeVO> empList;

    public EmployeeListVO() {
        this.empList = new ArrayList<>();
    }

    public List getEmpList() {
        return empList;
    }

    public void setEmpList(List empList) {
        this.empList = empList;
    }
}
