package apitest.restassured;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class RegisterUserModule {

        private String email;
        private String password;
}
