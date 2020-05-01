<?xml version="1.0" encoding="utf-8"?>
<#ftl output_format="XML" auto_esc=false>
    ${snippetText?html}

<#macro book>
    <html>
    <head>
        <title>qwe</title>
    </head>
    <body>
        ${snippetText}
    </body>
    </html>
</#macro>

<@book />


<#--<#macro @text>${.node?html}</#macro>-->