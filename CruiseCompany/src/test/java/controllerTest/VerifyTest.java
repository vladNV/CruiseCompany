package controllerTest;

import controller.util.Regexp;
import futures.Verify;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VerifyTest {

    @Test
    public void testVerifyBuy() {
        Verify verify = new Verify();

        String card = String.valueOf(1234567890);
        String cvv = String.valueOf(123);
        String price = String.valueOf(1900000000L);

        assertEquals(true,verify
        .incorrect("incorrect.card").regexp(Regexp.NUMBER).validate(card)
        .incorrect("incorrect.cvv").regexp(Regexp.CVV).validate(cvv)
        .incorrect("incorrect.price").regexp(Regexp.PRICE).validate(price).allRight());

        card = "UIOHKIJL";
        cvv = "68g";
        price = "010000";

        assertEquals(false,verify
        .incorrect("incorrect.card").regexp(Regexp.NUMBER).validate(card)
        .incorrect("incorrect.cvv").regexp(Regexp.CVV).validate(cvv)
        .incorrect("incorrect.price").regexp(Regexp.PRICE).validate(price).allRight());
        System.out.println(verify.getRemarks());
    }

    @Test
    public void testVerifySignUp() {

    }


    @Test
    public void testVerifyConfirm() {
        Verify verify = new Verify();
        String phone = "380953268575";
        String name = "Vladyslav";
        String surname = "Nahaiev";
        String amount = "1";

        assertEquals(true, verify
        .incorrect("incorrect.phone").regexp(Regexp.PHONE).validate(phone)
        .incorrect("incorrect.name").regexp(Regexp.NAME)
        .validate(name).validate(surname)
        .incorrect("incorrect.amount").regexp(Regexp.NUMBER).validate(amount)
        .allRight());

        phone = "38095326ds75";
        name = "Vladyslav";
        surname = "Nahaiev";
        amount = "*(^&*()_";

        assertEquals(false, verify
                .incorrect("incorrect.phone").regexp(Regexp.PHONE).validate(phone)
                .incorrect("incorrect.name").regexp(Regexp.NAME)
                .validate(name).validate(surname)
                .incorrect("incorrect.amount").regexp(Regexp.NUMBER).validate(amount)
                .allRight());
        System.out.println(verify.getRemarks());

    }

}
