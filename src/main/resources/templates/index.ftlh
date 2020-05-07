<!DOCTYPE HTML>
<html>
<head>
  <title>Expression Editor</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <link rel="stylesheet" href="css/main.css"/>
  <!--  CodeMirror CSS  -->
  <link rel="stylesheet" href="plugin/codemirror/lib/codemirror.css"/>
  <link rel="stylesheet" href="plugin/codemirror/addon/lint/lint.css"/>
  <link rel="stylesheet" href="plugin/codemirror/addon/hint/show-hint.css"/>
</head>
<body>
<div class="container-fluid p-0">
  <div class="row m-0">
    <!--  Expression References Toolbar  -->
    <div class="col-2 p-0 expressions" id="expression-container"></div>
    <!--  Expression editor & result frame  -->
    <div class="col px-0 py-0">
      <div class="navBar">
        <div class="col-6 p-0">
          <div class="row mx-4 justify-content-between">
            <div class="row ml-0 mr-auto">
              <h3>Format:</h3>
              <select id="formatType" name="formatType" onchange="onFormatTypeChange(this)">
                <option selected value="expression">Expression</option>
                <option disabled style="font-style:italic">&nbsp;&nbsp;&nbsp;-----------</option>
                <option value="html">HTML</option>
                <option value="markdown">Markdown</option>
                <option disabled style="font-style:italic">&nbsp;&nbsp;&nbsp;-----------</option>
                <option value="xml">XML</option>
                <option value="json">JSON</option>
              </select>
            </div>

            <div id="wysiswyg" class="checkbox-container">
              <label class="checkbox-label">
                <input type="checkbox">
                <span class="checkbox-custom"/>
              </label>
              <h3 class="input-title">WYSISWIG</h3>
            </div>

            <div class="checkbox-container">
              <label class="checkbox-label">
                <input type="checkbox" name="performEvaluation" id="performEvaluation" checked>
                <span class="checkbox-custom"/>
              </label>
              <h3 class="input-title">Evaluate</h3>
            </div>

          </div>
        </div>
        <div class="col-6 p-0">
          <div class="row mx-4">
            <h3>Result: </h3>
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

              <div class="checkbox-container">
                <label class="checkbox-label">
                  <input type="checkbox" id="autoShowCheckbox">
                  <span class="checkbox-custom"/>
                </label>
                <h3 class="input-title">Auto show</h3>
              </div>

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

      <div class="row mx-0">
        <!--   Codemirror part  -->
        <div class="col-6 editor-column">
          <!--  Editor  -->
          <div class="editor-window mb-1">
            <textarea name="snippetText" id="editorArea"></textarea>
          </div>
          <div class="errors">
            <h3 class="errors--title">Problems:</h3>
            <div id="errorArea" class="px-3 py-2"></div>
          </div>
        </div>

        <!--  Result frame part  -->
        <div class="col-6 editor-column">
          <iframe id="code"></iframe>
        </div>

      </div>
    </div>
  </div>
    <#include "modalReference.ftl">
    <#include "js_imports.ftl">
</body>
</html>