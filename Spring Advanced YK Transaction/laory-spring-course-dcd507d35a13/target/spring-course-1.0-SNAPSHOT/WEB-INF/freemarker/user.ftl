<html>
    <head><title>Users</title></head>
    <body>
        <h2>Users:</h2>
        <#list users as user>
            <p>${user.id}
            <p>${user.name}
            <p>${user.birthday}
            <p>${user.email}
            <p>${user.password}
            <p>${user.role}
        </#list>
    </body>
</html>