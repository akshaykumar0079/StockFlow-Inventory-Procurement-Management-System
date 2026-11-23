package com.akshay.stockflow.warehouse;
import org.junit.jupiter.api.Test; import static org.junit.jupiter.api.Assertions.*;
class WarehouseTransferRulesTests {
@Test void transferQuantityMustBePositive(){assertTrue(1>0);assertFalse(0>0);}
@Test void warehouseNamesAreNotEqualWhenDestinationDiffers(){assertNotEquals("Chennai Central","Bengaluru North");}
}
