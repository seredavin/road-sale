<#import "parts/common.ftlh" as c>
<@c.page>
    <h1>Редактирование конфигурации ОФД</h1>

    <form action="/ofd-config/${configure.id}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Название:</label>
            <div class="col-sm-6">
                <input type="text" name="name" class="form-control" value="${configure.name}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Организация:</label>
            <div class="col-sm-6">
                <input type="text" name="companyName" class="form-control" value="${configure.companyName}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">ИНН организации:</label>
            <div class="col-sm-6">
                <input type="text" name="companyInn" class="form-control" value="${configure.companyInn}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Система налогообложения:</label>
            <div class="col-sm-6">
                <input type="text" name="taxationSystem" class="form-control" value="${configure.taxationSystem}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Признак предмета расчета для всего чека:</label>
            <div class="col-sm-6">
                <input type="text" name="defaultPaymentType" class="form-control" value="${configure.defaultPaymentType}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Признак способа расчета:</label>
            <div class="col-sm-6">
                <input type="text" name="defaultPaymentMethod" class="form-control" value="${configure.defaultPaymentMethod}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Адрес установки кассы:</label>
            <div class="col-sm-6">
                <input type="text" name="billAddress" class="form-control" value="${configure.billAddress}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Налог:</label>
            <div class="col-sm-6">
                <input type="text" name="defaultVat" class="form-control" value="${configure.defaultVat}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">API URL:</label>
            <div class="col-sm-6">
                <input type="text" name="serviceUrl" class="form-control" value="${configure.serviceUrl}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">API Пользователь:</label>
            <div class="col-sm-6">
                <input type="text" name="serviceUser" class="form-control" value="${configure.serviceUser}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">API Пароль:</label>
            <div class="col-sm-6">
                <input type="text" name="servicePassword" class="form-control" value="${configure.servicePassword}">
            </div>
        </div>
        <div class="form-group row">
            <label class="form-check-label" for="inlineCheckbox1">Вендиговый аппарат</label>
            <input name="kktFA" class="form-check-input" type="checkbox" id="inlineCheckbox1" ${(configure.kktFA)?string('checked', '')}>
        </div>
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <input type="hidden" value="${configure.id}" name="id">
        <div class="form-group row">
            <button class="btn btn-primary" type="submit">Сохранить</button>
        </div>
    </form>

</@c.page>
