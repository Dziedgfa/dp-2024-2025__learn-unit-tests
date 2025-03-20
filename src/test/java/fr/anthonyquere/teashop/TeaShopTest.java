package fr.anthonyquere.teashop;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

class TeaShopTest {

    @Test
    void should_create_tea_with_correct_properties() {
        var tea = new Tea("Green Tea", 180, 80, true);

        assertThat(tea.getName()).isEqualTo("Green Tea");
        assertThat(tea.getSteepingTimeSeconds()).isEqualTo(180);
        assertThat(tea.getIdealTemperatureCelsius()).isEqualTo(80);
        assertThat(tea.isLoose()).isTrue();
    }

    @Test
    void should_create_empty_tea_cup() {
        var cup = new TeaCup();

        assertThat(cup.isReadyToDrink()).isFalse();
    }

 /*   @Test
    void should_throw_exception_when_adding_tea_to_empty_cup() {
        var cup = new TeaCup();
        var tea = new Tea("Black Tea", 240, 90, false);

        Exception exception = assertThrows(IllegalStateException.class, () -> cup.addTea(tea));
        assertThat(exception.getMessage()).isEqualTo("Cannot add tea to an empty cup!");
    }*/

    @Test
    void should_add_water_to_cup() {
        var cup = new TeaCup();
        cup.addWater(85);

        assertThat(cup.isReadyToDrink()).isFalse();
    }

    @Test
    void should_prepare_tea_in_tea_shop() {
        var teaShop = new TeaShop(90);
        var tea = new Tea("oolong", 200, 85, true);
        teaShop.addTea(tea);

        var cup = teaShop.prepareTea("oolong");

        assertThat(cup).isNotNull();
        assertThat(cup.isReadyToDrink()).isFalse();
    }

    @Test
    void should_throw_exception_when_preparing_nonexistent_tea() {
        var teaShop = new TeaShop(90);

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> teaShop.prepareTea("nonexistent tea"));
        assertThat(exception.getMessage()).isEqualTo("Tea not available: nonexistent tea");
    }

    @Test
    void should_set_valid_water_temperature_in_tea_shop() {
        var teaShop = new TeaShop(90);
        teaShop.setWaterTemperature(75);

        assertThatCode(() -> teaShop.setWaterTemperature(60)).doesNotThrowAnyException();
    }

    @Test
    void should_throw_exception_when_setting_invalid_water_temperature() {
        var teaShop = new TeaShop(90);

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> teaShop.setWaterTemperature(-5));
        assertThat(exception.getMessage()).isEqualTo("Water temperature must be between 0 and 100°C");

        exception = assertThrows(IllegalArgumentException.class,
                () -> teaShop.setWaterTemperature(105));
        assertThat(exception.getMessage()).isEqualTo("Water temperature must be between 0 and 100°C");
    }

    @Test
    void should_not_be_ready_to_drink_when_adding_tea_to_cup_with_water() throws InterruptedException {
        var cup = new TeaCup();
        cup.addWater(85);
        var tea = new Tea("Green Tea", 180, 85, true);
        cup.addTea(tea);

        Thread.sleep(1000);

        assertThat(cup.isReadyToDrink()).isFalse();
    }

    @Test
    void should_be_ready_to_drink_when_steeping_time_passes() throws InterruptedException {
        var cup = new TeaCup();
        cup.addWater(85);
        var tea = new Tea("Green Tea", 2, 85, true);
        cup.addTea(tea);

        Thread.sleep(3000);

        assertThat(cup.isReadyToDrink()).isTrue();
    }
}
