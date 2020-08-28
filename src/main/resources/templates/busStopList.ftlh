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

    <div class="card card-body mt-4">
        <h2>Добавление остановки</h2>
        <form action="/bus-stop" method="post">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Название:</label>
                <div class="col-sm-6">
                    <input type="text" name="name"
                           class="form-control ${(nameError??)?string('is-invalid', '')}"
                           placeholder="Название"
                            <#if thisName??>
                                value="${thisName}"
                            </#if>
                    />
                    <#if nameError??>
                        <div class="invalid-feedback">
                            ${nameError}
                        </div>
                    </#if>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button class="btn btn-primary" type="submit">Создать</button>
        </form>
    </div>
    <h1>Список остановок</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Название</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#if busStops??>
            <#list busStops as busStop>
                <tr scope="row">
                    <td>${busStop.name}</td>
                    <td><a href="/cashier/${busStop.id}">Изменить</a> </td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</@c.page>
