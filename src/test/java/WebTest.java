import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebTest {
    @Test
    void shouldTest() {
        open("http://localhost:9999");

        SelenideElement form = $("form");

        SelenideElement cityInput = form.find("[data-test-id=city] input");
        cityInput.setValue("Майкоп");

        SelenideElement dateInput = form.find("[data-test-id=date] input");
        LocalDate dateToInput = LocalDate.now().plusDays(5);
        dateInput.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String date = dateToInput.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        dateInput.setValue(date);

        SelenideElement nameAndSurnameInput = form
                .find("[data-test-id=name] input");
        nameAndSurnameInput.setValue("Елена Колесникова");

        SelenideElement phoneInput = form
                .find("[data-test-id=phone] input");
        phoneInput.setValue("+79062655137");

        SelenideElement agreementInput = form
                .find("[data-test-id=agreement] span");
        agreementInput.click();

        SelenideElement billButton = form.find(".button_theme_alfa-on-white");
        billButton.click();

        $("[data-test-id=notification] div.notification__content")
                .shouldBe(Condition.appear, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText(
                "Встреча успешно забронирована на " + date
        ));
    }

}