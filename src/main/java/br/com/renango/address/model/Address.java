package br.com.renango.address.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity(name="address")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue()
    Long id;

    @Column
    String streetName;

    @Column
    Long number;

    @Column
    String complement;

    @Column
    String neighbourhood;

    @Column
    String city;

    @Column
    String state;

    @Column
    String country;

    @Column
    String zipCode;

    @Column
    String latitude;

    @Column
    String longitude;


}
