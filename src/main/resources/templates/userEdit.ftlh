<#import "parts/common.ftlh" as c>
<@c.page>
    <h1>Редактирование пользователя</h1>

        <form action="/user/${user.id}" method="post">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Имя входа:</label>
                <div class="col-sm-6">
                    <input type="text" name="username" class="form-control" value="${user.username}">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Полное имя:</label>
                <div class="col-sm-6">
                    <input type="text" name="fullName" class="form-control" value="${user.fullName}">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Password:</label>
                <div class="col-sm-6">
                    <input type="password" name="password" class="form-control" placeholder="Password" />
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Роли:</label>
                <div class="col-sm-6">
                    <#list roles as role>
                        <div class="form-check form-check-inline">
                            <input name="${role}" class="form-check-input" type="checkbox" id="inlineCheckbox1" ${user.roles?seq_contains(role)?string("checked", "")}>
                            <label class="form-check-label" for="inlineCheckbox1">${role}</label>
                        </div>
                    </#list>
                </div>
            </div>
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <input type="hidden" value="${user.id}" name="userId">
            <div class="form-group row">
                <button class="btn btn-primary" type="submit">Сохранить</button>
            </div>
        </form>


</@c.page>
