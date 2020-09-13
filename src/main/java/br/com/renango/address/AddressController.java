package br.com.renango.address;

import br.com.renango.address.model.Address;
import br.com.renango.address.model.AddressDTO;
import br.com.renango.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    AddressService service;

    @GetMapping
    ResponseEntity<List<Address>> getAllAddress(){
        try{
            return ResponseEntity.ok(service.getAllAddress());
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<Address> getAddressById(@PathVariable(value = "id") Long id){
        try{
            Optional<Address> addressOptional = service.getAddress(id);
            if(addressOptional.isPresent()){
                return ResponseEntity.ok(addressOptional.get());
            }
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    ResponseEntity<Address> createAddress(@Valid @RequestBody AddressDTO address, UriComponentsBuilder uriBuilder){
        Address addressCreated = null;
        try{
            if(address.getId() == null){
                addressCreated = service.save(address.createAddress());
            }else{
                throw new Exception("Id should be null on creation");
            }
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        URI uri = uriBuilder.path("/api/{id}").buildAndExpand(addressCreated.getId()).toUri();
        return ResponseEntity.created(uri).body(addressCreated);
    }

    @PutMapping
    ResponseEntity<Address> updateAddress(@Valid @RequestBody AddressDTO address, UriComponentsBuilder uriBuilder){
        try{
            Optional<Address>  addressOptional = service.getAddress(address.getId());
            if(addressOptional.isPresent()){
                service.save(addressOptional.get());
                URI uri = uriBuilder.path("/api/{id}").buildAndExpand(addressOptional.get().getId()).toUri();
                return ResponseEntity.created(uri).body(addressOptional.get());
            }
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping
    ResponseEntity deleteAddress(@Valid @RequestBody AddressDTO address){
        try{
            Optional<Address>  addressOptional = service.getAddress(address.getId());
            if(addressOptional.isPresent()){
                service.delete(addressOptional.get());
                return ResponseEntity.ok().build();
            }
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }



}
