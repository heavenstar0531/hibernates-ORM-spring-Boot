package ru.lionsoft.javaee.jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import org.eclipse.persistence.annotations.Struct;
import org.eclipse.persistence.platform.database.oracle.annotations.OracleObject;

/**
 * Класс описывающий тип Сотрудник (EMPLOYEE_TYP)
 * 
 * @author Igor Morenko <morenko at lionsoft.ru>
 */

// Встраиваемый тип в другую сущность
@Embeddable
// fields - определяет порядок полей в структуре
@Struct(name = "EMPLOYEE_TYP", fields = { "NUM", "FIRST_NAME", "LAST_NAME", "JOB", "SALARY" })
// Чтобы корректно работало в Oracle иначе выдаются ??? в String полях (number почему то нормально)
@OracleObject(name = "EMPLOYEE_TYP", javaType = Employee.class, fields = { } )
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    
//    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUM")
    private Long num;
    
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "JOB")
    private String job;

    @Column(name = "SALARY")
    private BigDecimal salary;

    public Employee() {
    }

    public Employee(Long num) {
        this.num = num;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (num != null ? num.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.num == null && other.num != null) 
         || (this.num != null && !this.num.equals(other.num))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employee{" + "num=" + num + ", firstName=" + firstName + ", lastName=" + lastName + ", job=" + job + ", salary=" + salary + '}';
    }

}
