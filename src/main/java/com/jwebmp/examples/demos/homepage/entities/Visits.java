package com.jwebmp.examples.demos.homepage.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwebmp.Page;
import com.jwebmp.SessionHelper;
import com.jwebmp.entityassist.CoreEntity;
import com.jwebmp.examples.demos.homepage.entities.builders.VisitsBuilder;
import com.jwebmp.examples.demos.homepage.entities.persistasync.VisitsPersistAsync;
import com.jwebmp.guicedinjection.GuiceContext;
import org.json.JSONObject;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.concurrent.Executors;

@Table(name = "Visits")
@Entity(name = "Visits")
public class Visits
		extends CoreEntity<Visits, VisitsBuilder, Long>
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VisitsID")
	private Long visitsID;
	@JoinColumn(name = "VisitorID",
			referencedColumnName = "VisitorID",
			columnDefinition = "bigint")
	@ManyToOne(fetch = FetchType.EAGER)
	private Visitors visitorID;
	@Column(name = "VisitDate",
			columnDefinition = "datetime")
	private LocalDateTime visitDate;
	@Column(name = "BrowserData",
			columnDefinition = "varchar(max)")
	private String browserData;
	@Column(name = "VisitURL",
			columnDefinition = "varchar(max)")
	private String visitUrl;
	@Column(name = "HeaderData",
			columnDefinition = "varchar(max)")
	private String headerData;

	public static Visits create(Visitors visitors, Page page) throws JsonProcessingException
	{
		Visits visits = new Visits();
		visits.setVisitDate(LocalDateTime.now());
		visits.setBrowserData(GuiceContext.getInstance(ObjectMapper.class)
		                                  .writeValueAsString(page.getBrowser()));
		HttpServletRequest req = GuiceContext.getInstance(HttpServletRequest.class);
		visits.setVisitUrl(SessionHelper.getServletUrl());
		visits.setVisitorID(visitors);
		StringBuilder sb = new StringBuilder();
		Enumeration<String> headerNames = req.getHeaderNames();
		while (headerNames.hasMoreElements())
		{
			String h = headerNames.nextElement();
			String v = req.getHeader(h);
			//sb.append(h + "=" + v + STRING_SEMICOLON);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(h, v);
			sb.append(jsonObject.toString());
		}
		visits.setHeaderData(sb.toString());
		visits.builder()
		      .setRunDetached(true)
		      .persist(visits);
		return visits;
	}

	@Override
	public Long getId()
	{
		return visitsID;
	}

	@Override
	public Visits setId(Long id)
	{
		setVisitsID(id);
		return this;
	}

	/**
	 * Executes the commits in a separate thread
	 *
	 * @return
	 */
	@Override
	@SuppressWarnings("unsused")
	public Visits persist()
	{
		Executors.defaultThreadFactory()
		         .newThread(new VisitsPersistAsync(this));
		return this;
	}

	public Long getVisitsID()
	{
		return visitsID;
	}

	public void setVisitsID(Long visitsID)
	{
		this.visitsID = visitsID;
	}

	public Visitors getVisitorID()
	{
		return visitorID;
	}

	public void setVisitorID(Visitors visitorID)
	{
		this.visitorID = visitorID;
	}

	public LocalDateTime getVisitDate()
	{
		return visitDate;
	}

	public void setVisitDate(LocalDateTime visitDate)
	{
		this.visitDate = visitDate;
	}

	public String getBrowserData()
	{
		return browserData;
	}

	public void setBrowserData(String browserData)
	{
		this.browserData = browserData;
	}

	public String getVisitUrl()
	{
		return visitUrl;
	}

	public void setVisitUrl(String visitUrl)
	{
		this.visitUrl = visitUrl;
	}

	public String getHeaderData()
	{
		return headerData;
	}

	public void setHeaderData(String headerData)
	{
		this.headerData = headerData;
	}
}
