package ua.donskykh.mybookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ua.donskykh.mybookstore.domain.User;
import ua.donskykh.mybookstore.domain.UserModel;
import ua.donskykh.mybookstore.service.UserService;

import javax.servlet.http.HttpSession;

@ControllerAdvice
public class CommonController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private UserService userService;

    private UserModel userModel = null;

    @ModelAttribute("userModel")
    public UserModel getUserModel() {
        if (httpSession.getAttribute("userModel") == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(authentication.getName());
            if (user != null) {
                userModel = new UserModel();
                userModel.setId(user.getId());
                userModel.setEmail(user.getEmail());
                userModel.setRole(user.getRole());
                userModel.setFullName(user.getName() + " " + user.getSurname());
                if (userModel.getRole().equalsIgnoreCase("USER")) {
                    userModel.setShoppingCart(user.getShoppingCart());
                }
                httpSession.setAttribute("userModel", userModel);
            }
        }
        return (UserModel) httpSession.getAttribute("userModel");
    }

}
