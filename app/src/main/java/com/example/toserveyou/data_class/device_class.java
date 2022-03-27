package com.example.toserveyou.data_class;

public class device_class {
    String physical_address,program,pay, notes;

    public device_class(String physical_address, String program, String pay, String notes) {
        this.physical_address = physical_address;
        this.program = program;
        this.pay = pay;
        this.notes = notes;
    }

    public String getPhysical_address() {
        return physical_address;
    }

    public String getProgram() {
        return program;
    }

    public String getPay() {
        return pay;
    }

    public String getNotes() {
        return notes;
    }
}
