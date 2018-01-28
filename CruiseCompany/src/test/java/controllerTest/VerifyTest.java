package controllerTest;

import controller.util.RegexpParam;
import futures.Param;
import futures.Verify;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class VerifyTest {

    @Test
    public void testRegistrationParam() {
        Verify verify = new Verify();
        Param login = mock(Param.class);
        Param pass = mock(Param.class);
        Param repass = mock(Param.class);
        Param email = mock(Param.class);
        when(login.getIncorrect()).thenReturn("login incorrect");
        when(login.getRegexp()).thenReturn(RegexpParam.LOGIN);
        when(pass.getIncorrect()).thenReturn("pass incorrect");
        when(pass.getRegexp()).thenReturn(RegexpParam.PASSWORD);
        when(repass.getIncorrect()).thenReturn("repass incorrect");
        when(repass.getRegexp()).thenReturn(RegexpParam.PASSWORD);
        when(email.getIncorrect()).thenReturn("email incorrect");
        when(email.getRegexp()).thenReturn(RegexpParam.EMAIL);

        when(login.getValue()).thenReturn("vlad").thenReturn("GG&*HI*G&**&^*");
        when(pass.getValue()).thenReturn("12345*6").thenReturn("1234");
        when(repass.getValue()).thenReturn("123456").thenReturn("*^&&&*()as");
        when(email.getValue()).thenReturn("vlad.nagaev.vn@gmail.com").thenReturn("dkhsduhd");

        System.out.println(
                verify
                .validate(login)
                .validate(pass)
                .validate(repass)
                .validate(email)
                .allRight()
        );

        System.out.println(verify.getRemarks());

        System.out.println(
                verify
                .validate(login)
                .validate(pass)
                .validate(repass)
                .validate(email)
                .allRight()
        );
        System.out.println(verify.getRemarks());
    }

}
