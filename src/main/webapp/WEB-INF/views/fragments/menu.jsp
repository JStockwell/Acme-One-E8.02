
<%--
- menu.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.system-configuration.show" action="/administrator/system-configuration/show"/>
			<acme:menu-suboption code="master.menu.administrator.shut-down" action="/administrator/shut-down"/>
			<acme:menu-suboption code="master.menu.dashboards.admin" action="/administrator/admin-dashboard/show"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.any">
      		<acme:menu-suboption code="master.menu.lists.announcement" action="/any/announcement/list"/>
			<acme:menu-suboption code="master.menu.lists.chirp" action="/any/chirp/list"/>
			<acme:menu-suboption code="master.menu.lists.item" action="/any/item/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.user-account.list" action="/any/user-account/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.lists.system-configuration" action="/authenticated/system-configuration/show"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.inventor" access="hasRole('Inventor')">
			<acme:menu-suboption code="master.menu.inventor.myitems" action="/inventor/item/list"/>
			<acme:menu-suboption code="master.menu.lists.patronage" action="/inventor/patronage/list"/>
			<acme:menu-suboption code="master.menu.lists.patronageReport" action="/inventor/patronage-report/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.patron" access="hasRole('Patron')">
			<acme:menu-suboption code="master.menu.lists.patronage" action="/patron/patronage/list"/>
			<acme:menu-suboption code="master.menu.dashboards.patron" action="/patron/patron-dashboard/show" access="hasRole('Patron')"/>
			<acme:menu-suboption code="master.menu.lists.patronageReport" action="/patron/patronage-report/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.ferclavar-favourite-link" action="http://www.example.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.greortsol-favourite-link" action="https://www.yamaha-motor.eu/es/es/products/motocicletas/hyper-naked/mt-07-2022/techspecs//"/>
			<acme:menu-suboption code="master.menu.anonymous.pabsanval1-favourite-link" action="https://www.youtube.com/watch?v=dQw4w9WgXcQ"/>
			<acme:menu-suboption code="master.menu.anonymous.mancabmor1-favourite-link" action="https://www.youtube.com/watch?v=-3JbbFL-aks"/>
			<acme:menu-suboption code="master.menu.anonymous.alegestor-favourite-link" action="https://twitter.com/juanminismo/status/1464982823874486274"/>
			<acme:menu-suboption code="master.menu.anonymous.jaistomen-favourite-link" action="http://www.gendesign.co.jp/E_index.html"/>
		</acme:menu-option>		
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="hasRole('Authenticated')">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>
