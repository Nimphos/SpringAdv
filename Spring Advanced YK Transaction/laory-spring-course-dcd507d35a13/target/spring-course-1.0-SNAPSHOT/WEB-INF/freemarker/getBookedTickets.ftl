html>
	<head><title>Cinema</title></head>
	<body>
		<h2>Booked tickets:</h2>
        <#list tickets as ticket>
            <p>${ticket.id}
            <p>${ticket.user.name}
            <p>${ticket.event.name}
            <p>${ticket.event.auditorium.name}
            <p>${ticket.seat}
            <p>${ticket.price}
        </#list>
	</body>
</html>