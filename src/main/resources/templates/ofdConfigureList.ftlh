<#import "parts/common.ftlh" as c>
<@c.page>
    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="alert alert-danger" role="alert">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
        </div>
    </#if>
    <#if message??>
        <div class="alert alert-${messageType}" role="alert">
            ${message}
        </div>
    </#if>
    <h1>Список чеков</h1>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Название</th>
            <th scope="col">Название организации</th>
            <th scope="col">ИНН организации</th>
            <th scope="col">API URL</th>
            <th scope="col">Пользователь API</th>
        </tr>
        </thead>
        <tbody>
        <#if configures??>
            <#list configures as configure>
                <tr scope="row">
                    <td><a href="/ofd-config/${configure.id}">${configure.name}</a></td>
                    <td>${configure.companyName}</td>
                    <td>${configure.companyInn}</td>
                    <td>${configure.serviceUrl}</td>
                    <td>${configure.serviceUser}</td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</div>
</@c.page>


