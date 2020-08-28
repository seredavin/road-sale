<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="card card-body mt-4">
        <h2>Изменение маршрута</h2>
        <form action="/route/${route.id}" method="post">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Номер:</label>
                <div class="col-sm-6">
                    <input type="text" name="number"
                           class="form-control ${(Error??)?string('is-invalid', '')}"
                           placeholder="Номер"
                            <#if route.number??>
                                value="${route.number}"
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
                                    <#elseif route.startPoint == busStop> selected
                                </#if>
                                        value="${busStop.id}"
                                >${busStop}</option>
                            </#list>
                        </#if>
                    </select>
                    <#if Error ??>
                        <div class="invalid-feedback">
                            ${Error}
                        </div>
                    </#if>
                </div>
            </div>
            <#list route.busStops as routeBusStop>
                <#if routeBusStop_index != 0 && routeBusStop_index != route.busStops?size - 1>
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Остановка:</label>
                        <div class="col-sm-6">
                            <select class="form-control"
                                    name="point_${routeBusStop_index}"
                                    ${(Error??)?string('is-invalid', '')}>
                                <#if busStops??>
                                    <option ${(routeBusStop??)?string('', 'selected')} disabled>Выберите</option>
                                    <#list busStops as busStop>
                                        <option <#if routeBusStop == busStop> selected value="${busStop.id}"</#if>>${busStop}</option>
                                    </#list>
                                </#if>
                            </select>
                            <#if Error ??>
                                <div class="invalid-feedback">
                                    ${Error}
                                </div>
                            </#if>
                        </div>
                    </div>
                </#if>
            </#list>
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
                                <#elseif route.endPoint == busStop> selected
                                </#if>
                                        value="${busStop.id}"
                                >${busStop}</option>
                            </#list>
                        </#if>
                    </select>
                    <#if Error ??>
                        <div class="invalid-feedback">
                            ${Error}
                        </div>
                    </#if>
                </div>
            </div>
            <input type="hidden" name="route_id" value="${route.id}" />
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button class="btn btn-primary" type="submit">Сохранить изменения</button>
        </form>
    </div>
    <div class="card card-body mt-4">
        <h2>Добавить новую остановку</h2>
        <form action="/route/${route.id}" method="post">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label" for="addPoint">После остановки:</label>
                <div class="col-sm-6 ">
                    <select class="form-control"
                            id="postPoint"
                            name="postPoint"
                            ${(Error??)?string('is-invalid', '')}>
                        <#if busStops??>
                        <#--                                <option selected disabled>Выберите</option>-->
                            <#list route.busStops as busStop>
                                <#if busStop_index != route.busStops?size - 1><option value="${busStop_index}">${busStop}</option></#if>
                            </#list>
                        </#if>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label" for="addPoint">Новая остановка:</label>
                <div class="col-sm-6 ">
                    <select class="form-control"
                            id="addPoint"
                            name="addPoint"
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

            <input type="hidden" name="route_id" value="${route.id}" />
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button class="btn btn-primary" type="submit">Добавить остановку</button>
        </form>
    </div>


</@c.page>
