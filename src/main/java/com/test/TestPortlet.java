package com.test;

import com.liferay.util.bridges.mvc.MVCPortlet;
import com.test.database.impl.DBException;
import com.test.database.DatabaseHandler;
import com.test.database.impl.DatabaseHandlerImpl;
import com.test.entity.Vacancy;
import com.test.hhapi.ApiHandler;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;
import java.util.List;

public class TestPortlet extends MVCPortlet {

    private List<Vacancy> vacancies;

    @Override
    public void init() throws PortletException {
        ApiHandler handler = new ApiHandler();
        vacancies = handler.getVacancies();

        DatabaseHandler dbHandler = new DatabaseHandlerImpl();

        try {
            dbHandler.saveVacancies(vacancies);
        }
        catch (DBException e) {
            e.printStackTrace();
        }
        super.init();
    }

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
        renderRequest.setAttribute("vacancies", vacancies);
        super.render(renderRequest, renderResponse);
    }
}
