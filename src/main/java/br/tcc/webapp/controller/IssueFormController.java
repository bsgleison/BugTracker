package br.tcc.webapp.controller;

import br.tcc.webapp.model.Departament;
import br.tcc.webapp.model.Issue;
import br.tcc.webapp.model.Status;
import br.tcc.webapp.service.*;
import org.apache.commons.lang.StringUtils;
import org.appfuse.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: Gleison
 * Date: 15/07/13
 * Time: 23:56
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/issueform*")
public class IssueFormController extends BaseFormController  {
    @Autowired
    private IssueManager issueManager;
    @Autowired
    private UserManager userManger;
    @Autowired
    private DepartamentManager departamentManager;
    @Autowired
    private ActivityManager activityManager;
    @Autowired
    private StatusManager statusManager;
    @Autowired
    private ProjectManager projectManager;

    public IssueFormController() {
        setCancelView("redirect:issuesByUser");
        setSuccessView("redirect:issuesByUser");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Issue showForm(HttpServletRequest request)
            throws Exception {
        String id = request.getParameter("id");


        List<Departament> dpto = departamentManager.getDepartaments();
        List<Status> status = statusManager.getStatus();

        request.setAttribute("activityList", activityManager.getActivities());
        request.setAttribute("departamentList", departamentManager.getDepartaments());
        request.setAttribute("userList", userManger.getUsers());
        request.setAttribute("statusList", statusManager.getStatus());
        request.setAttribute("projectList", projectManager.getProjects());

        if (!StringUtils.isBlank(id)) {
            return issueManager.get(new Long(id));
        }

        return new Issue();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Issue issue, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(issue, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "/issueform";
            }
        }

        // Work-around para preencher a users do projeto com as instâncias do banco
        if (issue.getAssigned() != null){
            issue.setAssigned(userManger.getUser(issue.getAssigned().getUsername()));
            issue.setReporter(userManger.getUserByUsername(request.getRemoteUser()));
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (issue.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            issueManager.removeIssue(issue.getId());
            saveMessage(request, getText("issue.deleted", locale));
        } else {
            issueManager.saveIssue(issue);
            String key = (isNew) ? "issue.added" : "issue.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:/issueform?id=" + issue.getId();
            }
        }

        return success;
    }
}