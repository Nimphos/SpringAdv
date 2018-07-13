<html>
	<head><title>Cinema</title></head>
	<body>
		<h2>Events:</h2>
        <#list events as event>
            <p>${event.id}
            <p>${event.name}
            <p>${event.rate}
            <p>${event.basePrice}
            <p>${event.dateTime}
            <p>${event.auditorium.name}
        </#list>
	</body>
</html>