<html>
	<head><title>Cinema</title></head>
	<body>
        <h1>New user registration:</h1>

		<form action="${rc.contextPath}/registerUser" method="POST">
            <table>
                <tr>
                    <td>Name:</td>
                    <td>
                        <td><input name="name" type="input"/></td>
                    </td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td>
                        <td><input name="email" type="input"/></td>
                    </td>
                </tr>
                <tr>
                    <td>Role:</td>
                    <td>
                        <td><input name="role" type="input"/></td>
                    </td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td>
                        <td><input name="password" type="password"/></td>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Register" />
                    </td>
                </tr>
            </table>
        </form>
	</body>
</html>