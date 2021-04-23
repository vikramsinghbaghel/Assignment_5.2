package com.nagarro.employeemanagemet.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.employeemanagemet.entities.HRDetails;

public interface HRDao extends JpaRepository<HRDetails,Integer> {

}
