<#import "parts/common.ftl" as c>
<@c.page>
List of users
<#list users as user>
<table>
    <tr>
        <th>Name</th>
        <th>Role</th>
        <th>Edit</th>

        </tr>
    <tr>
        <td>${user.username}</td>
        <td><#list user.roles as role>${role}<#sep>, </#list></td>
        <td><a href="user/${user.id}">edit</a></td>

        </tr>
    </table>

</#list>
</@c.page>