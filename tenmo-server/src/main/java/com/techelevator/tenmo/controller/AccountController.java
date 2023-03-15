package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;

import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {

    @Autowired
    private JdbcAccountDao accountDao;
    @Autowired
    private UserDao userDao;

    public AccountController(UserDao dao, JdbcAccountDao accountDao) {
        this.userDao = dao;
        this.accountDao = accountDao;
    }



}