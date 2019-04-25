package ua.donskykh.mybookstore.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;
import ua.donskykh.mybookstore.domain.Address;
import ua.donskykh.mybookstore.domain.RegistrationModel;
import ua.donskykh.mybookstore.domain.ShoppingCart;
import ua.donskykh.mybookstore.domain.User;
import ua.donskykh.mybookstore.service.AddressService;
import ua.donskykh.mybookstore.service.UserService;

@Component
public class RegistrationHandler {

    private final UserService userService;

    private final AddressService addressService;

    @Autowired
    public RegistrationHandler(@Qualifier("userService") UserService userService, @Qualifier("addressService") AddressService addressService) {
        this.userService = userService;
        this.addressService = addressService;
    }

    public RegistrationModel init() {
        return new RegistrationModel();
    }

    public void addUser(RegistrationModel registrationModel, User user) {
        try {
            if (registrationModel != null) {
                registrationModel.setUser(user);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void addBilling(RegistrationModel registrationModel, Address billing) {
        try {
            if (registrationModel != null) {
                registrationModel.setBilling(billing);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public String saveAll(RegistrationModel registrationModel)  {
        String transitionValue = "success";
        try {
            if (registrationModel != null) {
                User user = registrationModel.getUser();
                if (user.getRole().equalsIgnoreCase("USER")) {
                    ShoppingCart shoppingCart = new ShoppingCart();
                    shoppingCart.setUser(user);
                    user.setShoppingCart(shoppingCart);
                }
                userService.saveUser(user);
                Address billing = registrationModel.getBilling();
                billing.setUser(user);
                billing.setBilling(true);
                addressService.saveAddress(billing);
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        return transitionValue;
    }

    public String validateUser(User user, MessageContext messageContext) {
        String transitionValue = "success";
        if (!(user.getPassword().equals(user.getConfirmPassword()))) {
            messageContext.addMessage(new MessageBuilder().error().source("confirmPassword").defaultText("Password does not match the confirm Password").build());
            transitionValue = "failure";
        }
        if (userService.findUserByEmail(user.getEmail()) != null) {
            messageContext.addMessage(new MessageBuilder().error().source("email").defaultText("Email is already in use").build());
            transitionValue = "failure";
        }
        return transitionValue;
    }

}
