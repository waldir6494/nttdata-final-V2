package com.nttdata.app.account.service;

import com.nttdata.app.account.model.CreateStaff;
import com.nttdata.app.account.model.entity.Staff;

import java.util.List;

public interface IStaffService {

    public Staff create (CreateStaff staff);
    public List<Staff> all();
    public Staff show(Long id);
}
