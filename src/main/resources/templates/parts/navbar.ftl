<#include "security.ftl">
<#import "login.ftl" as l>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">DizeR</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
        </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/">Home</a>
                </li>
            <li class="nav-item active">
                <a class="nav-link" href="/main">Messages</a>
                </li>
            <li class="nav-item active">
                <a class="nav-link" href="/profile">Profile</a>
                </li>
            <#if isAdmin>
            <li class="nav-item active">
                <a class="nav-link" href="/user">User list</a>
                </li>
            </#if>
            </ul>
        <#if known>
         <@l.logout/>
</#if>
        </div>
    </nav>