<!DOCTYPE HTML>
<html>
<head>
  <title>Editor</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <!--  CodeMirror  -->
  <link rel="stylesheet" href="plugin/codemirror/lib/codemirror.css"/>
  <link rel="stylesheet" href="plugin/codemirror/addon/lint/lint.css"/>
  <link rel="stylesheet" href="plugin/codemirror/addon/hint/show-hint.css"/>
  <link rel="stylesheet" href="css/style.css"/>
  <link rel="stylesheet" href="css/toolbox.css"/>
  <link rel="stylesheet" href="css/input.css"/>
  <!--  Bootstrap  -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>

<body>
<div class="container-fluid px-0 py-0">
  <div class="row m-0">
    <!--  Toolbox  -->
    <div class="col-2 p-0 expressions-bar" id="expression-container"></div>

    <!--  Expression editor & result  -->
    <div class="col px-0 py-0">
      <form action="/processTemplate" target="resultFrame" name="snippet" method="get" id="evalForm">
        <div class="row ml-0 mr-0 justify-content-between header align-items-center">
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
                  <input type="checkbox" name="performEvaluation" id="performEvaluation" checked>
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
                <option value="text/plain">Text</option>
                <option value="text/html">HTML</option>
                <option value="application/xml">XML</option>
                <option selected value="application/json">JSON</option>
                <option value="csv">CSV</option>
              </select>

              <div class="row ml-auto mr-2">
                <button type="reset">
                  <img class="btn-icon" src="assets/refresh.svg"/> Refresh
                </button>

                <button type="button" class="mx-2">
                  <img class="btn-icon" src="assets/save.svg"/> Save
                </button>


                <label class="checkbox-label ml-1">
                  <input type="checkbox" id="autoShowCheckbox">
                  <span class="checkbox-custom"></span>
                </label>
                <h3 class="input-title mx-2">Auto show</h3>

                <button type="button" class="submit-btn mx-2" onclick="evaluateTemplate()">
                  <img class="btn-icon" src="assets/arrow.svg"/> Show
                </button>

                <button type="button" class="cancel-btn">
                  <img class="btn-icon" src=""/> Close
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
            <iframe id="code" name="resultFrame"></iframe>
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
        <h3 class="modal-title" id="referenceName">Modal title</h3>
      </div>
      <div class="modal-body">
        <div class="tips" id="referenceDescription"></div>
        <div class="tips parameters" id="referenceParameters"></div>
        <div class="tips" id="referenceExample"></div>
        <div class="tips" id="referenceOutput"></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="submit-btn" data-dismiss="modal" onclick="addReferenceToEditor()">Insert
        </button>
        <button type="button" class="cancel-btn" data-dismiss="modal">Cancel</button>
      </div>
    </div>
  </div>
</div>

<#include "js_imports.ftl">
</body>
</html>