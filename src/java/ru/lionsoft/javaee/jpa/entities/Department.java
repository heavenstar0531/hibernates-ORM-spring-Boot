package ru.lionsoft.javaee.jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import org.eclipse.persistence.annotations.Array;
import org.eclipse.persistence.annotations.Struct;
import org.eclipse.persistence.platform.database.oracle.annotations.OracleArray;
import org.eclipse.persistence.platform.database.oracle.annotations.OracleObject;

/**
 * Класс описывающий тип Отдел (DEPARTMENT_TYP)
 * 
 * @author Igor Morenko <morenko at lionsoft.ru>
 */

// Встраиваемый тип в другую сущность
@Embeddable
// fields - определяет порядок полей в структуре
@Struct(name = "DEPARTMENT_TYP", fields = { "ID", "NAME", "EMPLOYEES" })
// Чтобы корректно работало в Oracle иначе выдаются ??? в String полях (number почему то нормально)
@OracleObject(name = "DEPARTMENT_TYP", javaType = Department.class, fields = { })
// Дополняет аннотацию @Array. Требуется для СУБД Oracle, так как @Array не хватает для работы с Oracle. 
@OracleArray(name = "EMPLOYEE_LIST", nestedType = "EMPLOYEE_TYP", javaType = Employee.class)
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    
    @Column(name = "NAME")
    private String name;
    
    // Коллекция EMPLOYEE_LIST типа VARRAY (NESTED TABLE) 
//    @Array(databaseType = "EMPLOYEE_TYP[]", targetClass = Employee.class)
    @Array(databaseType = "EMPLOYEE_LIST")
    @Column(name = "EMPLOYEES")
    private Collection<Employee> employees;

    public Department() {
    }

    public Department(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElementWrapper(name = "employees")
    @XmlElement(name = "employee")
    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.id == null && other.id != null)
         || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", name=" + name + ", employees=" + employees + '}';
    }

}
