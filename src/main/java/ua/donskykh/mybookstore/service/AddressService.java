package ua.donskykh.mybookstore.service;

import ua.donskykh.mybookstore.domain.Address;

public interface AddressService {

    boolean saveAddress(Address address);

    Address findAddressByBilling(boolean billing);
}
