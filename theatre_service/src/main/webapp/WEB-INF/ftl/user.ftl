<html>
<body>

<div id="content">

    <table class="datatable">
        <tr>
            <th>Firstname</th>  <th>Lastname</th>
        </tr>
    <#list model["userList"] as user>
        <tr>
            <td>${user.firstName}</td> <td>${user.lastName}</td>
        </tr>
    </#list>
    </table>

</div>
</body>
</html>