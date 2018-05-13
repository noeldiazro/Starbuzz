package es.montanus.starbuzz.tests;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import es.montanus.starbuzz.Drink;
import es.montanus.starbuzz.R;

public class DrinkTest {

    private static final String NAME = "The name of the drink";
    private static final String DESCRIPTION = "A description of the drink";
    private static final int IMAGE_RESOURCE_ID = R.drawable.ic_launcher_background;
    private Drink drink;

    @Before
    public void create() {
        drink = new Drink(NAME, DESCRIPTION, IMAGE_RESOURCE_ID);
    }

    @Test
    public void getsCorrectName() {
        Assert.assertEquals(NAME, drink.getName());
    }

    @Test
    public void getsCorrectDescription() {
        Assert.assertEquals(DESCRIPTION, drink.getDescription());
    }

    @Test
    public void getsCorrectImageResourceId() {
        Assert.assertEquals(IMAGE_RESOURCE_ID, drink.getImageResourceId());
    }

    @Test
    public void toStringReturnsName() {
        Assert.assertEquals(NAME, drink.toString());
    }
}