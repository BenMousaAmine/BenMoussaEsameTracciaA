package org.example;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public final class HotelRepository {
    Gson gson = new Gson();
    private static HotelRepository INSTANCE ;


    List<Hotel> hotelList = new ArrayList<Hotel>();

    public HotelRepository() {
        hotelList.add(new Hotel(0 , "vista lago" ,"hotel Yelloz" , 1 , true));
        hotelList.add(new Hotel(1 , "vista mare " ,"hotel White" , 50 , false));
        hotelList.add(new Hotel(1 , "vista Citta " ,"hotel Blue" , 50 , false));
        hotelList.add(new Hotel(1 , "centro citta " ,"hotel Red" , 50 , true));
        hotelList.add(new Hotel(1 , "vista mare " ,"hotel Black" , 50 , false));
        hotelList.add(new Hotel(1 , "vista fiume " ,"hotel Orange" , 50 , true));

    };

    public static HotelRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new org.example.HotelRepository();
        }
        return INSTANCE;
    }

    //manda tutti hotel in json
   String allHotelToJson (){
        String jsonStr = gson.toJson(hotelList);
        return jsonStr;
    }


    // most expensive suite in hotel

    // !importanat manca ancora
    List<Hotel> mostExpSuite(){
        double expPrice = 0 ;
        List<Hotel> mostExp = new ArrayList<>();
        mostExp = hotelList ;
        //manca ancora fare il piu exp
        mostExp.sort((o1, o2) -> {
            if (o1.getPrice()>o2.getPrice())
                return 1;
            if (o1.getPrice()<o2.getPrice())
                return -1;
            return 0;
        });
        return  hotelList ;
    }

    String moreExpSuiteToJson (){
        String jsonExpensive = gson.toJson(mostExpSuite());
        return jsonExpensive;
    }

    // all Sorted Hotel by name :
    List<Hotel> allSorted() {
        List<Hotel> sortedHotel = new ArrayList<>();
        sortedHotel = hotelList ;
        sortedHotel.sort((o1, o2) -> {
            return o1.getName().compareTo(o2.getName());
        });

        return sortedHotel;
    }

    String allSortedToJson(){
        String jsonSorted = gson.toJson(allSorted());
        return jsonSorted;
    }



}
