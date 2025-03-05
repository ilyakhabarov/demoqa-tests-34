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
        // правки по пункту 1:изменил селектор, изменил способ прокрутки
        $("#hobbiesWrapper").$(byText(checkbox1)).scrollTo().click();
        $("#hobbiesWrapper").$(byText(checkbox3)).click();
        $("#uploadPicture").uploadFromClasspath(uploadImage);
        $("#currentAddress").setValue(currentAddress);
        $("#state").click();
        $(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();

        // Подтверждаем регистрацию
        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        // Проверяем данные в окне подтверждения регистрации
        $x("/html/body/div[4]/div/div/div[2]/div/table/thead/tr/th[1]").shouldHave(text("Label"));
        $x("/html/body/div[4]/div/div/div[2]/div/table/thead/tr/th[2]").shouldHave(text("Values"));

        $(".table-responsive").shouldHave(text("Student Name\t"));
        $(".table-responsive").shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").shouldHave(text("Student Email\t"));
        $(".table-responsive").shouldHave(text(userEmail));
        $(".table-responsive").shouldHave(text("Gender\t"));
        $(".table-responsive").shouldHave(text(gender));
        $(".table-responsive").shouldHave(text("Mobile\t"));
        $(".table-responsive").shouldHave(text(userNumber));
        $(".table-responsive").shouldHave(text("Date of Birth\t"));
        $(".table-responsive").shouldHave(text(day + " " + month + "," + year));
        $(".table-responsive").shouldHave(text("Subjects\t"));
        $(".table-responsive").shouldHave(text(subject));
        $(".table-responsive").shouldHave(text("Hobbies\t"));
        $(".table-responsive").shouldHave(text(checkbox1 + ", " + checkbox3));
        $(".table-responsive").shouldHave(text("Picture\t"));
        $(".table-responsive").shouldHave(text(uploadImage));
        $(".table-responsive").shouldHave(text("Address\t"));
        $(".table-responsive").shouldHave(text(currentAddress));
        $(".table-responsive").scrollTo().shouldHave(text("State and City\t"));
        $(".table-responsive").shouldHave(text(state + " " + city));
    }
}
