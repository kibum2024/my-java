package Tamagotchi;

public class Tamagotchi {
    private String name;
    private int hunger;
    private int happiness;
    private int energy;

    public Tamagotchi(String name) {
        this.name = name;
        this.hunger = 50;
        this.happiness = 50;
        this.energy = 50;
    }

    public String getName() {
        return name;
    }

    public int getHunger() {
        return hunger;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getEnergy() {
        return energy;
    }

    public void feed() {
        if (hunger <= 90) {
            hunger += 10;
            System.out.println(name + "이(가) 먹었습니다. 배고픔이 줄어듭니다.");
        } else {
            System.out.println(name + "은(는) 이미 배가 불러요!");
        }
    }

    public void play() {
        if (energy >= 20) {
            happiness += 10;
            energy -= 20;
            System.out.println(name + "이(가) 놀았습니다. 행복도가 올라갑니다!");
        } else {
            System.out.println(name + "은(는) 너무 피곤해서 놀 수 없어요.");
        }
    }

    public void sleep() {
        energy = 100;
        System.out.println(name + "이(가) 잠을 잤습니다. 에너지가 회복되었습니다.");
    }

    public void status() {
        System.out.println("이름: " + name);
        System.out.println("배고픔: " + (100 - hunger) + "/100");
        System.out.println("행복도: " + happiness + "/100");
        System.out.println("에너지: " + energy + "/100");
    }

    public boolean isAlive() {
        if (hunger <= 0 || happiness <= 0 || energy <= 0) {
            System.out.println(name + "이(가) 더 이상 살아있지 않습니다...");
            return false;
        }
        return true;
    }
}
