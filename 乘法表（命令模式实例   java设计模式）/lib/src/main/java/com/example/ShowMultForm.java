package com.example;

        public class ShowMultForm {
            public void show(){
                for(int i=1;i<=9;i++) {
                    for (int j = 1; j <= i; j++) {
                        System.out.print(j + "x" + i + "=" + j * i + " ");
                    }
                    System.out.println(" ");
                }
            }
}
