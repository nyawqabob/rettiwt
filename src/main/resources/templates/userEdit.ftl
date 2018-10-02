<#import "parts/common.ftl" as c>
<@c.page>
List of users

<form action="/user" method="post">
    <input type="text" name="username" value="${user.username}" type="required">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <#list roles as role>
    <div>
      <input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</input> 
        
        </div>
</#list>
    <input type="hidden" value="${user.id}" name="userId">
    <input type="submit" name="submit">
    </form>
</@c.page>