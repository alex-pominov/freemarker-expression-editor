<@javascriptImports />

<#macro javascriptImports>
    <#--  Codemirror main js  -->
    <script type="text/javascript" src="plugin/codemirror/lib/codemirror.js"></script>
    <!-- Codemirror modes -->
    <script type="text/javascript" src="plugin/codemirror/mode/freemarker/freemarker.js"></script>
    <script type="text/javascript" src="plugin/codemirror/mode/markdown/markdown.js"></script>
    <script type="text/javascript" src="plugin/codemirror/mode/gfm/gfm.js"></script>
    <script type="text/javascript" src="plugin/codemirror/mode/xml/xml.js"></script>
    <script type="text/javascript" src="plugin/codemirror/mode/javascript/javascript.js"></script>
    <script type="text/javascript" src="plugin/codemirror/mode/mathematica/mathematica.js"></script>
    <!-- Codemirror addons -->
    <script type="text/javascript" src="plugin/codemirror/addon/mode/overlay.js"></script>
    <script type="text/javascript" src="plugin/codemirror/addon/selection/active-line.js"></script>
    <script type="text/javascript" src="plugin/codemirror/addon/hint/show-hint.js"></script>

    <!-- Bootstrap -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

    <!-- App JS -->
    <script type="text/javascript" src="js/app.js"></script>
    <script type="text/javascript" src="js/referenceModal.js"></script>
    <script type="text/javascript" src="js/FreemarkerDictionary.js"></script>

    <!-- Load React. -->
    <!-- Note: when deploying, replace "development.js" with "production.min.js". -->
    <script src="https://unpkg.com/react/umd/react.production.min.js" crossorigin></script>
    <script src="https://unpkg.com/react-dom/umd/react-dom.production.min.js" crossorigin></script>
    <!-- Load React component. -->
    <script src="https://unpkg.com/react-bootstrap@next/dist/react-bootstrap.min.js" crossorigin></script>
    <script src="https://unpkg.com/babel-standalone@6/babel.min.js"></script>
    <script type="text/babel" src="js/reactjs/expressions.js"></script>
</#macro>