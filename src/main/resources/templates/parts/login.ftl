<#macro login path isRegistration>
<form action="${path}" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <div class="form-group">
        <label for="exampleInputEmail1">Name</label>
        <input type="text" name="username" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter name">
        </div>
    <div class="form-group">
        <label for="exampleInputPassword1">Password</label>
        <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
        </div>
    <#if isRegistration><div class="form-group">
        <label for="exampleInputPassword1">Email</label>
        <input type="email" name="email" class="form-control" id="exampleInputPassword1" placeholder="email@email.com">
        </div></#if>
    <button type="submit" class="btn btn-primary"><#if isRegistration>Register<#else>Sign in</#if></button>
    </form>
</#macro>
<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <input type="submit" class="btn btn-primary" value="Sign Out"/>
    </form>
</#macro>