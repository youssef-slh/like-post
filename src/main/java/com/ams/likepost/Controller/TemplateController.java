package com.ams.likepost.Controller;

import com.ams.likepost.DAO.User;
import com.ams.likepost.DAO.UserSecurity;
import com.ams.likepost.Service.CountryService;
import com.ams.likepost.Service.UserSecurityService;
import com.ams.likepost.auth.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class TemplateController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private UserSecurityService userSecurityService;

    @GetMapping("login")
    public String  getLoginView(Model model){
        model.addAttribute("countries", countryService.getAllCountries());
        return "login";
    }

    @GetMapping("index")
    public String  getIndexView(@AuthenticationPrincipal ApplicationUser user, Model model){
        model.addAttribute("user",user.getAppUser());
        return "index";
    }

    @GetMapping("EmailVerification")
    public String  getEmailVerificationView(){
        return "emailVerification";
    }

    @GetMapping("Profile")
    public String  getProfileView(@AuthenticationPrincipal ApplicationUser user,Model model){
        model.addAttribute("user",user.getAppUser());
        return "profile";
    }

}
