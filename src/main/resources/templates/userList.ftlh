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
    <h1>Список пользователей</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Имя</th>
            <th scope="col">Роли</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#if users??>
            <#list users as user>
                <tr scope="row">
                    <td>${user.fullName} <i>(${user.username})</i></td>
                    <td><#list user.roles as role>
                            <span class="badge badge-secondary">
                            ${role}
                            </span>
                        <#sep>  </#list></td>
                    <td><a href="/user/${user.id}">Изменить</a></td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>

        <div class="card card-body">
            <h2>Добавление пользователя</h2>
            <form action="/user" method="post">
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Имя для входа:</label>
                    <div class="col-sm-6">
                        <input type="text" name="username"
                               class="form-control ${(usernameError??)?string('is-invalid', '')}"
                               placeholder="Имя для входа"
                               <#if thisUsername??>
                                value="${thisUsername}"
                               </#if>
                                />
                        <#if usernameError??>
                            <div class="invalid-feedback">
                                ${usernameError}
                            </div>
                        </#if>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Полное имя:</label>
                    <div class="col-sm-6">
                        <input type="text" name="fullName"
                               class="form-control ${(fullNameError??)?string('is-invalid', '')}"
                               placeholder="Полное имя"
                        />
                        <#if fullNameError??>
                            <div class="invalid-feedback">
                                ${fullNameError}
                            </div>
                        </#if>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Пароль:</label>
                    <div class="col-sm-6">
                        <input type="password" name="password"
                               class="form-control ${(passwordError??)?string('is-invalid', '')}"
                               placeholder="Пароль" />
                        <#if passwordError??>
                            <div class="invalid-feedback">
                                ${passwordError}
                            </div>
                        </#if>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Подтверждение пароля:</label>
                    <div class="col-sm-6">
                        <input type="password" name="password2"
                               class="form-control ${(password2Error??)?string('is-invalid', '')}"
                               placeholder="Повторите пароль" />
                        <#if password2Error??>
                            <div class="invalid-feedback">
                                ${password2Error}
                            </div>
                        </#if>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button class="btn btn-primary" type="submit">Создать</button>
            </form>
        </div>
</@c.page>
