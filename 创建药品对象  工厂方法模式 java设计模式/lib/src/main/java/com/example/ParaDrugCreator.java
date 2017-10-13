package com.example;

public class ParaDrugCreator implements DrugCreator {
    @Override
    public Drug getDrug() {
        int []a={250,15,1,10};
        Drug drug=new Paracetamol("ffuefhef",a);
        return drug;
    }
}
