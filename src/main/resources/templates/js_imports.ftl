<#--
                              Use this file for adding Javascript
-->
<@javascriptImports />

<#macro javascriptImports>
    <!-- JavaScript files -->
    <script type="text/javascript" src="plugin/codemirror/lib/codemirror.js"></script>
    <!-- Freemarker mode -->
    <script type="text/javascript" src="plugin/codemirror/mode/freemarker/freemarker.js"></script>
    <!-- Markdown mode -->
    <script type="text/javascript" src="plugin/codemirror/mode/markdown/markdown.js"></script>
    <script type="text/javascript" src="plugin/codemirror/mode/gfm/gfm.js"></script>
    <!-- XML mode -->
    <script type="text/javascript" src="plugin/codemirror/mode/xml/xml.js"></script>
    <!-- JSON mode -->
    <script type="text/javascript" src="plugin/codemirror/mode/javascript/javascript.js"></script>
    <!-- CSV mode -->
    <script type="text/javascript" src="plugin/codemirror/mode/mathematica/mathematica.js"></script>

    <!-- Codemirror addons -->
    <script type="text/javascript" src="plugin/codemirror/addon/mode/overlay.js"></script>
    <script type="text/javascript" src="plugin/codemirror/addon/selection/active-line.js"></script>

    <#-- Hints -->
    <script type="text/javascript" src="plugin/codemirror/addon/hint/anyword-hint.js"></script>
    <script type="text/javascript" src="plugin/codemirror/addon/hint/show-hint.js"></script>


    <!-- Javascript Bootstrap -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>

    <!-- App JS -->
    <script type="text/javascript" src="js/app.js"></script>
    <script type="text/javascript" src="js/toolbox.js"></script>
    <script type="text/javascript" src="js/FreemarkerDictionary.js"></script>

    <!-- Load React. -->
    <!-- Note: when deploying, replace "development.js" with "production.min.js". -->
    <script src="https://unpkg.com/react@16/umd/react.production.min.js" crossorigin></script>
    <script src="https://unpkg.com/react-dom@16/umd/react-dom.production.min.js" crossorigin></script>
    <!-- Load our React component. -->
    <script type="text/babel" src="js/components/expressions.js"></script>
    <script src="https://unpkg.com/babel-standalone@6/babel.min.js"></script>
</#macro>