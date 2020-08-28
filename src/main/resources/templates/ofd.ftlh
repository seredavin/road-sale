<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="row justify-content-center">
        <div class="col-md-4">
                <span class="h1">Проверка билета</span>

            <form method="get" action="/ofd" class="form my-4">
                <div class="input-group mb-3">
                    <input type="text"
                           name="ticketNumber"
                           class="form-control"
                           value="${ticketNumber?if_exists}"
                           placeholder="Введите номер билета"
                           aria-label="Введите номер билета"
                           aria-describedby="basic-addon2">
                    <div class="input-group-append">
                        <button class="btn btn-primary" type="submit">Найти</button>
                    </div>
                </div>
            </form>


            <#if !error??>
                <#if ticket??>
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title mt-4">Билет №${ticketNumber} найден</h5>
                            <p class="card-text">Билет успешно фискализирован. Перейдите на сайт оператора фискальных данных</p>
                            <div id="qrcode" class="my-4"></div>
                            <#noautoesc>
                            <script type="text/javascript">
                                var qrcode = new QRCode(document.getElementById("qrcode"), {
                                    text: "${qrString}",
                                    width: 128,
                                    height: 128,
                                    colorDark : "#2e2e2e",
                                    colorLight : "#ffffff",
                                    correctLevel : QRCode.CorrectLevel.H
                                });
                            </script>
                            </#noautoesc>
                            <a href="${ticket}" target="_blank" class="btn btn-primary">Перейти на сайт ОФД</a>
                        </div>
                    </div>
                </#if>
            <#else> <div class="alert alert-warning" role="alert">
                    ${error}
                </div>
            </#if>
        </div>
    </div>

</@c.page>