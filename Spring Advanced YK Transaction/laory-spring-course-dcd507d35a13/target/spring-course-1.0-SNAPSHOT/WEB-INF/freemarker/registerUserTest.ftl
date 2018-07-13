<html>
	<head><title>Cinema</title></head>
	<body>
        <h1>New user registration:</h1>

        <@spring.bind path= "newUser" />

		<form action="${rc.contextPath}/registerUser" method="POST">
            <table>
                <tr>
                    <td>Name:</td>
                    <td>
                        <@spring.formInput "newUser.name" />
                    </td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td>
                        <@spring.formInput "newUser.email" />
                    </td>
                </tr>
                <tr>
                    <td>Role:</td>
                    <td>
                        <@spring.formInput "newUser.role" />
                    </td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td>
                        <@spring.formPasswordInput "newUser.password" />
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