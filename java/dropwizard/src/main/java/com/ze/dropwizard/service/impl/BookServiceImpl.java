package com.ze.dropwizard.service.impl;

import com.ze.dropwizard.service.BookService;
import org.jvnet.hk2.annotations.Service;

@Service
public class BookServiceImpl implements BookService{
    public String getBook(){
        return "book";
    }
}
