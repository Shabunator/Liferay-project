<%@include file="/init.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="theme" %>

<portlet:defineObjects/>
<%--<theme:defineObjects/>--%>

<h1>Вакансии по запросу: <i>г.Новосибирск, «Информационные технологии, интернет, телеком»</i></h1>

<jsp:useBean id="vacancies" class="java.util.ArrayList" scope="request"/>

    <liferay-ui:search-container total="<%=vacancies.size()%>">
        <liferay-ui:search-container-results results="<%= vacancies %>"/>
        <liferay-ui:search-container-row className="com.test.entity.Vacancy" modelVar="entry">
            <liferay-ui:search-container-column-text property="name" name="Вакансия"/>
            <liferay-ui:search-container-column-text property="published" name="Дата"/>
            <liferay-ui:search-container-column-text property="employer" name="Организация"/>
            <liferay-ui:search-container-column-text property="salary" name="Зарплата"/>
        </liferay-ui:search-container-row>
        <liferay-ui:search-iterator/>
    </liferay-ui:search-container>
