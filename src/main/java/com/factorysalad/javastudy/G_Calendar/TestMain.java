package com.factorysalad.javastudy.G_Calendar;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/*

[6일차] 
(#3 : 날짜) <br>

*/
public class TestMain {

    public static void main(String[] args) {
        TestMain testMain = new TestMain();
        testMain.example();
    }

    private void example() {
        // Date는 deprecate 되고 Calendar 클래스가 나옴
        // 하지만 Calendar 클래스도 아래와 같은 문제들로 인해 LocalDate 클래스가 나오기 전에는 Joda-Time라는 오픈소스를
        // 사용했다.
        // 불변객체가 아니다 , 윤년 안됨 , 상수 남용 , 일관성 없는 요일 상수등

        // 우선 Calendar를 알아보자 Calendar는 움직이는 시계인가 아니면 그 시점을 캡쳐한 객체일 뿐인가?
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");

        Calendar c = Calendar.getInstance();

        for (int i = 0; i < 3; i++) {
            try {
                // 1초 지연
                Thread.sleep(1000);
                System.out.println(sdf.format(c.getTime()));
            } catch (InterruptedException e) {

            }
        }
        // Calendar의 연,월,일 구하기 (Calendar 문제점 1. 상수를 너무 많이 사용함, 2. +1과 같이 직관적이지 않음.)
        System.out.println(c.get(Calendar.YEAR));
        System.out.println(c.get(Calendar.MONTH) + 1);
        System.out.println(c.get(Calendar.DAY_OF_MONTH));

        // Date의 사용 범위 (Date형 데이터를 Calendar로 넣거나 Calendar에서 Date 형태로 꺼낼 때 용도로만 사용됨.)
        Date d = new Date(); // 1970/1/1/0/0/0 부터 경과한 시간을 밀리초로 보관한다.
        System.out.println(d);

        Calendar c1 = Calendar.getInstance();
        c1.setTime(d); // (Calendar 문제점 3. set을 할 수 있기 때문에 악용될 수 있음.)
        System.out.println(sdf.format(c1.getTime()));

        // 경과시간 구하기
        long time_1 = System.currentTimeMillis();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long time_2 = System.currentTimeMillis();
        System.out.println((time_2 - time_1) / 1000.0);

        // 삼성에서 사용하는 자바로 주차계산 https://zzznara2.tistory.com/194

        // LocalDate : 날짜를 나타내는 클래스
        // LocalTime : 시간을 나타내는 클래스
        // LocalDateTime : 날짜, 시간을 모두 표현하는 클래스
        // ZonedDateTime : LocalDateTime + 시간대까지 표현하는 클래스.

        // 연산 ------------------------------
        // TemporalAdjusters : 날짜 계산에 사용되는 클래스
        // Period : 두 날짜간의 차이를 표현하기 위한 클래스
        // Duration : 두 시간의 차이를 표현하기 위한 클래스

        // 이들 클래스의 특징은 String클래스처럼 불변이라는 것이다.
        // 그래서 날짜나 시간을 변경하는 메서드들은 기존의 객체를 변경하는 대신 항상 새로운 객체를 반환한다.
        System.out.println("현재시간 가져오기==============================================");
        LocalDate date1 = LocalDate.now();
        LocalTime time1 = LocalTime.now();
        LocalDateTime dateTime1 = LocalDateTime.now();
        ZonedDateTime zonedDateTime1 = ZonedDateTime.now();

        System.out.println(date1); // 2019-10-28
        System.out.println(time1); // 11:22:18.525
        System.out.println(dateTime1); // 2019-10-28T11:22:18.525
        System.out.println(zonedDateTime1); // 2019-10-28T11:22:18.525+09:00[Asia/Seoul]

        System.out.println("LocalDate==============================================");
        System.out.println("년도 : " + date1.getYear());
        System.out.println("월(숫자) : " + date1.getMonthValue());
        System.out.println("월(문자) : " + date1.getMonth());
        System.out.println("일 : " + date1.getDayOfMonth());
        System.out.println("같은 해의 1월1일 부터 몇번째 일 : " + date1.getDayOfYear());
        System.out.println("이번주 몇 일째 : " + date1.getDayOfWeek().getValue());
        System.out.println("이달의 총 일수 : " + date1.lengthOfMonth());
        System.out.println("이 해의 총 일수 (윤년=366) : " + date1.lengthOfYear());
        System.out.println("윤년여부 : " + date1.isLeapYear());

        System.out.println("시 : " + time1.getHour());
        System.out.println("분 : " + time1.getMinute());
        System.out.println("초 : " + time1.getSecond());
        System.out.println("나노초 : " + time1.getNano());
        // LocalDateTime은 LocalDate와 LocalTime 두가지 메서드 모두 다 사용 할 수 있다.

        // 분해와 합치기
        LocalDate date11 = LocalDate.of(2015, 03, 18);
        LocalTime time11 = LocalTime.of(11, 30, 30);

        LocalDateTime lo11 = LocalDateTime.of(date11, time11); // 합치기
        LocalDate date12 = lo11.toLocalDate(); // 분해
        LocalTime time12 = lo11.toLocalTime(); // 분해

        // ZonedDateTime도 분해와 합치기가 가능하다.
        LocalDateTime lo13 = LocalDateTime.now();
        ZoneId zid = ZoneId.of("Asia/Seoul");
        ZonedDateTime zdt = lo13.atZone(zid); // atZone으로 합치기

        System.out.println("현재시간 설정하기==============================================");
        LocalDate date2 = LocalDate.of(2015, 03, 18);
        LocalTime time2 = LocalTime.of(11, 30, 30);
        LocalDateTime dateTime2 = LocalDateTime.of(date2, time2);
        ZonedDateTime zonedDateTime2 = ZonedDateTime.of(dateTime2, ZoneId.of("Asia/Seoul"));

        System.out.println(date2); // 2015-03-18
        System.out.println(time2); // 11:30:30
        System.out.println(dateTime2); // 2015-03-18T11:30:30
        System.out.println(zonedDateTime2); // 2015-03-18T11:30:30+09:00[Asia/Seoul]

        // 시간 비교
        System.out.println("isAfter : " + date1.isAfter(date2));
        System.out.println("isBefore : " + date1.isBefore(date2));
        System.out.println("isEqual : " + date1.isEqual(date2));

        // 날짜 계산을 위한 클래스 (TemporalAdjusters)
        LocalDate today = LocalDate.now();
        System.out.println("다음 해의 첫 날 : " + today.with(TemporalAdjusters.firstDayOfNextYear()));
        System.out.println("다음 달의 첫 날 : " + today.with(TemporalAdjusters.firstDayOfNextMonth()));
        System.out.println("올 해의 첫 날 : " + today.with(TemporalAdjusters.firstDayOfYear()));
        System.out.println("이번 달의 첫 날 : " + today.with(TemporalAdjusters.firstDayOfMonth()));

        System.out.println("올 해의 마지막 날 : " + today.with(TemporalAdjusters.lastDayOfYear()));
        System.out.println("이번 달의 마지막 날 : " + today.with(TemporalAdjusters.lastDayOfMonth()));

        System.out.println("today의 요일과 같은 이번 달 첫 번째 요일은 몇 일 : "
                + today.with(TemporalAdjusters.firstInMonth(today.getDayOfWeek())));
        System.out.println(
                "today의 요일과 같은 이번 달 마지막 요일은 몇 일 : " + today.with(TemporalAdjusters.lastInMonth(today.getDayOfWeek())));
        System.out.println(
                "today의 요일과 같은 지난 요일은 몇 일(당일 미포함) : " + today.with(TemporalAdjusters.previous(today.getDayOfWeek())));
        System.out.println("today의 요일과 같은 지난 요일은 몇 일(당일 포함) : "
                + today.with(TemporalAdjusters.previousOrSame(today.getDayOfWeek())));
        System.out.println(
                "today의 요일과 같은 다음 요일은 몇 일(당일 미포함) : " + today.with(TemporalAdjusters.next(today.getDayOfWeek())));
        System.out.println(
                "today의 요일과 같은 다음 요일은 몇 일(당일 포함) : " + today.with(TemporalAdjusters.nextOrSame(today.getDayOfWeek())));
        System.out.println("today의 요일과 같은 n번째 주의 요일은 몇 일 : "
                + today.with(TemporalAdjusters.dayOfWeekInMonth(3, today.getDayOfWeek())));

        LocalDate date21 = LocalDate.of(2015, 03, 18);
        LocalDate date22 = LocalDate.of(2018, 04, 20);
        LocalTime time21 = LocalTime.of(11, 30, 30);
        LocalTime time22 = LocalTime.of(12, 35, 50);

        // 날짜의 차이를 계산한다.
        Period pe = Period.between(date21, date22);
        // 시간의 차이를 계산한다. (Duration에는 시, 분 차이를 구하는 메서드가 없다. )
        Duration du = Duration.between(time21, time22);

        System.out.println(pe.getYears()); // 3
        System.out.println(pe.getMonths()); // 1
        System.out.println(pe.getDays()); // 2

        System.out.println(du.getSeconds()); // 3920
    }

}