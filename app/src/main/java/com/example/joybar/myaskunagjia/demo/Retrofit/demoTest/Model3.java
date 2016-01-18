package com.example.joybar.myaskunagjia.demo.Retrofit.demoTest;

/**
 * Created by joybar on 1/9/16.
 */
public class Model3 {

    public  int result;
    Data data = new Data();
    public Error error = new Error();
    class Data{

        String company_name;
        String invite_code;
        String max_age_days;
        String membership_use_deadline;
        String user_id;
        String user_role;

        @Override
        public String toString() {
            return "Data{" +
                    "company_name='" + company_name + '\'' +
                    ", invite_code='" + invite_code + '\'' +
                    ", max_age_days='" + max_age_days + '\'' +
                    ", membership_use_deadline='" + membership_use_deadline + '\'' +
                    ", user_id='" + user_id + '\'' +
                    ", user_role='" + user_role + '\'' +
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
