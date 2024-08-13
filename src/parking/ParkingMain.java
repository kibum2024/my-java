package parking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ParkingMain {
    static Scanner scanner = new Scanner(System.in);
    static String vehicleKind;
    static String energyKind;
    static int sizeKind;
    static int busMaxCount;
    static String carNumber;
    static String packingInTime;
    static String packingOutTime;

    public static void main(String[] args) {

        while (true) {
            System.out.println("=====================================");
            System.out.println("= 현재 주차장의 잔여 좌석 : (" + ParkingInfoList.parkingCount() + "/10)  =");
            System.out.println("=====================================");
            System.out.println("원하는 작업을 선택하세요.");
            System.out.println("1. 입차");
            System.out.println("2. 출차");
            System.out.println("3. 주차차량 확인");
            System.out.println("4. 총 수입 확인");
            System.out.println("5. 종료");
            System.out.print("선택 : ");

            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("숫자로 입력해 주세요.");
            } finally {
                scanner.nextLine();
            }

            switch (choice) {
                case 1:
                    parkingIn();
                    break;
                case 2:
                    parkingOut();
                    break;
                case 3:
                    ParkingInfoList.displayParking();
                    break;
                case 4:
                    System.out.println("차량종류를 입력하세요.(승용차:s, 트럭:t, 버스:b)");
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("(1 ~ 5) 값으로 다시 입력하세요.");
            }
        }
    }

    public static void parkingIn() {
        boolean vehicleChk = true;
        while (vehicleChk) {
            System.out.println("차량종류를 입력하세요.(승용차:s, 트럭:t, 버스:b)");
            vehicleKind = scanner.nextLine();
            switch (vehicleKind) {
                case "s":
                    System.out.println("전기차 구분을 입력하세요.(전기차:1, 일반차:2)");
                    energyKind = scanner.nextLine();
                    parkingInfoAppend();
                    vehicleChk = false;
                    break;
                case "t":
                    System.out.println("중량을 입력하세요.(단위:톤)");
                    int truckKg = scanner.nextInt();
                    scanner.nextLine();
                    sizeKind = 0;
                    if (truckKg < 5) {
                        sizeKind = 1;
                    } else if (truckKg >= 5 && truckKg <= 10) {
                        sizeKind = 2;
                    } else {
                        sizeKind = 3;
                    }
                    parkingInfoAppend();
                    vehicleChk = false;
                    break;
                case "b":
                    System.out.println("최대승차인원을 입력하세요.");
                    busMaxCount = scanner.nextInt();
                    scanner.nextLine();
                    sizeKind = 0;
                    if (busMaxCount < 24) {
                        sizeKind = 1;
                    } else if (busMaxCount >= 24 && busMaxCount <= 40) {
                        sizeKind = 2;
                    } else {
                        sizeKind = 3;
                    }
                    parkingInfoAppend();
                    vehicleChk = false;
                    break;
                default:
                    System.out.println("차량종류를 잘 못 입력하였습니다.");
            }
        }
    }

    public static void parkingOut() {
        System.out.println("출차 차량번호를 입력하세요.");
        carNumber = scanner.nextLine();
        System.out.println("출차일시를 입력하세요.(현재일시는:1 입력하세요.)");
        String checkTime = scanner.nextLine();
        packingOutTime = "";
        if (checkTime.trim().equals("1")) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
            packingOutTime = now.format(formatter);
        } else {
            packingOutTime = checkTime;
        }
        System.out.println("출차일시 : " + packingOutTime.substring(0,4) + '-' + packingOutTime.substring(4,6) + '-' + packingOutTime.substring(6,8)
                + ' ' + packingOutTime.substring(8,10) + ':' + packingOutTime.substring(10,12));
        ParkingInfoList.parkingOutInfo(carNumber, packingOutTime);
    }

    public static void parkingInfoAppend() {
        System.out.println("차량번호를 입력하세요.");
        carNumber = scanner.nextLine();
        System.out.println("입차일시를 입력하세요.(현재일시는:1 입력하세요.)");
        String checkTime = scanner.nextLine();
        packingInTime = "";
        if (checkTime.trim().equals("1")) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
            packingInTime = now.format(formatter);
        } else {
            packingInTime = checkTime;
        }
        System.out.println("입차일시 : " + packingInTime.substring(0,4) + '-' + packingInTime.substring(4,6) + '-' + packingInTime.substring(6,8)
                + ' ' + packingInTime.substring(8,10) + ':' + packingInTime.substring(10,12));
        ParkingInfoList.addParkingInfo(vehicleKind, energyKind, sizeKind, carNumber, packingInTime);
    }
}

