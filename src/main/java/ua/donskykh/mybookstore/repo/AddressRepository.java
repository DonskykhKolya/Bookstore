package ua.donskykh.mybookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.donskykh.mybookstore.domain.Address;

@Repository("addressRepository")
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findAddressByBilling(boolean billing);

}
