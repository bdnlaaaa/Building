package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
                ArrayList<String> list = new ArrayList<>();
                String line;
                while ((line = br.readLine()) != null) {
                    list.add(line);
                }
                List<Owner> ownerList = new ArrayList<>();
                List<Address> addressList = new ArrayList<>();
                List<Building> buildingList = new ArrayList<>();
                int i = 0;
                for(String s : list){
                    String[] arr = s.split(" ");
                    ownerList.add(new Owner(arr[0], arr[1], arr[2]));
                    addressList.add(new Address(arr[3], arr[4]));
                    buildingList.add(new Building(ownerList.get(i), addressList.get(i), Float.parseFloat(arr[5])));
                    i++;
                }
                for(Building building : buildingList){
                    bw.write(building.info() + "\n");
                }
                Building copy = new Building(buildingList.get(5));
                Owner newOwner = new Owner("Alekseev", "Dmitrii", "Ivanovich");
                copy.changeOwner(newOwner);
                System.out.println(buildingList.get(5).info() + "\n" + copy.info());
                System.out.println(buildingList.get(5).getCount());
                Collections.sort(buildingList);
                bw.write("\n");
                for(Building building : buildingList){
                    bw.write(building.info() + "\n");
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class Building implements Comparable<Building> {
    private Float distance;
    private String street, building_number, name, surname, patronymic;
    private static Integer count = 0;
    public Building(Owner owner, Address address, Float distance){
        street = address.getStreet();
        building_number = address.getBuilding_number();
        this.distance = distance;
        name = owner.getName();
        surname = owner.getSurname();
        patronymic = owner.getPatronymic();
        count++;
    }
    public Building(){
        count++;
    }

    public Building(Building building){
        street = building.getStreet();
        building_number = building.getBuilding_number();
        distance = building.getDistance();
        name = building.getName();
        surname = building.getSurname();
        patronymic = building.getPatronymic();
        count++;
    }

    public String getStreet(){
        return street;
    }

    public String getBuilding_number(){
        return building_number;
    }

    public Float getDistance(){
        return distance;
    }

    public void setDistance(Float distance){
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String info(){
        return "Дом на улице " + street + " " + building_number + ", расстояние до вокзала "
                + distance + ", владелец " + surname + " " + name + " " + patronymic;
    }

    public void changeOwner(Owner owner){
        name = owner.getName();;
        surname = owner.getSurname();
        patronymic = owner.getPatronymic();
    }

    public Integer getCount(){
        return count;
    }

    @Override
    public int compareTo(Building o) {
        int result = this.distance.compareTo(o.distance);
        if(result == 0){
            result = this.street.compareTo(o.street);
            if(result == 0){
                result = this.building_number.compareTo(o.building_number);
            }
        }
        return result;
    }
}
class Owner{
    private String name, surname, patronymic;
    public Owner(String surname, String name, String patronymic){
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPatronymic() {
        return patronymic;
    }
}
class Address{
    private String street, building_number;
    public Address(String street, String building_number){
        this.street = street;
        this.building_number = building_number;
    }
    public void setStreet(String street){
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setBuilding_number(String building_number) {
        this.building_number = building_number;
    }

    public String getBuilding_number() {
        return building_number;
    }
}
