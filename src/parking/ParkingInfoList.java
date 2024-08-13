package parking;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingInfoList {
    private static List<ParkingInfo> parkingInfos = new ArrayList<>();

    public static void addParkingInfo(String vKind, String energyKind, int sizeKind, String carNumber, String parkingInTime) {
        ParkingInfo parkingInfo = new ParkingInfo(vKind, energyKind, sizeKind, carNumber, parkingInTime,"",0, 0);
        parkingInfos.add(parkingInfo);
        System.out.println("차량번호 : " + parkingInfo.getCarNumber() + "  입차되었습니다.");
    }

    public static void parkingOutInfo(String carNumber, String parkingOutTime) {
        int baseTime = 0;
        int baseFee = 0;
        int additionalTime = 0;
        int additionalFee = 0;
        int chargingFee = 0;
        int chargingMaxTime = 0;
        int totalFee = 0;
        int totalChargingFee = 0;
        String formattedFee = "";
        int index = 0;

        Optional<ParkingInfo> foundParkingInfo = parkingInfos.stream().filter(parkingInfo -> parkingInfo.getCarNumber().equals(carNumber))
                .findFirst();

        ParkingInfo parkingInfo = foundParkingInfo.orElseThrow(() ->
            new IllegalArgumentException("주어진 차량번호를 가진 차량이 없습니다.")
        );

        parkingInfo.setParkingOutTime(parkingOutTime);

        index = parkingInfos.indexOf(parkingInfo);
        if (index >= 0) {
            parkingInfos.set(index, parkingInfo);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        // LocalDateTime 객체로 변환
        LocalDateTime startTime = LocalDateTime.parse(parkingInfo.getParkingInTime(), formatter);
        LocalDateTime endTime = LocalDateTime.parse(parkingInfo.getParkingOutTime(), formatter);

        // 시간 차이를 계산
        Duration duration = Duration.between(startTime, endTime);
        int hours = (int) duration.toHours(); // 전체 시간을 int로 변환
        int minutes = (int) (duration.toMinutes() % 60); // 나머지 분을 int로 변환

        int minutesDifference = (int) duration.toMinutes();

        if (parkingInfo.getvKind().equals("s")) {
            baseTime = VehicleType.SEDAN.getBaseTime();
            baseFee = VehicleType.SEDAN.getBaseFee()[0];
            additionalTime = VehicleType.SEDAN.getAdditionalTime();
            additionalFee = VehicleType.SEDAN.getAdditionalFee()[0];
            chargingFee = VehicleType.SEDAN.getChargingFee();
            chargingMaxTime = VehicleType.SEDAN.getChargingMaxTime();
        } else if (parkingInfo.getvKind().equals("t")) {
            baseTime = VehicleType.TRUCK.getBaseTime();
            baseFee = VehicleType.TRUCK.getBaseFee()[parkingInfo.getSizeKind() - 1];
            additionalTime = VehicleType.TRUCK.getAdditionalTime();
            additionalFee = VehicleType.TRUCK.getAdditionalFee()[parkingInfo.getSizeKind() - 1];
            chargingFee = VehicleType.TRUCK.getChargingFee();
            chargingMaxTime = VehicleType.TRUCK.getChargingMaxTime();
        } else {
            baseTime = VehicleType.BUS.getBaseTime();
            baseFee = VehicleType.BUS.getBaseFee()[parkingInfo.getSizeKind() - 1];
            additionalTime = VehicleType.BUS.getAdditionalTime();
            additionalFee = VehicleType.BUS.getAdditionalFee()[parkingInfo.getSizeKind() - 1];
            chargingFee = VehicleType.BUS.getChargingFee();
            chargingMaxTime = VehicleType.BUS.getChargingMaxTime();
        }

        if (minutesDifference <= baseTime) {
            totalFee = baseFee;
        } else {
            totalFee = baseFee;
            minutesDifference = minutesDifference - baseTime;
            int quotient = minutesDifference / additionalTime;
            int remainder = minutesDifference % additionalTime;
            if (remainder != 0) {
                quotient += 1;
            }
            totalFee = totalFee + (additionalFee * quotient);
        }

        if (parkingInfo.getvKind().equals("s")) {
            if (minutesDifference > chargingMaxTime) {
                totalChargingFee = chargingMaxTime * chargingFee;
            } else {
                totalChargingFee = minutesDifference * chargingFee;
            }
        }

        parkingInfo.setParkingAmt(totalFee);
        index = parkingInfos.indexOf(parkingInfo);
        if (index >= 0) {
            parkingInfos.set(index, parkingInfo);
        }

        parkingInfo.setEnergyAmt(totalChargingFee);
        index = parkingInfos.indexOf(parkingInfo);
        if (index >= 0) {
            parkingInfos.set(index, parkingInfo);
        }

        System.out.println("=====================================");
        System.out.println("입차일시 : " + parkingInfo.getParkingInTime().substring(0,4) + '-' + parkingInfo.getParkingInTime().substring(4,6) + '-' + parkingInfo.getParkingInTime().substring(6,8)
                + ' ' + parkingInfo.getParkingInTime().substring(8,10) + ':' + parkingInfo.getParkingInTime().substring(10,12));
        System.out.println("출차일시 : " + parkingInfo.getParkingOutTime().substring(0,4) + '-' + parkingInfo.getParkingOutTime().substring(4,6) + '-' + parkingInfo.getParkingOutTime().substring(6,8)
                + ' ' + parkingInfo.getParkingOutTime().substring(8,10) + ':' + parkingInfo.getParkingOutTime().substring(10,12));
        System.out.println("주차시간 : " + (hours != 0? hours + "시간 " : "") + minutes + "분");
        formattedFee = String.format("%,d", totalFee);
        System.out.println("기본시간 : " + baseTime + "분, " + "기본요금 : " + baseFee + "원");
        System.out.println("추가시간 : " + additionalTime + "분, " + "추가요금 : " + additionalFee + "원");
        System.out.println("주차요금 : " + formattedFee + "원");

        if (parkingInfo.getvKind().equals("s")) {
            formattedFee = String.format("%,d", totalChargingFee);
            System.out.println("충전요금 : " + formattedFee + "원");
        }

        System.out.println("차량번호 " + parkingInfo.getCarNumber() + "  출차되었습니다.");
    }

    public static void displayParking() {

        System.out.println("=====================================");
        System.out.println("=           주차현황(" + parkingInfos.size() + "/10)           =");
        System.out.println("=====================================");
        parkingInfos.forEach(parkingInfo -> {
            System.out.println("차량번호 : " + parkingInfo.getCarNumber());
            System.out.println("차량종류 : " + (parkingInfo.getvKind().equals("s")? "승용차" : parkingInfo.getvKind().equals("t")? "트럭" : "버스"));
            System.out.println((parkingInfo.getvKind().equals("s")? "전기차구분 : " : "중량/승차인원 : ")
                    + (parkingInfo.getvKind().equals("s")? (parkingInfo.getEnergyKind().equals("1")? "전기차" : "일반차") : (parkingInfo.getSizeKind() == 1? "소형" : parkingInfo.getSizeKind() == 2? "중형" : "대형")));
            System.out.println("입차시간 : " + parkingInfo.getParkingInTime().substring(0,4) + '-' + parkingInfo.getParkingInTime().substring(4,6) + '-' + parkingInfo.getParkingInTime().substring(6,8)
                    + ' ' + parkingInfo.getParkingInTime().substring(8,10) + ':' + parkingInfo.getParkingInTime().substring(10,12));
            System.out.println("출차시간 : " + (parkingInfo.getParkingOutTime().isEmpty() ?  "주차 중" : parkingInfo.getParkingOutTime().substring(0,4) + '-' + parkingInfo.getParkingOutTime().substring(4,6) + '-' + parkingInfo.getParkingOutTime().substring(6,8)
                    + ' ' + parkingInfo.getParkingOutTime().substring(8,10) + ':' + parkingInfo.getParkingOutTime().substring(10,12)));
            System.out.println("주차요금 : " + String.format("%,d", parkingInfo.getParkingAmt()) + "원");

            if (parkingInfo.getvKind().equals("s")) {
//                formattedFee = String.format("%,d", parkingInfo.getEnergyAmt());
                System.out.println("충전요금 : " + String.format("%,d", parkingInfo.getEnergyAmt()) + "원");
            }
            System.out.println("-------------------------------------");
        });
        System.out.println("=====================================");
    }

    public static void displayParkingAmt() {
        int totalAmt1 = 0;
        int totalAmt2 = 0;

        System.out.println("=====================================");
        System.out.println("=             총 수입 현황           =");
        System.out.println("=====================================");
        for (ParkingInfo parkingInfo : parkingInfos) {
            totalAmt1 += parkingInfo.getParkingAmt();
            totalAmt2 += parkingInfo.getEnergyAmt();
        }

        System.out.println("주차요금 : " + String.format("%,d", totalAmt1) + "원");
        System.out.println("충전요금 : " + String.format("%,d", totalAmt2) + "원");
        System.out.println("=====================================");
    }

    public static int parkingCount() {
        return (int) parkingInfos.stream()
                .filter(parkingInfo -> parkingInfo.getParkingOutTime().isEmpty())
                .count();
    }
}
