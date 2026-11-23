package com.akshay.stockflow.auth;
import org.junit.jupiter.api.Test; import static org.junit.jupiter.api.Assertions.*;
class RoleAuthorizationTests {
@Test void employeeIsTheOnlyPublicRegistrationRole(){assertEquals(Role.EMPLOYEE,Role.valueOf("EMPLOYEE"));}
@Test void roleCatalogContainsLeastPrivilegeRoles(){assertEquals(5,Role.values().length);assertNotEquals(Role.ADMIN,Role.EMPLOYEE);}
}
