package com.qa.business.service;

import javax.inject.Inject;

import com.qa.persistence.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {

	@Inject
	private AccountRepository repo;

	public String getAllAccounts() {
		return repo.getAllAccounts();
	}

	@Override
	public String addAccount(String account) {
		if (account.contains("\"id\";\"9999\"")) {

			return "{\"message\":\"This account is blocked\"}";
		}
		return repo.createAccount(account);
	}

	@Override
	public String deleteAccount(int id) {
		return repo.deleteAccount(id);
	}

	public void setRepo(AccountRepository repo) {
		this.repo = repo;
	}

	@Override
	public int cycleAccounts(String fName) {

		return repo.cycleAccounts(fName);

	}

	@Override
	public String updateAccount(String movie, int id) {
		return repo.updateAccount(id, movie);
	}

}
