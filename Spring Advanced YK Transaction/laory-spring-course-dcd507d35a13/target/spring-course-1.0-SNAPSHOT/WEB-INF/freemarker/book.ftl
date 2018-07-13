<html>
	<head><title>Cinema</title></head>
	<body>
        <h1>Book tickets</h1>

		<form action="${rc.contextPath}/tickets/book" method="POST">
            <table>
                <tr>
                    <td>Event Name:</td>
                    <td>
                        <input name="name" type="input"/>
                    </td>
                </tr>
                <tr>
                    <td>Seat:</td>
                    <td>
                        <input name="seatsStr" type="input"/>
                    </td>
                </tr>
                <tr>
                    <td>Event Name:</td>
                    <td>
                        <select name="name2">
                            <#list events as event>
                                <option value="${event.id}>${event.name}</option>
                            </#list>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Book tickets" />
                    </td>
                </tr>
            </table>
        </form>
	</body>
</html>