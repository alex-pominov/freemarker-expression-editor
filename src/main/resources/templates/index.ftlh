<!DOCTYPE HTML>
<html xmlns:th="https://www.thymeleaf.org">
<head>
    <title>Editor</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <!--  CodeMirror  -->
    <link rel="stylesheet" href="plugin/codemirror/lib/codemirror.css"/>
    <link rel="stylesheet" href="plugin/codemirror/addon/lint/lint.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <link rel="stylesheet" href="css/toolbox.css"/>
    <link rel="stylesheet" href="css/input.css"/>
    <!--  Bootstrap  -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>

<body>
<div class="container-fluid px-0 py-0">
    <div class="row ml-0 mr-0">
        <!--  Toolbox  -->
        <div class="col-2 px-0 py-0 toolbox">
            <h3 class="py-3 px-3 toolbox--header">Expressions:</h3>
            <div class="accordion" id="accordionExample">
                <!--  Variables  -->
                <div class="card border-0">
                    <div class="card-header toolbox--element" id="headingOne">
                        <h2 class="mb-0">
                            <button class="button collapsed variables" type="button" data-toggle="collapse"
                                    data-target="#collapseOne"
                                    aria-expanded="true" aria-controls="collapseOne">
                                Variables
                            </button>
                        </h2>
                    </div>

                    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne"
                         data-parent="#accordionExample">
                        <div class="card-body toolbox--element__menu">
                            <ul class="nav flex-column">
                                <li class="nav-item">
                                    <button type="button" onclick="toModalHelper('person')">Person</button>
                                </li>
                                <li class="nav-item">
                                    <button type="button">Customer</button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!--  Loops  -->
                <div class="card border-0">
                    <div class="card-header toolbox--element" id="headingTwo">
                        <h2 class="mb-0">
                            <button class="button collapsed loops" type="button" data-toggle="collapse"
                                    data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                Filters
                            </button>
                        </h2>
                    </div>
                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
                        <div class="card-body toolbox--element__menu">
                            <ul class="nav flex-column">
                                <li class="nav-item">
                                    <button type="button" onclick="loopCollection()">Loop over collection</button>
                                </li>
                                <li class="nav-item">
                                    <button type="button" onclick="loopKeyValue()">Loop over key-value pairs</button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!--  Filters  -->
                <div class="card border-0">
                    <div class="card-header toolbox--element" id="headingThree">
                        <h2 class="mb-0">
                            <button class="button collapsed filters" type="button" data-toggle="collapse"
                                    data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                Control and loops
                            </button>
                        </h2>
                    </div>
                    <div id="collapseThree" class="collapse" aria-labelledby="headingThree"
                         data-parent="#accordionExample">
                        <div class="card-body toolbox--element__menu">
                            <ul class="nav flex-column">
                                <li class="nav-item">
                                    <button type="button" onclick="openMethodModalHelper('Assign')">Assign</button>
                                </li>
                                <li class="nav-item">
                                    <button type="button" onclick="openMethodModalHelper('Attempt, recover')">Attempt, recover
                                    </button>
                                </li>
                                <li class="nav-item">
                                    <button type="button" onclick="openMethodModalHelper('Function, return')">Function, return
                                    </button>
                                </li>
                                <li class="nav-item">
                                    <button type="button" onclick="openMethodModalHelper('If, else, elseif')">If, else, elseif
                                    </button>
                                </li>
                                <li class="nav-item">
                                    <button type="button" onclick="openMethodModalHelper('Setting')">Setting
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--  Expression editor & result  -->
        <div class="col px-0 py-0">
            <form action="/processTemplate" target="resultFrame" name="snippet" method="post" id="evalForm">
                <div class="row ml-0 mr-0 justify-content-between header  align-items-center">
                    <div class="col-6 px-0 py-0">
                        <div class="row mx-4 justify-content-between">
                            <div class="row ml-0 mr-auto">
                                <h3 class="mr-2">Format:</h3>
                                <select id="formatType" name="formatType" onchange="onFormatTypeChange(this)">
                                    <option selected value="expression">Expression</option>
                                    <option value="#">-----</option>
                                    <option value="html">HTML</option>
                                    <option value="markdown">Markdown</option>
                                    <option value="#">------</option>
                                    <option value="xml">XML</option>
                                    <option value="json">JSON</option>
                                    <option value="csv">CSV</option>
                                </select>
                            </div>

                            <div id="wysiswyg" class="checkbox-container mx-4">
                                <label class="checkbox-label">
                                    <input type="checkbox">
                                    <span class="checkbox-custom"></span>
                                </label>
                                <h3 class="input-title ml-2">WYSISWIG</h3>
                            </div>

                            <div id="__" class="checkbox-container mx-4">
                                <label class="checkbox-label">
                                    <input type="checkbox" name="performEvaluation" checked>
                                    <span class="checkbox-custom"></span>
                                </label>
                                <h3 class="input-title ml-2">Evaluate</h3>
                            </div>

                        </div>
                    </div>
                    <div class="col-6 px-0 py-0">
                        <div class="row mx-4">
                            <h3 class="mr-2">Result: </h3>
                            <select id="resultType" name="resultType">
                                <option value="text">Text</option>
                                <option value="html">HTML</option>
                                <option value="xml">XML</option>
                                <option selected value="json">JSON</option>
                                <option value="csv">CSV</option>
                            </select>

                            <div class="row ml-auto mr-0">
                                <button type="reset">
                                    <img class="btn-icon" src="assets/refresh.svg"/> Refresh
                                </button>
                                <button type="reset" class="mx-4">
                                    <img class="btn-icon" src="assets/save.svg"/> Save
                                </button>

                                <div class="checkbox-container mx-4">
                                    <label class="checkbox-label">
                                        <input type="checkbox" id="autoShowCheckbox">
                                        <span class="checkbox-custom"></span>
                                    </label>
                                    <h3 class="input-title  ml-2">Auto show</h3>
                                </div>

                                <button type="button" class="submit-btn" onclick="evaluateTemplate()">
                                    <img class="btn-icon" src="assets/arrow.svg"/> Show
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row ml-0 mr-0">
                    <!--   Editor column  -->
                    <div class="col-6 pl-1 pr-0 py-1 editor-side-column">
                        <!--  Editor  -->
                        <div class="editor-window mb-1">
                            <textarea name="snippetText" id="editorArea"></textarea>
                        </div>

                        <div class="error-window">
                            <div class="row error-window--title px-3 mx-0 my-0">
                                <h3>Problems:</h3>
                            </div>
                            <div id="errorArea" class="px-3 py-2"></div>
                        </div>
                    </div>

                    <!--  Result column  -->
                    <div class="col-6 px-1 py-1 editor-side-column">
                        <!--  iFrame  -->
                        <iframe type="text/plain" id="code" name="resultFrame"></iframe>
                    </div>

                </div>
            </form>
        </div>
    </div>
</div>

<#-- Modal -->
<div class="modal fade" id="modalFilterHelper" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header justify-content-center">
                <h3 class="modal-title" id="modalHelperTitle">Modal title</h3>
            </div>
            <div class="modal-body" id="modalHelperBody">
                <div class="tips">
                    <h4>Description:</h4>
                    <p id="description"></p>
                </div>
                <div class="tips parameters" id="parameters">
                </div>
                <div class="tips">
                    <h4>Example:</h4>
                    <code>
                        <pre><p id="example"></p></pre>
                    </code>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="cancel-btn" data-dismiss="modal">Cancel</button>
                <button type="button" class="submit-btn" data-dismiss="modal" onclick="onAddExpressionToEditor()">Insert
                </button>
            </div>
        </div>
    </div>
</div>

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

<script src="//ajax.aspnetcdn.com/ajax/jshint/r07/jshint.js"></script>
<script type="text/javascript" src="plugin/codemirror/addon/lint/lint.js"></script>
<#-- JSON lint -->
<script type="text/javascript" src="plugin/codemirror/addon/lint/json-lint.js"></script>
<script src="https://unpkg.com/jsonlint@1.6.3/web/jsonlint.js"></script>

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

</body>
</html>