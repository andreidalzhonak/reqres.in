package apitest.testdata;

import apitest.restassured.RegisterFailedUserModule;
import apitest.restassured.RegisterUserModule;
import apitest.restassured.UpdateUserModel;

public class PrepareRegisterUserData {

    public static RegisterUserModule getValidData() {
        return RegisterUserModule
                .builder()
                .email("eve.holt@reqres.in")
                .password("pistol")
                .build();
    }

    public static RegisterFailedUserModule getOnValidData() {
        return RegisterFailedUserModule
                .builder()
                .email("sydney@fife")
                .build();
    }

    public static RegisterUserModule getLoginData() {
        return RegisterUserModule
                .builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();
    }
    public static RegisterFailedUserModule getOnLoginData() {
        return RegisterFailedUserModule
                .builder()
                .email("peter@klaven")
                .build();
    }

    public static UpdateUserModel getNameAndeJob() {
        return UpdateUserModel
                .builder()
                .name("Andrei")
                .job("AQA")
                .build();
    }
}
