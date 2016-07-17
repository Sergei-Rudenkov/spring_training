<html>
<body>

<div id="content">

    <table class="datatable">
        <tr>
            <th>Name</th>  <th>Number of seats</th>
        </tr>
    <#list model["auditoriumList"] as auditorium>
        <tr>
            <td>${auditorium.name}</td> <td>${auditorium.numberOfSeats}</td>
        </tr>
    </#list>
    </table>

</div>
</body>
</html>