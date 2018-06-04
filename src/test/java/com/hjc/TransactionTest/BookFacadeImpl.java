package com.hjc.TransactionTest;

/**
 * @author : Hjc
 * @date : 2018/6/4 0004 10:17
 * @description :
 */
public class BookFacadeImpl implements BookFacade {

    @Transaction
    @Override
    public void addBook() {
        query();
        System.out.println("增加图书....");
    }

    @Override
    public void query() {
        System.out.println("查询是否可以增加图书....");
    }
}
