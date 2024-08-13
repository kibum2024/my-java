package parking;

public class ParkingInfo {
    private final String vKind;
    private final String energyKind;
    private final int sizeKind;
    private final String carNumber;
    private final String parkingInTime;
    private String parkingOutTime;
    private final int parkingAmt;

    public ParkingInfo(String vKind, String energyKind, int sizeKind, String carNumber, String parkingInTime, String parkingOutTime, int parkingAmt) {
        this.vKind = vKind;
        this.energyKind = energyKind;
        this.sizeKind = sizeKind;
        this.carNumber = carNumber;
        this.parkingInTime = parkingInTime;
        this.parkingOutTime = parkingOutTime;
        this.parkingAmt = parkingAmt;
    }

//    public ParkingInfo(String vKind, String energyKind, int sizeKind, String carNumber, String parkingInTime) {
//        this.vKind = vKind;
//        this.energyKind = energyKind;
//        this.sizeKind = sizeKind;
//        this.carNumber = carNumber;
//        this.parkingInTime = parkingInTime;
//        this.parkingOutTime = "";
//        this.parkingAmt = 0;
//    }

    public String getvKind() {
        return vKind;
    }

    public String getEnergyKind() {
        return energyKind;
    }

    public int getSizeKind() {
        return sizeKind;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public String getParkingInTime() {
        return parkingInTime;
    }

    public String getParkingOutTime() {
        return parkingOutTime;
    }

    public void setParkingOutTime(String parkingOutTime) {
        this.parkingOutTime = parkingOutTime;
    }


    public int getParkingAmt() {
        return parkingAmt;
    }
}
