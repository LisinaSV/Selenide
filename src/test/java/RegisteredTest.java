import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;



public class RegisteredTest {
    public String generateData (int day,String pattern){
        return LocalDate.now().plusDays(day).format(DateTimeFormatter.ofPattern(pattern));

    }
    @Test
    void shouldRegisteredAccount() {
        String planningDate = generateData(4, "dd.MM.yyyy");
        Selenide.open("http://localhost:9999/");//открыть страничку
//        $$(".tab-item").find(Condition.text("По номеру счета")).click();//Поиск коллекции по номеру счета
        SelenideElement form = $("form");//вся форма, внутри формы можно искать вложенные в него элементы
        form.$("[placeholder='Город']").setValue("Москва");
        form.$("[data-test-id='date']").click();
        form.$("[name='name']").setValue("Лисина Светлана");
        form.$("[name='phone']").setValue("+79260000000");
        form.$("[data-test-id='agreement']").click();
        form.$$("button").find(Condition.text("Забронировать")).click();
        $(Selectors.withText("Успешно")).should(Condition.visible, Duration.ofSeconds(15));
    }

}
