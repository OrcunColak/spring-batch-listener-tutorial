package com.colak.springtutorial.config.itemread;

import org.springframework.batch.core.ItemReadListener;

public class CustomItemReadListener<T> implements ItemReadListener<T> {

    @Override
    public void beforeRead() {
        System.out.println("Before reading item");
    }

    @Override
    public void afterRead(T item) {
        System.out.println("After reading item: " + item.toString());
    }

    @Override
    public void onReadError(Exception ex) {
        System.err.println("Error during item reading: " + ex.getMessage());
    }
}
