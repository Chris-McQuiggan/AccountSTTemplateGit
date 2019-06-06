package com.qa.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.business.service.AccountService;

@Path("/Account")
public class AccountEndpoint {

	@Inject
	private AccountService service;

	@Path("/getAllAccounts")
	@GET
	@Produces({ "application/json" })
	public String getAllAccounts() {
		return service.getAllAccounts();
	}

	@Path("/cycleAccounts/{accountNumber}")
	@GET
	@Produces({ "application/json" })
	public int cycleMovies(@PathParam("accountNumber") String accountNumber) {
		return service.cycleAccounts(accountNumber);
	}

	@Path("/getAccount/{id}")
	@GET
	@Produces({ "application/json" })
	public String getAccount(@PathParam("id") int id) {
		return service.getAccount(id);
	}

	@Path("/createAccount")
	@POST
	@Produces({ "application/json" })
	public String addAccount(String account) {
		return service.addAccount(account);
	}

	@Path("/deleteAccount/{id}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteAccount(@PathParam("id") int id) {
		return service.deleteAccount(id);
	}

	@Path("/updateAccount/{id}")
	@PUT
	@Produces({ "application/json" })
	public String updateAccount(String account, @PathParam("id") int id) {
		return service.updateAccount(account, id);
	}

	public void setService(AccountService service) {
		this.service = service;
	}

}
