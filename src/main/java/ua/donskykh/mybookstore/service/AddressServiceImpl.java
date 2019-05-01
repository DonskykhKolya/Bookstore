package ua.donskykh.mybookstore.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.donskykh.mybookstore.domain.Address;
import ua.donskykh.mybookstore.repo.AddressRepository;

@Service("addressService")
public class AddressServiceImpl implements AddressService {

    @Qualifier("addressRepository")
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional
    public boolean saveAddress(Address address) {
        addressRepository.saveAndFlush(address);
        return true;
    }

    @Override
    @Transactional
    public Address findAddressByBilling(boolean billing) {
        return addressRepository.findAddressByBilling(billing);
    }

}
