package com.hjc.TransactionTest;

/**
 * @author : Hjc
 * @date : 2018/6/4 0004 10:30
 * @description :
 */
public class TestCglib {
    public static void main(String[] args) {
        BookFacadeCglib cglib = new BookFacadeCglib();
        BookFacadeImpl bookFacade = (BookFacadeImpl) cglib.getInstance(new BookFacadeImpl());
        bookFacade.addBook();
    }
}
