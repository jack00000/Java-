package com.example;

import java.io.File;

/**
 * Created by 15581 on 2017/9/21.
 */

public interface EncryptStrategy {
    public  abstract void  encryptFile(File file);
    public  abstract  String decryptFile(File file);
}
