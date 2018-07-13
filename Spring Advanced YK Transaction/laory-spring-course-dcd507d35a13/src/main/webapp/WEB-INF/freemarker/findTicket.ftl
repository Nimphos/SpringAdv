<html>
	<head><title>Cinema</title></head>
	<body>
        <h1>Find booked tickets</h1>

		<form action="${rc.contextPath}/getBookedTickets" method="POST">
            <table>
                <tr>
                    <td>User email:</td>
                    <td>
                        <td><input name="userEmail" type="input"/></td>
                    </td>
                </tr>
                <tr>
                    <td>Event id:</td>
                    <td>
                        <td><input name="eventId" type="input"/></td>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Get tickets" />
                    </td>
                </tr>
            </table>
        </form>
	</body>
</html>