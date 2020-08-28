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
    <div class="card card-body mt-4">
        <h2>Добавление чека</h2>
        <form action="/sale" method="post">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label" for="startPointSelect">Рейс:</label>
                <div class="col-sm-6">
                    <select class="form-control"
                            id="startPointSelect"
                            name="trip"
                            ${(Error??)?string('is-invalid', '')}>
                        <#if trips??>
                            <option selected disabled>Выберите</option>
                            <#list trips as trip>
                                <option value="${trip.id}">${trip}</option>
                            </#list>
                        </#if>
                    </select>
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
                            <option selected disabled>Выберите</option>
                            <#list busStops as busStop>
                                <option value="${busStop.id}">${busStop}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label" for="startPointSelect">Пункт назначения:</label>
                <div class="col-sm-6">
                    <select class="form-control"
                            id="startPointSelect"
                            name="endPoint"
                            ${(Error??)?string('is-invalid', '')}>
                        <#if busStops??>
                            <option selected disabled>Выберите</option>
                            <#list busStops as busStop>
                                <option value="${busStop.id}">${busStop}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Дата отправления:</label>
                <div class="col-sm-6">
                    <input type="datetime-local" name="date">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label" for="startPointSelect">Кассир:</label>
                <div class="col-sm-6">
                    <select class="form-control"
                            id="startPointSelect"
                            name="cashier"
                            ${(Error??)?string('is-invalid', '')}>
                        <#if cashiers??>
                            <option selected disabled>Выберите</option>
                            <#list cashiers as cashier>
                                <option value="${cashier.id}">${cashier}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Номер:</label>
                <div class="col-sm-6">
                    <input type="number" name="number">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Цена:</label>
                <div class="col-sm-6">
                    <input type="number" name="price">
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button class="btn btn-primary" type="submit">Создать</button>
        </form>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Название</th>
            <th scope="col">Номер</th>
            <th scope="col">Сумма</th>
            <th scope="col">ФН</th>
            <th scope="col">ФД</th>
            <th scope="col">ФП</th>
        </tr>
        </thead>
        <tbody>
        <#if tickets??>
            <#list tickets as ticket>
                <tr scope="row">
                    <td>${ticket}</td>
                    <td>${ticket.id}</td>
                    <td>${ticket.price}</td>
                    <td><#if ticket.cheque.statusDataDto??>${ticket.cheque.statusDataDto.statusDataDeviceDto.fn}</#if></td>
                    <td><#if ticket.cheque.statusDataDto??>${ticket.cheque.statusDataDto.statusDataDeviceDto.fdn}</#if></td>
                    <td><#if ticket.cheque.statusDataDto??>${ticket.cheque.statusDataDto.statusDataDeviceDto.fpd}</#if></td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>

</@c.page>
