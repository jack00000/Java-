package com.example;

/**
 * Created by 15581 on 2017/9/18.
 */

public class MultiCommand implements Command{
    ShowMultForm showMultForm;
    MultiCommand(ShowMultForm showMultForm){
        this.showMultForm=showMultForm;

    }
    public void execute(){
        showMultForm.show();
    }
    public  String getName(){
        return "显示惩罚表";
    }
}
