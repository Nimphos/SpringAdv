<html>
    <head><title>Tickets</title></head>
    <body>
        <h2>Tickets:</h2>
        <#list tickets as ticket>
            <p>${ticket.id}
            <p>${ticket.user.name}
            <p>${ticket.event.name}
            <p>${ticket.event.auditorium.name}
            <p>${ticket.seats}
            <p>${ticket.price}
        </#list>
    </body>
</html>
