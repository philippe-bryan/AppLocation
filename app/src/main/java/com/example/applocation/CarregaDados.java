package com.example.applocation;

public class CarregaDados {
        private String name;
        private String fullName;
        private String placeOfBirth;

        public  CarregaDados(String name, String fullName, String placeOfBirth){
            this.name = name;
            this.fullName = fullName;
            this.placeOfBirth = placeOfBirth;
        }

        public String getName(){return this.name;}
        public String getFullName(){return this.fullName;}
        public String getPlaceOfBirth(){return this.placeOfBirth;}
}
