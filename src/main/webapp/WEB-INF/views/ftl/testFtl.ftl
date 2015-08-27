<Html>
    <head>
        <title>${message}</title>
    </head>
    <body>
        <b>${message}</b><br>
        <b>循环模板测试：</b><br>
        <#if listStr??>
        	<#list listStr as entity>
        		<b>${entity}</><br>
        	</#list>
        </#if>
    <body>
</html>