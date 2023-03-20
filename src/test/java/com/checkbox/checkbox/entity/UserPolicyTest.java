package com.checkbox.checkbox.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Estas pruebas cubren los métodos getters y setters de la clase UserPolicy. 
 * Se crean instancias de la clase con algunos valores predeterminados y se 
 * verifican los valores devueltos por los métodos getters. 
 * Luego se establecen algunos valores nuevos utilizando los métodos setters y se 
 * verifica que los valores devueltos por los métodos getters sean correctos.
 */
public class UserPolicyTest {

	@Test
	void testGetUserId() {
		UserPolicy userPolicy = new UserPolicy("123", "policy123", true, false, true);
		assertEquals("123", userPolicy.getUserId());
	}

	@Test
	void testGetPolicyNumber() {
		UserPolicy userPolicy = new UserPolicy("123", "policy123", true, false, true);
		assertEquals("policy123", userPolicy.getPolicyNumber());
	}

	@Test
	void testGetCheckbox1() {
		UserPolicy userPolicy = new UserPolicy("123", "policy123", true, false, true);
		assertTrue(userPolicy.isCheckbox1());
	}

	@Test
	void testGetCheckbox2() {
		UserPolicy userPolicy = new UserPolicy("123", "policy123", true, false, true);
		assertFalse(userPolicy.isCheckbox2());
	}

	@Test
	void testGetCheckbox3() {
		UserPolicy userPolicy = new UserPolicy("123", "policy123", true, false, true);
		assertTrue(userPolicy.isCheckbox3());
	}

	@Test
	void testSetUserId() {
		UserPolicy userPolicy = new UserPolicy();
		userPolicy.setUserId("123");
		assertEquals("123", userPolicy.getUserId());
	}

	@Test
	void testSetPolicyNumber() {
		UserPolicy userPolicy = new UserPolicy();
		userPolicy.setPolicyNumber("policy123");
		assertEquals("policy123", userPolicy.getPolicyNumber());
	}

	@Test
	void testSetCheckbox1() {
		UserPolicy userPolicy = new UserPolicy();
		userPolicy.setCheckbox1(true);
		assertTrue(userPolicy.isCheckbox1());
	}

	@Test
	void testSetCheckbox2() {
		UserPolicy userPolicy = new UserPolicy();
		userPolicy.setCheckbox2(false);
		assertFalse(userPolicy.isCheckbox2());
	}

	@Test
	void testSetCheckbox3() {
		UserPolicy userPolicy = new UserPolicy();
		userPolicy.setCheckbox3(true);
		assertTrue(userPolicy.isCheckbox3());
	}

}