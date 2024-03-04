package org.max.home;

import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@Epic(value = "Тестирование API https://spoonacular.com/food-api")
@Feature(value = "Домашнее задание")
public class ClassifyCuisineTest extends AbstractTest {

    @Test
    @DisplayName("ClassifyCuisineTest")
    @Description("POST IngredientSubstitutes")
    @Link("")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Кравченко Максим")
    @Story(value = "Тестирование метода IngredientSubstitutes")
    void postClassifyCuisine_whenValid_shouldReturn() {
        ClassifyCuisineDTO response = given()
                .queryParam("apiKey", getApiKey())
                .when()
                .formParam("title","Pork roast with green beans")
                .formParam("ingredientList","3 oz pork shoulder")
                .formParam("language","en")
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(1000l))
                .extract()
                .response()
                .body()
                .as(ClassifyCuisineDTO.class);
        assertThat(response.getCuisine(), containsString("Mediterranean"));
        Assertions.assertEquals(response.getConfidence(),0.0);
        Assertions.assertEquals(response.getCuisines().size(),3);
    }
}
