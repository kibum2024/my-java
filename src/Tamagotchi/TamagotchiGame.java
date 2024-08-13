package Tamagotchi;

import java.util.Scanner;

public class TamagotchiGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("다마고치의 이름을 입력하세요:");
        String name = scanner.nextLine();

        Tamagotchi tamagotchi = new Tamagotchi(name);

        while (tamagotchi.isAlive()) {
            System.out.println("\n무엇을 하시겠습니까?");
            System.out.println("1. 먹이주기");
            System.out.println("2. 놀아주기");
            System.out.println("3. 재우기");
            System.out.println("4. 상태 확인");
            System.out.println("5. 종료");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    tamagotchi.feed();
                    break;
                case 2:
                    tamagotchi.play();
                    break;
                case 3:
                    tamagotchi.sleep();
                    break;
                case 4:
                    tamagotchi.status();
                    break;
                case 5:
                    System.out.println("게임을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
            }

            // 게임 진행에 따라 상태 변화
            tamagotchi.status();
        }

        scanner.close();
    }
}
