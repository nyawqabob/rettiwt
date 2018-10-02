<#import "parts/common.ftl" as c>
<@c.page>
<form class="form-inline" method="get" action="/main">
    <label class="sr-only" for="inlineFormInputName2">Name</label>
    <input type="text" name="filter" value="${filter?ifExists}" class="form-control mb-2 mr-sm-2" id="inlineFormInputName2" placeholder="Search by tag">
    <button type="submit" class="btn btn-primary mb-2">Search</button>
    </form>
<div>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new message
        </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group">
            <form method="post" enctype="multipart/form-data">
                <input type="text" name="text" class="form-control mt-2" placeholder="Your message" />
                <input type="text" name="tag" class="form-control mt-2" placeholder="Tag">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="custom-file mt-2">
                    <input type="file" name="file" class="custom-file-input" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                <button type="submit" class="btn btn-primary mb-2">Add</button>
                </form>
            </div>
        </div>
    </div>
<#list messages as message>
<div class="card mt-3 mb-3" style="width: 18rem;">
  <#if message.filename??><img class="card-img-top" src="/img/${message.filename}" alt="Card image cap"></#if>
  <div class="card-body">
    <h5 class="card-title">${message.authorName}</h5>
    <p class="card-text">${message.text}</p>
    <p class="card-text"><small class="text-muted">${message.tag}</small></p>
  </div>
</div>
<#else>
No messages
</#list>
</@c.page>