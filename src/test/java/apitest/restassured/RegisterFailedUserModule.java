package apitest.restassured;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterFailedUserModule {

    private String email;
}
