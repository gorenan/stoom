package br.com.renango.address.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter @NoArgsConstructor
public class AddressDTO {
    Long id;
    @NotNull @NotEmpty
    String streetName;
    @NotNull @NotEmpty
    Long number;
    String complement;
    @NotNull @NotEmpty
    String neighbourhood;
    @NotNull @NotEmpty
    String city;
    @NotNull @NotEmpty
    String state;
    @NotNull @NotEmpty
    String country;
    @NotNull @NotEmpty
    String zipCode;
    String latitude;
    String longitude;

    public AddressDTO(Long id, @NotNull @NotEmpty String streetName, @NotNull @NotEmpty Long number, @NotNull @NotEmpty String neighbourhood,
                      @NotNull @NotEmpty String city, @NotNull @NotEmpty String state, @NotNull @NotEmpty String country, @NotNull @NotEmpty String zipCode) {
        this.id = id;
        this.streetName = streetName;
        this.number = number;
        this.neighbourhood = neighbourhood;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }

    public Address createAddress(){
        Address address = new Address(this.getId(), this.getStreetName(), this.getNumber(), this.getComplement(),
                this.getNeighbourhood(), this.getCity(), this.getState(), this.getCountry(), this.getZipCode(),
                this.getLatitude(), this.getLongitude());
        return address;
    }






}
