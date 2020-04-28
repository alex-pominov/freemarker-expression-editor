<#assign name = 'Alex'>
<#assign yearOfBirthday = 2010>
<#assign job = 'teacher'>

<#function calcAge yearOfBirthday>
    <#assign curYear = .now?string('yyyy')?number>
    <#return (curYear - yearOfBirthday)>
</#function>

<#function getAppeal age>
    <#if age < 18>
        <#return ('Teenager')>
    <#else>
        <#return ('Mr/Ms')>
    </#if>
</#function>

<#function getRandomId>
    <#assign randomId = .now?long % 1000 + 1>
    <#return (randomId)>
</#function>
<#switch job>
    <#case 'driver'>
        ${getAppeal(calcAge(yearOfBirthday))} ${name} is ${calcAge(yearOfBirthday)} years old.
        ${name} working as a ${job}. He drives uber in Lisbon. ${name} id = ${getRandomId()}
        <#break>
    <#case 'teacher'>
        ${getAppeal(calcAge(yearOfBirthday))} ${name} is ${calcAge(yearOfBirthday)} years old.
        ${name} working as a ${job}. He teaches students at University. ${name} id = ${getRandomId()}
        <#break>
    <#case 'programmer'>
        ${getAppeal(calcAge(yearOfBirthday))} ${name} is ${calcAge(yearOfBirthday)} years old.
        ${name} working as a ${job}. He likes programming with Java and Javascript. ${name} id = ${getRandomId()}
        <#break>
    <#default>
        ${name} is ${calcAge(yearOfBirthday)} years old.
        ${name} working as a ${job}. ${name} id = ${getRandomId()}
        <#break>
</#switch>