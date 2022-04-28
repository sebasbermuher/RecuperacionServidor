package org.iesalixar.servidor.utils;

public class Main {

	public Main() {
		String test_passwd = "abcdefghijklmnopqrstuvwxyz";
		String test_hash = "$2a$06$.rCVZVOThsIa97pEDOxvGuRRgzG64bvtJ0938xuqzv18d3ZpQhstC";

		System.out.println("Testing BCrypt Password hashing and verification");
		System.out.println("Test password: " + test_passwd);
		System.out.println("Test stored hash: " + test_hash);
		System.out.println("Hashing test password...");
		System.out.println();

		String computed_hash = PasswordHashGenerator.hashPassword(test_passwd);
		System.out.println("Test computed hash: " + computed_hash);
		System.out.println();
		System.out.println("Verifying that hash and stored hash both match for the test password...");
		System.out.println();

		String compare_test = PasswordHashGenerator.checkPassword(test_passwd, test_hash)
			? "Passwords Match" : "Passwords do not match";
		String compare_computed = PasswordHashGenerator.checkPassword(test_passwd, computed_hash)
			? "Passwords Match" : "Passwords do not match";

		System.out.println("Verify against stored hash:   " + compare_test);
		System.out.println("Verify against computed hash: " + compare_computed);
	}

}
