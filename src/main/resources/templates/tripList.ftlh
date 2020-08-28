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
        <h2>Добавление рейса</h2>
        <form action="/trip" method="post">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Дата:</label>
                <div class="col-sm-6">
                    <input type="datetime-local" name="date">
                </div>

            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label" for="endPointSelect">Маршрут:</label>
                <div class="col-sm-6">
                    <select class="form-control"
                            id="endPointSelect"
                            name="route"
                            ${(Error??)?string('is-invalid', '')}>
                        <#if routes??>
                            <option selected disabled>Выберите</option>
                            <#list routes as route>
                                <option value="${route.id}"
                                >${route}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button class="btn btn-primary" type="submit">Создать</button>
        </form>
    </div>
    <h1>Список рейсов</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Время отправления</th>
            <th scope="col">Маршрут</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#if trips??>
            <#list trips as trip>
                <tr scope="row">
                    <td>${trip.startDate}</td>
                    <td>${trip.route}</td>
                    <td><a href="/trip/${trip.id}">Изменить</a> </td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</@c.page>
