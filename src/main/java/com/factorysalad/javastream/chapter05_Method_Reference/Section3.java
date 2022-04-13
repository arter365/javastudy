package com.factorysalad.javastream.chapter05_Method_Reference;

import com.factorysalad.javastream.chapter05_Method_Reference.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/*
클래스의 생성자를 지정할 때 사용하는 방법.
 */
public class Section3 {
    public static void main(String[] args) {
        // 객체를 생성자 메서드 레퍼런스
        BiFunction<Integer, String, User> userCreator = User::new;
        User charlie = userCreator.apply(3, "Charlie");
        System.out.println(charlie);
        // 결과 : User{id=3, name='Charlie'}

        // -------------------------------------------------------

        // 기존에 아래의 데이터로 생성하려면 if문을 사용해야 했다. 새로운 차가 추가되면 if 문이 추가되어야 한다.
        String[][] inputs = new String[][] {
                { "sedan", "Sonata", "Hyundai" },
                { "van", "Sienna", "Toyota" },
                { "sedan", "Model S", "Tesla" },
                { "suv", "Sorento", "KIA" }
        };

        Map<String, BiFunction<String, String, Car>> carTypeToConstructorMap = new HashMap<>();
        // 아래와 같이 각각의 차 종류별로 생성자 메서드 레퍼런스를 만들었다.
        carTypeToConstructorMap.put("sedan", Sedan::new);
        carTypeToConstructorMap.put("suv", Suv::new);
        carTypeToConstructorMap.put("van", Van::new);

        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < inputs.length; i++) {
            String[] input = inputs[i];
            String carType = input[0];
            String name = input[1];
            String brand = input[2];

            // 값으로 넘어오는 내용 중 carType을 키로 사용하여 생성자 메서드를 호출 한다.
            // 생성된 car를 리스트에 추가한다.
            cars.add(carTypeToConstructorMap.get(carType).apply(name, brand));
        }

        for (Car car : cars) {
            car.drive();
        }
        /*
        결과 :
        Driving a sedan Sonata from Hyundai
        Driving a van Sienna from Toyota
        Driving a sedan Model S from Tesla
        Driving an SUV Sorento from KIA
         */
    }
}
