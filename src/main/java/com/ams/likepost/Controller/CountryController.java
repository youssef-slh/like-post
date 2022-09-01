package com.ams.likepost.Controller;

import com.ams.likepost.DAO.Country;
import com.ams.likepost.DAO.UserSecurity;
import com.ams.likepost.Service.CountryService;
import com.ams.likepost.Service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/Country")
public class CountryController {
    @Autowired
    private CountryService countryService;


    Map<String,String> map=new HashMap<String,String>();

}
