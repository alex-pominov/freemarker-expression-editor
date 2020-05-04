<#ftl output_format="HTML" auto_esc=false />
<!DOCTYPE HTML>
<html>
<head>
    <title>Result</title>
</head>
<body>
<#list snippetText as row>
    ${row?trim}
</#list>
</body>
</html>