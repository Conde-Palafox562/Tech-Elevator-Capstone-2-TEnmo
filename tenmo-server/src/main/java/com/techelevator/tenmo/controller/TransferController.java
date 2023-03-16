package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@PreAuthorize("isAuthenticated()")
@RestController
public class TransferController {

    private JdbcTransferDao jdbcTransferDao;
    private AccountDao accountDao;
    private TransferDao transferDao;
    private UserDao userDao;

    public TransferController (AccountDao accountDao, TransferDao transferDao, UserDao userDao, JdbcTransferDao jdbcTransferDao) {
        this.accountDao = accountDao;
        this.transferDao = transferDao;
        this.userDao = userDao;
        this.jdbcTransferDao = jdbcTransferDao;
    }

    @RequestMapping(path = "/transfer/{transferId}", method = RequestMethod.GET)
    public Transfer getTransferById(@PathVariable int transferId){
        return transferDao.getTransfer(transferId);
    }

    @RequestMapping(path = "/accounts/{accountId}", method = RequestMethod.GET)
    public Transfer getTransferByAccountId(@PathVariable int accountId){
        return transferDao.getTransferByAccountId(accountId);
    }

    @RequestMapping(path = "/transfer", method = RequestMethod.POST)
    public HttpStatus createTransfer(@RequestBody int toAccountId, int fromAccountId, BigDecimal amount){
        transferDao.createTransfer(toAccountId, fromAccountId, amount);
        return HttpStatus.CREATED;
    }

}
