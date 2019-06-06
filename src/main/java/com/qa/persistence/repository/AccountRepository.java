package com.qa.persistence.repository;

public interface AccountRepository {

	String getAllAccounts();

	String createAccount(String account);

	String deleteAccount(int id);

	String updateAccount(int id, String account);

	int cycleAccounts(String fName);

	String getAccount(int id);

}
