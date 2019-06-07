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
		return util.getJSONForObject(accList);
	}

	@Transactional(TxType.REQUIRED)
	@Override
	public String createAccount(String account) {
		Account acc1 = util.getObjectForJSON(account, Account.class);
		em.persist(acc1);

		return "Success:---  " + account;
	}

	@Transactional(TxType.REQUIRED)
	@Override
	public String deleteAccount(int id) {
		Account acc1 = em.getReference(Account.class, id);
		em.remove(acc1);
		return "Deleted";
	}

	@Transactional(TxType.REQUIRED)
	@Override
	public String updateAccount(int id, String account) {
		Account acc1 = util.getObjectForJSON(account, Account.class);
		Account accountToUpdate = em.getReference(Account.class, id);
		accountToUpdate.setAccountNumber(acc1.getAccountNumber());
		accountToUpdate.setFirstName(acc1.getFirstName());
		accountToUpdate.setLastName(acc1.getLastName());
		em.persist(accountToUpdate);
		return "Updated:---   " + account;
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
