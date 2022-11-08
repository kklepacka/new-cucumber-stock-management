package concombre;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.opentestfactory.exception.ParameterException;
import org.opentestfactory.util.ParameterService;

public class StepDefinitions {
    int lot1;
    int lot2;
    int total;
    String item;
    int itemValue;
    final int minExpected = 5;
    int currentStock;
    int estimatedProduction;
    int warehouseCapacity;
    double maintenanceCost;
    float rentingCost;

    @Given("I've {int} products")
    public void i_ve_number_products(int qty) {
        lot1 = qty;
    }

    @Given("I add {int} additional products")
    public void i_had_number_additional_products(int qty) {
        lot2 = qty;
    }

    @When("I count everything I have in stock")
    public void i_count_everything_i_have_in_stock() {
        total = lot1 + lot2;
    }

    @Then("I've at least {int} products in stock")
    public void i_ve_at_least_number_products_in_stock(int expected) {
        Assertions.assertTrue(total >= expected, "There is not enough stock");
    }

    @Given("I need to add some {string}")
    public void i_need_to_add_some_product(String newItem) {
        item = newItem;
    }

    @Given("I know how much I have")
    public void i_knom_how_much_i_have() {
        switch (item) {
            case "Ladder":
                itemValue = 3;
                break;
            case "Chest":
                itemValue = 2;
                break;
            case "Table":
                itemValue = -2;
                break;
            default:
                itemValue = 0;
                break;
        }
    }

    @When("I add it to the stock")
    public void i_add_it_to_the_stock() {
        total = lot1 + lot2 + itemValue;
    }

    @Then("I should have more than the minimum needed")
    public void i_should_have_more_than_the_minimum_needed() {
        Assertions.assertTrue(total >= minExpected, "Error detected, we need at least "+ minExpected +" products, right now we have only "+ total + " products");
    }

    @Given("I count my current stock")
    public void i_count_my_current_stock() throws ParameterException {
        currentStock = ParameterService.INSTANCE.getTestInt("DS_current_stock");
    }

    @When("I count next month's estimated production")
    public void i_count_next_month_s_estimated_production() throws ParameterException {
        estimatedProduction = ParameterService.INSTANCE.getTestInt("TC_CUF_estimated_production");
    }
    
    @When("I check my warehouse capacity")
    public void i_check_my_warehouse_capacity() throws ParameterException {
        warehouseCapacity = ParameterService.INSTANCE.getTestInt("DS_warehouse_capacity");
    }
    
    @Then("it should fit")
    public void it_should_fit() {
        Assertions.assertTrue(warehouseCapacity > (currentStock + estimatedProduction), "Stock will overflow, our warehouse is not big enough");
    }
    
    @When("I check the current product's value is {int}")
    public void i_check_the_current_product_s_value_is(Integer int1) throws ParameterException {
        Assertions.assertTrue(ParameterService.INSTANCE.getBoolean("CPG_CUF_product_value_is_five"));
    }
    
    @When("I check the warehouse's reference")
    public void i_check_the_warehouse_s_reference() throws ParameterException {
        Assertions.assertEquals("WH1948", ParameterService.INSTANCE.getTestString("IT_CUF_warehouse_name"), "This is not the expected warehouse");
    }
    
    @When("I check the warehouse's maintenance cost")
    public void i_check_the_warehouse_s_maintenance_cost() throws ParameterException {
        maintenanceCost = ParameterService.INSTANCE.getTestDouble("TS_CUF_maintenance_cost");
    }
    
    @When("I check the warehouse's renting cost")
    public void i_check_the_warehouse_s_renting_cost() throws ParameterException {
        rentingCost = ParameterService.INSTANCE.getTestFloat("DS_renting_cost");
    }
    
    @Then("the charges should not exceed a third of the products value")
    public void the_charges_should_not_exceed_a_third_of_the_products_value() {
        Assertions.assertTrue((((float)currentStock * 5) / 3) > (rentingCost + maintenanceCost));
    }
}
