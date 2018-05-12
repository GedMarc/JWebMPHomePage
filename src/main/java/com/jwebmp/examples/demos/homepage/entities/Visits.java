package com.jwebmp.examples.demos.homepage.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwebmp.Page;
import com.jwebmp.examples.demos.homepage.entities.builders.VisitsBuilder;
import com.jwebmp.examples.demos.homepage.entities.persistasync.VisitsPersistAsync;
import lombok.Data;
import com.jwebmp.SessionHelper;
import za.co.mmagon.entityassist.CoreEntity;
import za.co.mmagon.guiceinjection.GuiceContext;
import com.jwebmp.Page;
import com.jwebmp.examples.demos.homepage.entities.builders.VisitsBuilder;
import com.jwebmp.examples.demos.homepage.entities.persistasync.VisitsPersistAsync;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.concurrent.Executors;

@Table(name = "Visits")
@Entity(name = "Visits")
@Data

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
			sb.append(h + "=" + v + STRING_SEMICOLON);
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

	@Override
	public int hashCode()
	{
		return super.hashCode();
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Visits))
		{
			return false;
		}
		if (!super.equals(o))
		{
			return false;
		}

		Visits visits = (Visits) o;

		return getVisitsID() != null ? getVisitsID().equals(visits.getVisitsID()) : visits.getVisitsID() == null;
	}
}
