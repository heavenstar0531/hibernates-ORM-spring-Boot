package ru.lionsoft.javaee.jpa.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.annotations.Array;
import org.eclipse.persistence.annotations.Structure;
import org.eclipse.persistence.platform.database.oracle.annotations.OracleArray;

/**
 * Класс описывающий сущность типа Компания (COMPANY_TYP)
 * 
 * @author Igor Morenko <morenko at lionsoft.ru>
 */

@Entity
@Table(name = "COMPANIES")
@NamedQueries({
      @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c")
    , @NamedQuery(name = "Company.findByInn", query = "SELECT c FROM Company c WHERE c.inn = :inn")
    , @NamedQuery(name = "Company.findByName", query = "SELECT c FROM Company c WHERE c.name = :name")
})
// Дополняет аннотацию @Array. Требуется для СУБД Oracle, так как @Array не хватает для работы с Oracle. 
@OracleArray(name = "DEPARTMENT_LIST", nestedType = "DEPARTMENT_TYP", javaType = Department.class)
@XmlRootElement
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "INN")
    private Long inn;
    
    @Size(max = 200)
    @Column(name = "NAME")
    private String name;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    // Объектный тип встраиваемый в класс
    @Structure 
    @Column(name = "ADDRESS")
    private Address address;

    // Коллекция DEPARTMENT_LIST типа VARRAY (NESTED TABLE) 
//    @Array(databaseType = "DEPARTMENT_TYP[]", targetClass = Department.class)
    @Array(databaseType = "DEPARTMENT_LIST")
    @Column(name = "DEPARTMENTS")
    private Collection<Department> departments;

    public Company() {
    }

    public Company(Long inn) {
        this.inn = inn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getInn() {
        return inn;
    }

    public void setInn(Long inn) {
        this.inn = inn;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @XmlElementWrapper(name = "departments")
    @XmlElement(name = "department")
    public Collection<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Collection<Department> departments) {
        this.departments = departments;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inn != null ? inn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.inn == null && other.inn != null) || (this.inn != null && !this.inn.equals(other.inn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Company{" + "inn=" + inn + ", name=" + name + ", address=" + address + ", departments=" + /*departments + */'}';
    }
    
}
