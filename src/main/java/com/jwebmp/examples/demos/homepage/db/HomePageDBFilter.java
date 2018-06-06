package com.jwebmp.examples.demos.homepage.db;

import bitronix.tm.BitronixTransactionManager;
import bitronix.tm.jndi.BitronixContext;
import com.google.inject.Inject;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.UnitOfWork;
import com.jwebmp.guiceinjection.GuiceContext;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.transaction.*;
import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * Just starts and finishes a user transactions with the request scoped thread.
 */
public class HomePageDBFilter
		implements Filter
{
	private UnitOfWork unitOfWork;
	private PersistService persistService;

	public HomePageDBFilter()
	{
		HomePageDBFilter dbFilter = GuiceContext.getInstance(HomePageDBFilter.class);
		persistService = dbFilter.persistService;
		unitOfWork = dbFilter.unitOfWork;
	}

	@Inject
	public HomePageDBFilter(@HomePageDB UnitOfWork unitOfWork, @HomePageDB PersistService persistService)
	{
		this.unitOfWork = unitOfWork;
		this.persistService = persistService;
	}

	@Override
	public void init(FilterConfig filterConfig)
	{
		//System.out.println("Filter init");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
	{
		unitOfWork.begin();
		try
		{
			getUserTransaction().begin();
			filterChain.doFilter(servletRequest, servletResponse);
			getUserTransaction().commit();
		}
		catch (SystemException | NamingException | NotSupportedException | RollbackException | HeuristicRollbackException | HeuristicMixedException e)
		{
			e.printStackTrace();
		}
		finally
		{
			unitOfWork.end();
		}
	}

	@Override
	public void destroy()
	{
	}

	@NotNull
	private UserTransaction getUserTransaction() throws NamingException
	{
		BitronixContext ic = new BitronixContext();
		BitronixTransactionManager btm = null;
		btm = (BitronixTransactionManager) ic.lookup("java:comp/UserTransaction");
		return btm;
	}
}
