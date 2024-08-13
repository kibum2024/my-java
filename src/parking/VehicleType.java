package parking;

public enum VehicleType {
    SEDAN("s", 30, 2000, 10, 1000, 40, 300),
    TRUCK("t", 60, new int[] {3000, 4000, 5000}, 30, new int[] {1000, 2000, 3000}, 40, 300),
    BUS("b", 60, new int[] {2000, 3000, 4000}, 60, new int[] {2000, 3000, 4000}, 40, 300);

    private final String vKind;
    private final int baseTime;
    private final int[] baseFee;
    private final int additionalTime;
    private final int[] additionalFee;
    private final int chargingFee;
    private final int chargingMaxTime;

    //트럭, 버스
    VehicleType(String vKind, int baseTime, int[] baseFee, int additionalTime, int[] additionalFee, int chargingFee, int chargingMaxTime) {
        this.vKind = vKind;
        this.baseTime = baseTime;
        this.baseFee = baseFee;
        this.additionalTime = additionalTime;
        this.additionalFee = additionalFee;
        this.chargingFee = chargingFee;
        this.chargingMaxTime = chargingMaxTime;
    }

    //승용차
    VehicleType(String vKind, int baseTime, int baseFee, int additionalTime, int additionalFee, int chargingFee, int chargingMaxTime) {
        this.vKind = vKind;
        this.baseTime = baseTime;
        this.baseFee = new int[]{baseFee};
        this.additionalTime = additionalTime;
        this.additionalFee = new int[]{additionalFee};
        this.chargingFee = chargingFee;
        this.chargingMaxTime = chargingMaxTime;
    }

    // 각 속성의 getter 메서드
    public String getVKind() {
        return vKind;
    }

    public int getBaseTime() {
        return baseTime;
    }

    public int[] getBaseFee() {
        return baseFee;
    }

    public int getAdditionalTime() {
        return additionalTime;
    }

    public int[] getAdditionalFee() {
        return additionalFee;
    }

    public int getChargingFee() {
        return chargingFee;
    }

    public int getChargingMaxTime() {
        return chargingMaxTime;
    }

}
