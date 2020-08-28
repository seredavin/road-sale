<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?if_exists}" placeholder="Search by tag" />
                <button class="btn btn-primary ml-2" type="submit">Search</button>
            </form>
        </div>
    </div>
</@c.page>