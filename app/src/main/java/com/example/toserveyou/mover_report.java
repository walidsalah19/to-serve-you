package com.example.toserveyou;

public class mover_report {
   static long report_number;
    static String employee_name,section, physical_address,program,date,time,problem_description,employee_id,answer,statuse,report_id;

    public static String getReport_id() {
        return report_id;
    }

    public static void setReport_id(String report_id) {
        mover_report.report_id = report_id;
    }

    public static String getEmployee_id() {
        return employee_id;
    }

    public static String getAnswer() {
        return answer;
    }

    public static void setAnswer(String answer) {
        mover_report.answer = answer;
    }

    public static String getStatuse() {
        return statuse;
    }

    public static void setStatuse(String statuse) {
        mover_report.statuse = statuse;
    }

    public static void setEmployee_id(String employee_id) {
        mover_report.employee_id = employee_id;
    }

    public static void setReport_number(long report_number) {
        mover_report.report_number = report_number;
    }

    public static void setEmployee_name(String employee_name) {
        mover_report.employee_name = employee_name;
    }

    public static void setSection(String section) {
        mover_report.section = section;
    }

    public static void setPhysical_address(String physical_address) {
        mover_report.physical_address = physical_address;
    }

    public static void setProgram(String program) {
        mover_report.program = program;
    }

    public static void setDate(String date) {
        mover_report.date = date;
    }

    public static void setTime(String time) {
        mover_report.time = time;
    }

    public static void setProblem_description(String problem_description) {
        mover_report.problem_description = problem_description;
    }

    public static long getReport_number() {
        return report_number;
    }

    public static String getEmployee_name() {
        return employee_name;
    }

    public static String getSection() {
        return section;
    }

    public static String getPhysical_address() {
        return physical_address;
    }

    public static String getProgram() {
        return program;
    }

    public static String getDate() {
        return date;
    }

    public static String getTime() {
        return time;
    }

    public static String getProblem_description() {
        return problem_description;
    }
}
