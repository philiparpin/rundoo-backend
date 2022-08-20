package com.example.rundoobackend;


import javax.persistence.*;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Long id;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "company_logo_path")
    private String companyLogoPath;
    @Column(name = "street_address")
    private String streetAddress;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "country")
    private String country;


    public Supplier(String companyName, String companyLogoPath, String streetAddress, String city, String state, String zipCode, String country) {
        this.companyName = companyName;
        this.companyLogoPath = companyLogoPath;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    public Supplier() {

    }
}