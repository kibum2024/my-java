package parking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingInfoList {
    private static List<ParkingInfo> parkingInfos = new ArrayList<>();

    public static void addParkingInfo(String vKind, String energyKind, int sizeKind, String carNumber, String parkingInTime) {
        ParkingInfo parkingInfo = new ParkingInfo(vKind, energyKind, sizeKind, carNumber, parkingInTime,"",0);
        parkingInfos.add(parkingInfo);
        System.out.println("차량번호 : " + parkingInfo.getCarNumber() + "  입차되었습니다.");
    }

    public static void parkingOutInfo(String carNumber, String parkingOutTime) {
        Optional<ParkingInfo> foundParkingInfo = parkingInfos.stream().filter(parkingInfo -> parkingInfo.getCarNumber().equals(carNumber))
                .findFirst();

        ParkingInfo parkingInfo = foundParkingInfo.orElseThrow(() ->
            new IllegalArgumentException("주어진 차량번호를 가진 차량이 없습니다.")
        );

        parkingInfo.setParkingOutTime(parkingOutTime);

        int index = parkingInfos.indexOf(parkingInfo);
        if (index >= 0) {
            parkingInfos.set(index, parkingInfo);
        }
        parkingInfos.set(index, parkingInfo);
        System.out.println("차량번호 : " + parkingInfo.getCarNumber() + "  출차되었습니다.");
    }

    public static void displayParking() {
        System.out.println("=====================================");
        System.out.println("=          주차현황(" + parkingInfos.size() + "/10)           =");
        System.out.println("=====================================");
        parkingInfos.forEach(parkingInfo -> {
            System.out.println("전체 : " + parkingInfo.getParkingOutTime());
            System.out.println("전기차구분 : " + parkingInfo.getEnergyKind());
            System.out.println("차량번호 : " + parkingInfo.getCarNumber());
            System.out.println("차량종류 : " + (parkingInfo.getvKind().equals("s")? "승용차" : parkingInfo.getvKind().equals("t")? "트럭" : "버스"));
            System.out.println((parkingInfo.getvKind().equals("s")? "전기차구분 : " : "중량/승차인원 : ")
                    + (parkingInfo.getvKind().equals("s")? (parkingInfo.getEnergyKind().equals("1")? "전기차" : "일반차") : parkingInfo.getSizeKind()));
            System.out.println("입차시간 : " + parkingInfo.getParkingInTime().substring(0,4) + '-' + parkingInfo.getParkingInTime().substring(4,6) + '-' + parkingInfo.getParkingInTime().substring(6,8)
                    + ' ' + parkingInfo.getParkingInTime().substring(8,10) + ':' + parkingInfo.getParkingInTime().substring(10,12));
            System.out.println("출차시간 : " + (parkingInfo.getParkingOutTime().isEmpty() ?  "주차 중" : parkingInfo.getParkingOutTime().substring(0,4) + '-' + parkingInfo.getParkingOutTime().substring(4,6) + '-' + parkingInfo.getParkingOutTime().substring(6,8)
                    + ' ' + parkingInfo.getParkingOutTime().substring(8,10) + ':' + parkingInfo.getParkingOutTime().substring(10,12)));
            System.out.println("-------------------------------------");
        });
        System.out.println("=====================================");
    }

    public static int parkingCount() {
        return (int) parkingInfos.stream()
                .filter(parkingInfo -> parkingInfo.getParkingOutTime().isEmpty())
                .count();
    }
}
