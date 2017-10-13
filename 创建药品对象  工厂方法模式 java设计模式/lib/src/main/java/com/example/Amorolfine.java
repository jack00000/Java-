package com.example;

public class Amorolfine extends Drug {
    String part1="ncaifofjsoaf";
    String part2="nfdefegsgsgf";
    public Amorolfine(String name,int[]a){
        this.name=name;
        part1=part1+":"+a[0]+"毫克";
        part2=part2+":"+a[1]+"毫克";

        constitute=part1+part2;
    }



}
