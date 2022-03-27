package com.example.toserveyou.data_class;

public class report_class {
    long report_number;
    String employee_name,section, physical_address,program,date,time,problem_description,employee_id;

    public report_class( String date,String employee_name,String physical_address,String problem_description,String program,long report_number,  String section,   String time ,String employee_id) {
        this.report_number = report_number;
        this.employee_name = employee_name;
        this.section = section;
        this.physical_address = physical_address;
        this.program = program;
        this.date = date;
        this.time = time;
        this.problem_description = problem_description;
        this.employee_id=employee_id;
    }


    public String getEmployee_id() {
        return employee_id;
    }

    public long getReport_number() {
        return report_number;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public String getSection() {
        return section;
    }

    public String getPhysical_address() {
        return physical_address;
    }

    public String getProgram() {
        return program;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getProblem_description() {
        return problem_description;
    }
}
