<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
${message?ifExists}
<@l.login "/login" false/>
<a href="/registration">Registration</a>
</@c.page>
