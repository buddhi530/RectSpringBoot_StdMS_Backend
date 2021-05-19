package com.example.demo.repostory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Employee;

public interface EmployeRepository extends JpaRepository<Employee, Integer> {

}
