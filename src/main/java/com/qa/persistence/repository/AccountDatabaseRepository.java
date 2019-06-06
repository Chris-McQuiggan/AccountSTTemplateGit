package com.qa.persistence.repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(TxType.SUPPORTS)
@Default
public class AccountDatabaseRepository implements AccountRepository {

	@Inject
	private JSONUtil util;

	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	@Override
	public String getAllAccounts() {
		TypedQuery<Account> query = null;
		query = em.createQuery("SELECT a FROM Account a", Account.class);
		List<Account> accList = query.getResultList();
		return new JSONUtil().getJSONForObject(accList);
	}

	@Transactional(TxType.REQUIRED)
	@Override
	public String createAccount(String account) {
		em.persist(account);
		return account;
	}

	@Transactional(TxType.REQUIRED)
	@Override
	public String deleteAccount(int accountNumber) {
		TypedQuery<Account> query = null;
		query = em.createQuery("DELETE FROM Account a WHERE a.accountNumber = '" + accountNumber + "'", Account.class);
		query.executeUpdate();
		return "Deleted";
	}

	@Transactional(TxType.REQUIRED)
	@Override
	public String updateAccount(int accountNumber, String account) {
		TypedQuery<Account> query = null;
		query = em.createQuery("DELETE FROM Account a WHERE a.accountNumber = '" + accountNumber + "'", Account.class);
		query.executeUpdate();
		em.persist(account);
		return "Updated";
	}

	@Override
	public int cycleAccounts(String fName) {
		Query query = em.createQuery("Select a FROM Account a");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();

		List<Account> validList = accounts.stream().filter(n -> n.getFirstName().equals(fName))
				.collect(Collectors.toList());

		return validList.size();

	}

	@Override
	public String getAccount(int id) {
		return util.getJSONForObject(em.find(Account.class, id));
	}

}
