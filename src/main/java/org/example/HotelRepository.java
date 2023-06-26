package org.example;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public final class HotelRepository {
    Gson gson = new Gson();
    private static HotelRepository INSTANCE ;


    List<Hotel> hotelList = new ArrayList<Hotel>();

    public HotelRepository() {
        hotelList.add(new Hotel(1 , "vista lago" ,"Yellow" , 1 , true));
        hotelList.add(new Hotel(2 , "vista mare " ,"White" , 50 , false));
        hotelList.add(new Hotel(3 , "vista Citta " ,"Blue" , 700 , false));
        hotelList.add(new Hotel(4 , "centro citta " ,"Red" , 580 , true));
        hotelList.add(new Hotel(5 , "vista mare " ,"Black" , 530 , false));
        hotelList.add(new Hotel(6 , "vista fiume " ,"Orange" , 250 , true));

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

    Hotel mostExpSuite() {
        List<Hotel> suiteHotels = new ArrayList<>();
        for (Hotel hotel : hotelList) {
            if (hotel.isSuite()) {
                suiteHotels.add(hotel);
            }
        }
        suiteHotels.sort((o1, o2) -> {
            if (o1.getPrice() > o2.getPrice()) {
                return -1;
            } else if (o1.getPrice() < o2.getPrice()) {
                return 1;
            }
            return 0;
        });

        if (!suiteHotels.isEmpty()) {
            return suiteHotels.get(0);
        }

        return null;
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
