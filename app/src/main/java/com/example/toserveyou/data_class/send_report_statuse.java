package com.example.toserveyou.data_class;

public class send_report_statuse extends report_class {
    String statuse,answer,report_id;

    public send_report_statuse(String date, String employee_name, String physical_address, String problem_description, String program, long report_number, String section, String time, String employee_id,String report_id, String statuse, String answer) {
        super(date, employee_name, physical_address, problem_description, program, report_number, section, time, employee_id);
        this.statuse = statuse;
        this.answer = answer;
        this.report_id = report_id;
    }

    public String getStatuse() {
        return statuse;
    }

    public String getAnswer() {
        return answer;
    }

    public String getReport_id() {
        return report_id;
    }
}
