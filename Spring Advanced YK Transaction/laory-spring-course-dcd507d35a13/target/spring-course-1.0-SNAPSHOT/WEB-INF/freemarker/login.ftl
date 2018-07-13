<html>
    <head><title>Login form</title></head>
    <body>
        <div>
            <h1>Login</h1>
            <form method="post" action="${rc.contextPath}/login">
                <table>
                    <tr>
                        <th><label for="email">Email</label></th>
                        <td><input name="username" type="text"/></td>
                    </tr>
                    <tr>
                        <th><label for="password">Password</label></th>
                        <td><input name="password" type="password"/></td>
                    </tr>
                    <tr>
                        <th></th>
                        <td>
                            <input name="remember-me" type="checkbox"/>
                            <label for="remember-me">Remember me</label>
                        </td>
                    </tr>
                    <tr>
                        <th></th>
                        <td><input name="commit" type="submit" value="Login" /></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>