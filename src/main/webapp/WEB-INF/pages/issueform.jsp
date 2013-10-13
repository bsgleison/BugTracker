<%--
  Created by IntelliJ IDEA.
  User: Gleison
  Date: 03/08/13
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>

<%@ include file="/common/taglibs.jsp" %>
<head>
    <title><fmt:message key="issueDetail.title"/></title>
    <meta name="menu" content="IssueMenu"/>
    <link rel="stylesheet" type="text/css" media="all" link
          href="<c:url value='/scripts/datepicker/css/datepicker.css'/>"/>
    <style rel="stylesheet" type="text/css">
        .panel {
            margin-bottom: 10px;
            background-color: #ffffff;
            border: 1px solid #dddddd;
            border-radius: 4px;
            -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, 0.05);
            box-shadow: 0 1px 1px rgba(0, 0, 0, 0.05);
        }

        .panel-body {
            padding: 10px;
        }

        .panel .list-group {
            margin-bottom: 0;
        }

        .panel .list-group .list-group-item {
            border-width: 1px 0;
        }

        .panel .list-group .list-group-item:first-child {
            border-top-right-radius: 0;
            border-top-left-radius: 0;
        }

        .panel .list-group .list-group-item:last-child {
            border-bottom: 0;
        }

        .panel-heading + .list-group .list-group-item:first-child {
            border-top-width: 0;
        }

        .panel-heading {
            padding: 10px 10px;
            background-color: #f5f5f5;
            border-bottom: 1px solid #dddddd;
            border-top-right-radius: 3px;
            border-top-left-radius: 3px;
        }

        .panel-title {
            margin-top: 0;
            margin-bottom: 0;
            font-size: 15.5px;
        }

        .panel-title > a {
            color: inherit;
        }

        .panel-footer {
            padding: 10px 15px;
            background-color: #f5f5f5;
            border-top: 1px solid #dddddd;
            border-bottom-right-radius: 3px;
            border-bottom-left-radius: 3px;
        }

        .panel-group .panel {
            margin-bottom: 0;
            overflow: hidden;
            border-radius: 4px;
        }

        .panel-group .panel + .panel {
            margin-top: 5px;
        }

        .panel-group .panel-heading {
            border-bottom: 0;
        }

        .panel-group .panel-heading + .panel-collapse .panel-body {
            border-top: 1px solid #dddddd;
        }

        .panel-group .panel-footer {
            border-top: 0;
        }

        .panel-group .panel-footer + .panel-collapse .panel-body {
            border-bottom: 1px solid #dddddd;
        }

        .panel-primary {
            border-color: #428bca;
        }

        .panel-primary .panel-heading {
            color: #ffffff;
            background-color: #428bca;
            border-color: #428bca;
        }

        .panel-primary .panel-heading + .panel-collapse .panel-body {
            border-top-color: #428bca;
        }

        .panel-primary .panel-footer + .panel-collapse .panel-body {
            border-bottom-color: #428bca;
        }

        .panel-success {
            border-color: #d6e9c6;
        }

        .panel-success .panel-heading {
            color: #468847;
            background-color: #dff0d8;
            border-color: #d6e9c6;
        }

        .panel-success .panel-heading + .panel-collapse .panel-body {
            border-top-color: #d6e9c6;
        }

        .panel-success .panel-footer + .panel-collapse .panel-body {
            border-bottom-color: #d6e9c6;
        }

        .panel-warning {
            border-color: #fbeed5;
        }

        .panel-warning .panel-heading {
            color: #c09853;
            background-color: #fcf8e3;
            border-color: #fbeed5;
        }

        .panel-warning .panel-heading + .panel-collapse .panel-body {
            border-top-color: #fbeed5;
        }

        .panel-warning .panel-footer + .panel-collapse .panel-body {
            border-bottom-color: #fbeed5;
        }

        .panel-danger {
            border-color: #eed3d7;
        }

        .panel-danger .panel-heading {
            color: #b94a48;
            background-color: #f2dede;
            border-color: #eed3d7;
        }

        .panel-danger .panel-heading + .panel-collapse .panel-body {
            border-top-color: #eed3d7;
        }

        .panel-danger .panel-footer + .panel-collapse .panel-body {
            border-bottom-color: #eed3d7;
        }

        .panel-info {
            border-color: #bce8f1;
        }

        .panel-info .panel-heading {
            color: #3a87ad;
            background-color: #d9edf7;
            border-color: #bce8f1;
        }

        .panel-info .panel-heading + .panel-collapse .panel-body {
            border-top-color: #bce8f1;
        }

        .panel-info .panel-footer + .panel-collapse .panel-body {
            border-bottom-color: #bce8f1;
        }
    </style>
</head>

<div class="span3">
    <h2><fmt:message key='issueDetail.heading'/></h2>
</div>
<div class="span7">
    <form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
    <form:form commandName="issue" method="post" action="/issueform" id="issueForm"
               cssClass="well form-horizontal" onsubmit="">
        <form:hidden path="id"/>

        <div class="control-group">
            <appfuse:label styleClass="control-label" key="issue.summary"/>
            <div class="controls">
                <form:input path="summary" id="summary" maxlength="100"/>
                <form:errors path="summary" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <appfuse:label styleClass="control-label" key="issue.description"/>
            <div class="controls">
                <form:textarea path="description" id="description" maxlength="2000" rows="5" class="span11"/>
                <form:errors path="description" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <appfuse:label styleClass="control-label" key="issue.departament"/>
            <div class="controls">
                <select id="departament" name="departament" class="span11">
                    <option selected></option>
                    <c:forEach items="${departamentList}" var="depto">
                        <option value="${depto.id}" ${issue.departament.id eq depto.id ? " selected" : ""} >${depto.name}</option>
                    </c:forEach>
                </select>
                <form:errors path="departament" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <appfuse:label styleClass="control-label" key="issue.activity"/>
            <div class="controls">
                <select id="activity" name="activity" class="span11">
                    <option selected></option>
                    <c:forEach items="${activityList}" var="a">
                        <option value="${a.id}" ${issue.activity.id eq a.id ? " selected" : ""} >${a.name}</option>
                    </c:forEach>
                </select>
                <form:errors path="activity" cssClass="help-inline"/>
            </div>
        </div>
        <c:if test="${issue.project.id != null}">
            <div class="control-group">
                <appfuse:label styleClass="control-label" key="issue.project"/>
                <div class="controls">
                    <select id="project" name="project" class="span11" disabled>
                        <option selected></option>
                        <c:forEach items="${projectList}" var="p">
                            <option value="${p.id}" ${issue.project.id eq p.id ? " selected" : ""} >${p.name}</option>
                        </c:forEach>
                    </select>
                    <form:errors path="project" cssClass="help-inline"/>
                </div>
            </div>
        </c:if>
        <div class="control-group">
            <appfuse:label styleClass="control-label" key="issue.assigned"/>
            <div class="controls">
                <select id="assigned" name="assigned" class="span6">
                    <option selected=""></option>
                    <c:forEach items="${userList}" var="u">
                        <option value="${u.id}" ${issue.assigned.id eq u.id ? " selected" : "" } >${u.firstName}&#32;${u.lastName}</option>
                    </c:forEach>
                </select>
                <form:errors path="assigned" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <appfuse:label styleClass="control-label" key="issue.status"/>
            <div class="controls">
                <select id="status" name="status" class="span6">
                    <option selected=""></option>
                    <c:forEach items="${statusList}" var="s">
                        <option value="${s.id}" ${issue.status.id eq s.id ? " selected" : ""} >${s.description}</option>
                    </c:forEach>
                </select>
                <form:errors path="status" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <appfuse:label styleClass="control-label" key="issue.expectedDate"/>
            <div class="controls">
                <form:input path="expectedDate" id="expectedDate" class="span6"/>
                <form:errors path="expectedDate" cssClass="help-inline"/>
            </div>
        </div>
        <div class="form-actions">
            <button type="submit" class="btn btn-primary" name="save">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>
            <c:if test="${not empty issue.id}">
                <button type="submit" class="btn" name="delete">
                    <i class="icon-trash"></i> <fmt:message key="button.delete"/>
                </button>
            </c:if>
            <button type="submit" class="btn" name="cancel">
                <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
            </button>
            <button type="submit" class="btn" name="cancel">
                <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
            </button>
            <button type="button" class="btn btn-primary" name="videoConf" id="videoConf">
                <i class="icon-facetime-video icon-white"></i> <fmt:message key="button.videoConference"/>
            </button>
        </div>
    </form:form>

    <form:form commandName="history" method="post" action="/issueformAddComment" id="commentForm"
               class="well form-horizontal" onsubmit="">
        <input type="hidden" value="${issue.id}" id="idIssue" name="idIssue"/>

        <div class="control-group">
            <appfuse:label styleClass="control-label" key="issue.history"/>
            <div class="controls">
                <textarea id="comment" name="comment" maxlength="2000" rows="4" class="span10"></textarea>
                <button type="submit" class="btn btn-primary"
                        id="btnRemoveUser" ${issue.id != null ? "" : " disabled " }>
                    <i class="icon-comment icon-white"></i> <fmt:message key="button.add"/>
                </button>
                <form:errors path="description" cssClass="help-inline"/>
            </div>

        </div>
        <c:set var="user" value=""/>
        <c:forEach items="${issue.history}" var="h">

            <div ${h.author.username == user || user == "" ? " class='panel panel-info' " : " class='panel panel-primary' " } >
                <div class="panel-heading" style="padding: 3px">
                    <h5 class="panel-title">
                        <img src="" style="height:35px; width:35px;" class="gravatar img-polaroid"
                             email="${h.author.email}"/> ${h.author.username} - ${h.date}
                    </h5>
                </div>
                <div class="panel-body">
                    <textarea maxlength="2000" class="span12" disabled="true">${h.comment}</textarea>
                </div>
            </div>
            <c:set var="user" value="${h.author.username}"/>
        </c:forEach>
    </form:form>
</div>
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<script type="text/javascript" src="<c:url value="/scripts/MD5.js"/>"></script>
<script>
    $(function () {
        $("#expectedDate").datepicker();
    });

    function openVideoConference() {
        var url = "/issues/videoConference/" + ${issue.id};
        window.open(url);
    }

    var hash;
    $(document).ready(function () {

        $("#videoConf").click(function () {
            openVideoConference();
        });

        if ($("#departament").val() == null || $("#departament").val() == "") {
            $("#activity").attr("disabled", "disabled");
        }

        $('.gravatar').each(function (i, e) {
            hash = calcMD5($(e).attr("email"));
            $(e).attr('src', 'https://gravatar.com/avatar/' + hash + '?d=mm');
        });

        $("#departament").change(function () {

            if ($("#departament").val() == null || $("#departament").val() == "") {
                $("#activity").attr("disabled", "disabled");
                return false;
            }

            $("#activity").html("");
            $("#activity").append('<option selected></option>');

            if ($(this).val() != null) {

                $.ajax({
                    url: "/admin/activities/getActivities?idDepartament=" + $(this).val(),
                    type: "GET",
                    dataType: "json",
                    processData: true,
                    contentType: "application/json; charset=utf-8",
                    success: function (data) {
                        $(data).each(function (a, b) {
                            $("#activity").append('<option value=' + b.id + '>' + b.name + '</option>');
                        });
                        $("#activity").removeAttr("disabled");
                    },
                    error: function (data) {
                        alert("Erro ao buscar as Atividades do Departamento selecionado")
                    }
                });
            }
        });

    });

</script>
