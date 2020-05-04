<#ftl output_format="HTML" auto_esc=true />
<style>
    body {
        width: 100%;
        height: 100%;
        margin: 0;
    }
    table {
        border-collapse: separate;
        white-space: pre-wrap !important;
        line-height: normal;
        font-weight: normal;
        font-size: initial;
        font-style: normal;
        color: -moz-mac-accentdarkestshadow;
        text-align: start;
        word-break: break-word;
        font-family: monospace;
        tab-size: 4;
        border-spacing: 0px;
        font-variant: normal;
    }
    tbody {
        display: table-row-group;
        vertical-align: middle;
        border-color: inherit;
    }
    td {
        display: table-cell;
        vertical-align: baseline;
        padding: 0px !important;
    }
    tr {
        display: table-row;
        vertical-align: inherit;
        border-color: inherit;
    }
    .line-number {
        box-sizing: border-box;
        width: 15px;
        background-color: rgb(240, 240, 240);
        user-select: none;
        text-align: right;
        color: rgb(128, 128, 128);
        word-break: normal;
        white-space: nowrap;
        font-size: 9px;
        font-family: Helvetica;
        padding: 0px 4px !important;
        border-right: 1px solid rgb(187, 187, 187) !important;
    }
    .line-gutter-backdrop {
        box-sizing: border-box;
        width: 24px;
        background-color: rgb(240, 240, 240);
        user-select: none;
        position: absolute;
        z-index: -1;
        left: 0px;
        top: 0px;
        height: 100%;
        padding: 0px 4px !important;
        border-right: 1px solid rgb(187, 187, 187) !important;
    }
    .line-content {
        padding: 0px 5px !important;
    }
    .line-number::before {
        content: attr(value);
    }
</style>

<#assign rowNum = 1>
<div class="line-gutter-backdrop"></div>
<table>
    <tbody>
    <#list result as row>
        <tr>
            <td class="line-number" value="${rowNum}"></td>
            <td class="line-content">${row}</td>
        </tr>
        <#assign rowNum++>
    </#list>
    </tbody>
</table>