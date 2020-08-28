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
        <h2>Добавление маршрута</h2>
        <form action="/route" method="post">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Номер:</label>
                <div class="col-sm-6">
                    <input type="text" name="number"
                           class="form-control ${(Error??)?string('is-invalid', '')}"
                           placeholder="Номер"
                            <#if thisNumber??>
                                value="${thisNumber}"
                            </#if>
                    />
                    <#if Error ??>
                        <div class="invalid-feedback">
                            ${Error}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label" for="startPointSelect">Пункт отправления:</label>
                <div class="col-sm-6">
                    <select class="form-control"
                            id="startPointSelect"
                            name="startPoint"
                            ${(Error??)?string('is-invalid', '')}>
                        <#if busStops??>
                            <option ${(thisStartPoint??)?string('', 'selected')} disabled>Выберите</option>
                            <#list busStops as busStop>
                                <option <#if thisStartPoint??>
                                    <#if thisStartPoint == busStop> selected</#if>
                                </#if>
                                        value="${busStop.id}"
                                >${busStop}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label" for="endPointSelect">Пункт назначения:</label>
                <div class="col-sm-6">
                    <select class="form-control"
                        id="endPointSelect"
                        name="endPoint"
                        ${(Error??)?string('is-invalid', '')}>
                        <#if busStops??>
                            <option ${(thisEndPoint??)?string('', 'selected')} disabled>Выберите</option>
                            <#list busStops as busStop>
                                <option <#if thisEndPoint??>
                                            <#if thisEndPoint == busStop> selected</#if>
                                        </#if>
                                    value="${busStop.id}"
                                >${busStop}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button class="btn btn-primary" type="submit">Создать</button>
        </form>
    </div>
    <h1>Список маршрутов</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Название</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#if routes??>
            <#list routes as route>
                <tr scope="row">
                    <td>${route}</td>
                    <td><a href="/route/${route.id}">Изменить</a> </td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</@c.page>
