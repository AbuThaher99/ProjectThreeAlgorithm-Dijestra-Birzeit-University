package com.example.projectthreealgorthem;

import javafx.scene.effect.Light;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.IOException;
import java.util.*;
//import java.util.ArrayList;
//import java.util.List;
//import org.opencv.core.Core;
//import org.opencv.core.Mat;
//import org.opencv.imgcodecs.Imgcodecs;

public class test {
    public static void main(String[] args) throws IOException {

//        String[] countries = {  "Argentina", "Brazil", "Chile", "China", "Algeria",
//                "Egypt", "Greenland",  "Iran",   "South Korea",
//                "Kazakhstan", "Canada",  "Mongolia",  "Niger", "Russia", "Saudi Arabia",
//                 "South Africa", "India",  "Libya", "Japan"  ,"Peru"  , "Jordan"
//       ,"France"  ,"Sweden","Mali","Sudan","Ethiopia","Finland","Spain","Portugal"
//                ,"Morocco","Pakistan" ,"Norway","North Sweden","Ukraine", "Palestine", "Yemen", "Oman", "Iraq",
//                "Turkey","Indonesia","Malaysia","Italy","UK","Mauritania","Syrian Arab Republic","Tunisia","Gana","Genia","Somalia","USA"};
//
//
//
//        String con[] = {"Bolivia", "Peru", "Colombia", "Palestinian", "Jordan", "Venezuela", "France", "United Arab Emirates", "Armenia", "Argentina", "Brazil", "Chile", "China", "Algeria", "Greenland", "Denmark", "India", "Iceland", "Iran", "Iraq", "Kyrgyzstan", "North Korea", "South Korea", "Kazakhstan", "Canada", "Monaco", "Mongolia", "Nigeria", "Niger", "Russia", "Saudi Arabia", "United States", "South Africa", "Egypt", "Australia", "Libya", "Japan", "Chad", "Sweden", "Mali", "Sudan", "Ethiopia", "Finland", "Angola", "Mexico", "Spain", "Portugal", "Congo [DRC]", "Morocco", "Pakistan", "New Zealand", "Congo [Republic]", "Cuba","Germany","Cameroon"};
//        System.out.println("countries.length = " + con.length);
//        generateRandomEdges(con);

//        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
//        pq.add(1);
//        pq.add(2);
//        pq.add(3);
//        pq.add(4);
//        pq.add(5);
//        System.out.println(pq.poll());
//
//        A.Shaheen,Bahrani,
//                A.Shaheen,Khoury,
//                A.Shaheen,Masrouji,

//                Bahrani,Khoury,
//                Bahrani,Masrouji,
//                Bahrani,Pharmacy,

//                Khoury,Masrouji,
//                Khoury,Pharmacy,
//                Khoury,IT,

//                Masrouji,Pharmacy,
//                Masrouji,IT,
//                Masrouji,Anjad Zaneni,

// how to calculate distance between two points on the map






        double d= distance(31.958582, 35.180308, 31.961755, 35.184654);
      //  double d1= distance(31.959246, 35.181170, 31.959300, 35.181044);
//        double d2= distance(31.960765, 35.182387, 31.961500, 35.183411);
//
//        double d3= distance(31.961132, 35.182919, 31.961102, 35.183393);
//        double d4= distance(31.961132, 35.182919, 31.961500, 35.183411);
//        double d5= distance(31.961132, 35.182919, 31.961928, 35.183154);
//
//        double d6= distance(31.961102, 35.183393, 31.961500, 35.183411);
//        double d7= distance(31.961102, 35.183393, 31.961928, 35.183154);
//        double d8= distance(31.961102, 35.183393, 31.961436, 35.184408);
//
//        double d9= distance(31.961500, 35.183411, 31.961928, 35.183154);
//        double d10= distance(31.961500, 35.183411, 31.961436, 35.184408);
//        double d11= distance(31.961500, 35.183411, 31.961122, 35.184128);

        // convert the value from km to meter
        d = d * 1000;
     //   d1 = d1 * 1000;
//        d2 = d2 * 1000;
//        d3 = d3 * 1000;
//        d4 = d4 * 1000;
//        d5 = d5 * 1000;
//        d6 = d6 * 1000;
//        d7 = d7 * 1000;
//        d8 = d8 * 1000;
//        d9 = d9 * 1000;
//        d10 = d10 * 1000;
//        d11 = d11 * 1000;

        System.out.println(d);
     //   System.out.println(d1);
//        System.out.println(d2);
//        System.out.println(d3);
//        System.out.println(d4);
//        System.out.println(d5);
//        System.out.println(d6);
//        System.out.println(d7);
//        System.out.println(d8);
//        System.out.println(d9);
//        System.out.println(d10);
//        System.out.println(d11);


    }

    public static void generateRandomEdges(String[] countries) {
        Random random = new Random();
        int n = countries.length;
        HashSet<String> uniqueEdges = new HashSet<>();

        while (uniqueEdges.size() < n * 6) {
            int i = random.nextInt(n);
            int j = random.nextInt(n);
            if (i != j) {
                uniqueEdges.add(countries[i] + "," + countries[j]);
            }
        }
        System.out.println("uniqueEdges.size() = " + uniqueEdges.size());

        for (String edge : uniqueEdges) {
            System.out.println(edge);
        }
    }

    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double R = 6371; // Earth radius in km
        return R * c;
    }


}
