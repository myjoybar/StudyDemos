package com.example.joybar.myaskunagjia.demo.Retrofit.demoTest;

/**
 * Created by joybar on 1/9/16.
 */
public class Model2 {

    public  int result;
    Data data = new Data();
    public Error error = new Error();
    class Data{

        int checked;
        String token;

        Drivers drivers = new Drivers();
        class Drivers{

            String contact_name;
            String contact_number;
            int id;
            String memo;
            String plate_number;

            @Override
            public String toString() {
                return "Drivers{" +
                        "contact_name='" + contact_name + '\'' +
                        ", contact_number='" + contact_number + '\'' +
                        ", id=" + id +
                        ", memo='" + memo + '\'' +
                        ", plate_number='" + plate_number + '\'' +
                        '}';
            }


        }

        @Override
        public String toString() {
            return "Data{" +
                    "checked=" + checked +
                    ", token='" + token + '\'' +
                    ", drivers=" + drivers.toString() +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Model2{" +
                "result=" + result +
                ", data=" + data.toString() +
                ", error=" + error.toString() +
                '}';
    }
}
