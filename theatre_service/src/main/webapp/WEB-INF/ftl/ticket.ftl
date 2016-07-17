<html>
<body>

<div id="content">

<#if model["ticketList"]?size gt 0>
    <table class="datatable">
        <tr>
            <th>User Name</th>  <th>Event</th>
        </tr>
        <#list model["ticketList"] as ticket>
            <tr>
                <td>${ticket.user.name}</td> <td>${ticket.event.name}</td>
            </tr>
        </#list>
    </table>
<#else>
    <p>There is no bought tickets yet.</p>
</#if>


</div>
</body>
</html>