package com.hjc.TransactionTest;

/**
 * @author : Hjc
 * @date : 2018/6/4 0004 10:23
 * @description :
 */
public class TestProxy {
    public static void main(String[] args) {
        BookFacadeProxy proxy = new BookFacadeProxy();
        BookFacade bookFacade = (BookFacade) proxy.bind(new BookFacadeImpl());
        bookFacade.addBook();
    }
}
