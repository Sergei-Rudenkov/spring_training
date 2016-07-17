<html>
<body>

<div id="content">

    <table class="datatable">
        <tr>
            <th>Name</th>  <th>Base Price</th>
        </tr>
    <#list model["eventList"] as event>
        <tr>
            <td>${event.name}</td> <td>${event.basePrice}</td>
        </tr>
    </#list>
    </table>

</div>
</body>
</html>