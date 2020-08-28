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
        <h2>Добавление кассира</h2>
        <form action="/cashier" method="post">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">ФИО:</label>
                <div class="col-sm-6">
                    <input type="text" name="name"
                           class="form-control ${(nameError??)?string('is-invalid', '')}"
                           placeholder="ФИО"
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
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">ИНН:</label>
                <div class="col-sm-6">
                    <input type="text" name="inn"
                           class="form-control ${(innError??)?string('is-invalid', '')}"
                           placeholder="ИНН"
                            <#if thisInn??>
                                value="${thisInn}"
                            </#if>
                    />
                    <#if innError??>
                        <div class="invalid-feedback">
                            ${innError}
                        </div>
                    </#if>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button class="btn btn-primary" type="submit">Создать</button>
        </form>
    </div>
    <h1>Список кассиров</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Имя</th>
            <th scope="col">ИНН</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#if cashiers??>
            <#list cashiers as cashier>
                <tr scope="row">
                    <td>${cashier.name}</td>
                    <td>${cashier.inn}</td>
                    <td><a href="/cashier/${cashier.id}">Изменить</a> </td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</@c.page>
