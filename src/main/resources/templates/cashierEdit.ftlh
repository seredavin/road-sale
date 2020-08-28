<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="card card-body mt-4">
        <h2>Редактирование кассира</h2>
        <form action="/cashier/${cashier.id}" method="post">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">ФИО:</label>
                <div class="col-sm-6">
                    <input type="text" name="name"
                           class="form-control ${(nameError??)?string('is-invalid', '')}"
                           placeholder="ФИО"
                           value="${cashier.name}"
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
                           value="${cashier.inn}"
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


</@c.page>
