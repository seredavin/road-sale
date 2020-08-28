<#macro textInput route thisPoint="" busStops="">
<div class="form-group row">
    <label class="col-sm-2 col-form-label" for="startPointSelect">Остановка:</label>
    <div class="col-sm-6">
        <select class="form-control"
                id="startPointSelect"
                name="startPoint"
            <#if busStops??>
                <option ${(thisStartPoint??)?string('', 'selected')} disabled>Выберите</option>
                <#list busStops as busStop>
                    <option <#if thisPoint == busStop> selected</#if> value="${busStop.id}" >${busStop}</option>
                </#list>
            </#if>
        </select>
    </div>
</div>
</#macro>