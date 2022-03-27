package com.example.toserveyou.data_class;

public class employee_data {
    String employee_name,employee_section,id,email,password;

    public employee_data(String employee_name, String employee_section, String id) {
        this.employee_name = employee_name;
        this.employee_section = employee_section;
        this.id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public String getEmployee_section() {
        return employee_section;
    }

    public String getId() {
        return id;
    }
}
