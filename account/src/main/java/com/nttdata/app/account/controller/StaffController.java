package com.nttdata.app.account.controller;

import com.netflix.discovery.converters.Auto;
import com.nttdata.app.account.model.CreateAccount;
import com.nttdata.app.account.model.CreateStaff;
import com.nttdata.app.account.model.entity.Account;
import com.nttdata.app.account.model.entity.Staff;
import com.nttdata.app.account.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StaffController {

    @Autowired
    IStaffService staffService;

    @PostMapping("/staff")
    @ResponseStatus(HttpStatus.CREATED)
    public Staff create(@RequestBody CreateStaff staff) {
        return staffService.create(staff);
    }

    @GetMapping("/staff")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Staff> all() {
        return staffService.all();
    }

    @GetMapping("/staff/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Staff show(@PathVariable Long id) {
        return staffService.show(id);
    }
}
