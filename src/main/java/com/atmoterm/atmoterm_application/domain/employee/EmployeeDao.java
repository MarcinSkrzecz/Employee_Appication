package com.atmoterm.atmoterm_application.domain.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {

    List<Employee> findAll();

    List<Employee> findEmployeesByDateOfEmploymentIsNotNullAndSalaryIsNotNull();

    Optional<Employee> findById(Long id);

    boolean existsById(Long id);

    Employee save(Employee employee);

    void deleteById(Long id);

}
