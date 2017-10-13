package com.example;

import java.io.File;

/**
 * Created by 15581 on 2017/9/19.
 */

public class EncodeContext {
    EncryptStrategy strategy;
    public void setStrategy(EncryptStrategy strategy){

        this.strategy=strategy;
    }
    public void encryptFile(File file){
        strategy.encryptFile(file);
    }
    public String decryptFile(File file){
        return strategy.decryptFile(file);
    }
}
