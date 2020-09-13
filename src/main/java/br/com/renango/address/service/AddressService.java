package br.com.renango.address.service;

import br.com.renango.address.model.Address;
import br.com.renango.address.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository repository;

    public Optional<Address> getAddress(Long id){
        return repository.findById(id);
    }

    public List<Address> getAllAddress(){
        return repository.findAll();
    }

    public Address save(Address address){
        repository.save(address);
        return address;
    }
    public void delete(Address address){
        repository.delete(address);
    }

}
