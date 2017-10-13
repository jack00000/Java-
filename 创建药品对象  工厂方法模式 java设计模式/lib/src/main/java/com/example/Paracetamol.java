package com.example;

public class Paracetamol extends Drug{
    String part1="每粒含cshsjdh";
    String part2="每粒含dghgh";
    String part3="每粒含gggrgrg";
    String part4="每粒含gergrg";

    public Paracetamol(String name,int []a){
        this.name=name;
        part1=part1+":"+a[0]+"毫克";
        part2=part2+":"+a[1]+"毫克";
        part3=part3+":"+a[2]+"毫克";
        part4=part4+":"+a[3]+"毫克";
        constitute=part1+part2+part3+part4;
    }

}
