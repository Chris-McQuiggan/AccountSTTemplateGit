package com.qa.persistence.repository;

import java.util.Map;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@Alternative
public class AccountMapRepository implements AccountRepository {

	@Inject
	private Map<Integer, Account> accountMap;

	@Inject
	private JSONUtil util;

	// You must provide concrete implementation for each of these methods
	// do not change the method signature
	// THINK - if the parameter is a String
	// AND - We are required to create an account from this String
	// How can I convert to a String from an Object?
	// What utility methods do I have available?

	// You must complete this section using TDD
	// You can use the suggested tests or build your own.

	public String getAllAccounts() {

		return util.getJSONForObject(accountMap);

	}

	public String createAccount(String account) {
		Account newAccount = util.getObjectForJSON(account, Account.class);
		accountMap.put(newAccount.getId(), newAccount);
		return "Account successfuly created";
	}

	public String deleteAccount(int id) {
		accountMap.remove((Integer) id);
		return "Account successfully removed";
	}

	public String updateAccount(int id, String account) {
		Account accToUpdate = util.getObjectForJSON(account, Account.class);
		accountMap.put(id, accToUpdate);

		return "Account successfully updated";
	}

	public int cycleAccounts(String aName) {
		int count = 0;
		for (int i = 1; i <= accountMap.size(); i++) {
			if (accountMap.get(i).getFirstName().equals(aName)) {
				count++;
			}
		}

		return count;
	}

	public Map<Integer, Account> getAccountMap() {
		return accountMap;
	}

	public void setAccountMap(Map<Integer, Account> accountMap) {
		this.accountMap = accountMap;
	}

	public int numAccWFirstName(String fName) {
		int count = 0;
		for (int i = 1; i <= accountMap.size(); i++) {
			if (accountMap.get(i).getFirstName().equals(fName)) {
				count++;
			}
		}

		return count;

	}

	@Override
	public String getAccount(int id) {
		return util.getJSONForObject(accountMap.containsKey(id));
	}

}
