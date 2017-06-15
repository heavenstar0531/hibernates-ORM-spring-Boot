package ru.lionsoft.javaee.jpa.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.eclipse.persistence.annotations.Struct;
import org.eclipse.persistence.platform.database.oracle.annotations.OracleObject;

/**
 * Класс описывающий тип Адрес (ADDRESS_TYP)
 * 
 * @author Igor Morenko <morenko at lionsoft.ru>
 */

// Встраиваемый тип в другую сущность
@Embeddable
// fields - определяет порядок полей в структуре
@Struct(name = "ADDRESS_TYP", fields = { "COUNTRY", "CITY", "STREET", "HOUSE", "OFFICE", "ZIP" } )
// Чтобы корректно работало в СУБД Oracle, иначе выдаются ??? в String полях (number почему то нормально)
@OracleObject(name = "ADDRESS_TYP", javaType = Address.class, fields = { } )
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "COUNTRY")
    private String country;
    
    @Column(name = "CITY")
    private String cityName;
    
    @Column(name = "STREET")
    private String streetName;
    
    @Column(name = "HOUSE")
    private String house;
    
    @Column(name = "OFFICE")
    private String office;
    
    @Column(name = "ZIP")
    private Integer zip;

    public Address() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Address{" + "country=" + country + ", city=" + cityName + ", street=" + streetName + ", house=" + house + ", office=" + office + ", zip=" + zip + '}';
    }
    
}
