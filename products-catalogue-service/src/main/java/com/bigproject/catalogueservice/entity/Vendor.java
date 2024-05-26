package com.bigproject.catalogueservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "catalogue", name = "t_vendor")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "c_vendor_name")
    private String vendorName;


    @Column (name = "c_vendor_mail;")
    private String vendorMail;

    @Column(name = "c_vendor_phone_number")
    private String vendorPhoneNumber;

    @Column(name = "c_vendor_address")
    private String vendorAddress;

    @Column(name = "c_city")
    private String city;

    @Column(name = "c_is_active")
    private boolean isActive;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private List<Product> products = new ArrayList<>();
}
