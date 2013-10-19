<%@ include file="/common/taglibs.jsp" %>
<head>
    <script src='https://swww.tokbox.com/webrtc/v2.0/js/TB.min.js'></script>
</head>


<div class="span10">
    <h2>
        <fmt:message key='issue.videoConference'>
            <fmt:param value="${issue.id}"/>
            <fmt:param value="${issue.summary}"/>
        </fmt:message>
    </h2>

    <div id="${issue.id}" name="${issue.id}"></div>
    <br>
    <br>

</div>

<script type="text/javascript">
    $('#' +${issue.id}).videoChatInit({ apiKey: ${command.apiKey},
        sessionId: '${command.sessionKey}',
        token: '${command.userToken}' });

</script>