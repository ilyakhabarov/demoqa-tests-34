package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {

    String firstName = "Ilya";
    String lastName = "Khabarov";
    String userEmail = "test@test.com";
    String gender = "Male";
    String userNumber = "1234567890";
    String year = "1994";
    String month = "May";
    String day = "06";
    String subject = "Computer Science";
    String checkbox1 = "Sports";
    String checkbox3 = "Music";
    String uploadImage = "qa_guru_logo.png";
    String currentAddress = "Moscow, Russia";
    String state = "Rajasthan";
    String city = "Jaipur";


    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1280x720";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
//        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormTest() {

        // Открываем форму
        open("/automation-practice-form");
        $(".text-center").shouldHave(text("Practice Form"));

        // Заполняем все поля в форме
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#gender-radio-1").parent().$(byText(gender)).click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__day--006").click();
        $("#subjectsInput").setValue(subject).pressEnter();
        $("#hobbiesWrapper").$(byText(checkbox1)).scrollTo().click();   // поправил селектор по пункту 1
        $("#hobbiesWrapper").$(byText(checkbox3)).click();
        $("#uploadPicture").uploadFromClasspath(uploadImage);
        $("#currentAddress").setValue(currentAddress);
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();   // поправил селектор по пункту 1
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();    // поправил селектор по пункту 1

        // Подтверждаем регистрацию
        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        // Проверяем данные в окне подтверждения регистрации
        $x("/html/body/div[4]/div/div/div[2]/div/table/thead/tr/th[1]").shouldHave(text("Label"));
        $x("/html/body/div[4]/div/div/div[2]/div/table/thead/tr/th[2]").shouldHave(text("Values"));

        // Изменил способ проверки значений итоговой таблицы
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(userEmail));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(gender));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(userNumber));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text(day + " " + month + "," + year));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text(subject));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text(checkbox1 + ", " + checkbox3));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text(uploadImage));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text(currentAddress));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text(state + " " + city));
    }
}
