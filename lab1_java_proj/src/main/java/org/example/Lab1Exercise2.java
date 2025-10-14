package org.example;
//Builder method design pattern exercise
public class Lab1Exercise2 {
    //final car object can`t change, and I don`t need to have a constructor with 100 parameters
    public static void main(String[] args) {
        Car fordTrend = new Car.Builder("Ford", 2009, 87, "diesel", "XYZ").build();
        Car fordTitanium = new Car.Builder("Ford", 2018, 125, "diesel", "WWW")
                .sound(Sound.MP3)
                .navigation(Navigation.Small)
                .build();
        Car fordEco = new Car.Builder("Ford", 2019, 100, "gas", "YHD")
                .air(Air.AUTO)
                .build();

        System.out.println(fordTrend);
        System.out.println(fordTitanium);
        System.out.println(fordEco);
    }

    // Optional feature enums
    enum Sound { RadioCD, MP3 }
    enum Navigation { None, Small, Full }
    enum Air { MANUAL, AUTO }

    // Immutable Car class with Builder
    static class Car {
        // Required fields
        private final String brand;
        private final int year;
        private final int enginePower;
        private final String fuelType;
        private final String chassisNumber;

        // Optional fields
        private final Sound sound;
        private final Navigation navigation;
        private final Air air;

        private Car(Builder builder) {
            this.brand = builder.brand;
            this.year = builder.year;
            this.enginePower = builder.enginePower;
            this.fuelType = builder.fuelType;
            this.chassisNumber = builder.chassisNumber;
            this.sound = builder.sound;
            this.navigation = builder.navigation;
            this.air = builder.air;
        }

        public static class Builder {
            // Required fields
            private final String brand;
            private final int year;
            private final int enginePower;
            private final String fuelType;
            private final String chassisNumber;

            // Optional fields with defaults
            private Sound sound = Sound.RadioCD;
            private Navigation navigation = Navigation.None;
            private Air air = Air.MANUAL;

            public Builder(String brand, int year, int enginePower, String fuelType, String chassisNumber) {
                this.brand = brand;
                this.year = year;
                this.enginePower = enginePower;
                this.fuelType = fuelType;
                this.chassisNumber = chassisNumber;
            }

            public Builder sound(Sound sound) { this.sound = sound; return this; }
            public Builder navigation(Navigation navigation) { this.navigation = navigation; return this; }
            public Builder air(Air air) { this.air = air; return this; }

            public Car build() { return new Car(this); }
        }

        @Override
        public String toString() {
            return brand + " " + year + " " + enginePower + " " + fuelType + " " + chassisNumber
                    + " [Sound: " + sound + ", Nav: " + navigation + ", Air: " + air + "]";
        }
    }
}

