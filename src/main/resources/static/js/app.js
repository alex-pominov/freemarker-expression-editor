"use strict"

const editorArea = document.getElementById("editorArea");
const formatType = document.getElementById("formatType")
const resultType = document.getElementById("resultType");
const code = document.getElementById("code");
const autoShow = document.getElementById('autoShowCheckbox');
const errorArea = document.getElementById("errorArea");
const wysiswyg = document.getElementById("wysiswyg");

/*##### Set format type when page loaded to avoid common drop-list bug #####*/
document.addEventListener("DOMContentLoaded", function (event) {
  onFormatTypeChange(formatType);
});

/*##### Codemirror setup #####*/
const editor = CodeMirror.fromTextArea(editorArea, {
  lineNumbers: true,
  viewportMargin: Infinity,
  mode: "freemarker",
  styleActiveLine: true,
  indentWithTabs: true,
  extraKeys: {"Ctrl-Space": "autocomplete"}
});

/*##### Handle codemirror mode when format type changed #####*/
function onFormatTypeChange(obj) {
  switch (obj.value) {
    case 'expression':
      editor.setOption("mode", "freemarker");
      break;
    case 'html':
      editor.setOption("mode", "text/html");
      break;
    case 'xml':
      editor.setOption("mode", "xml");
      break;
    case 'markdown':
      editor.setOption("mode", "gfm");
      break;
    case 'json':
      editor.setOption("mode", {name: "application/json", json: true});
      break;
    case 'csv':
      editor.setOption("mode", "mathematica");
      break;
    default:
      break;
  }

  switch (obj.value) {
    case 'html':
    case 'markdown':
      wysiswyg.style.display = 'flex';
      break;
    default:
      wysiswyg.style.display = 'none';
      break;
  }

  editor.refresh();
}

/*##### Evaluate template and pass to iFrame on submit #####*/
async function evaluateTemplate() {
  errorArea.innerHTML = '';
  const expression = encodeURIComponent(editor.getValue())        // Encode separately to replace(/#/, '%23');

  // Build uri for GET request
  const evaluateTemplate = document.querySelector('#performEvaluation:checked') !== null;
  const uri = encodeURI("http://localhost:8080/processTemplate?" +
    "formatType=" + formatType.value +
    "&performEvaluation=" + evaluateTemplate +
    "&resultType=" + resultType.value +
    "&snippetText=") + expression;

  fetch(uri).then(res => {
    if (res.ok) {
      document.getElementById('code').src = res.url;
    } else {
      res.json().then(error => errorArea.innerHTML = error.message);
    }
  });
}

/*##### Evaluate template on changes #####*/
const doEvaluation = () => autoShow.checked && evaluateOnChange();
const evaluateOnChange = debounce(function () {
  evaluateTemplate();
}, 700);

formatType.addEventListener('change', evaluateTemplate, false);
resultType.addEventListener('change', evaluateTemplate, false);
document.querySelector('#performEvaluation').addEventListener('change', doEvaluation, false);
autoShow.addEventListener('click', doEvaluation, false);
editor.on('change', doEvaluation);

// Debounce function
function debounce(func, wait, immediate) {
  let timeout;
  return function executedFunction() {
    const context = this;
    const args = arguments;
    const later = function () {
      timeout = null;
      if (!immediate) func.apply(context, args);
    };
    const callNow = immediate && !timeout;
    clearTimeout(timeout);
    timeout = setTimeout(later, wait);
    if (callNow) func.apply(context, args);
  };
};

editor.on('change', function () {
  const snippetCode = editor.getValue();

  let listVariables = [];
  try {
    const result = snippetCode.match(/<#list([\s\S\n]*?)(?=<\/#list)/mg);
    listVariables = new Set(result.map(el => {
      const parentNode = el.match(/[A-z]+?(?=\sas)/gm);
      const childNode = el.match(/\w+(?=})/gm);
      return parentNode.map(parent => {
        return childNode.map(child => parent + '.' + child);
      })
    }).flat(5));
  } catch (e) {}

  let simpleVariables = [];
  try {
    simpleVariables = snippetCode.match(/\w+\.\w+(?=})/gm);
  } catch (e) {}

  let arrayVariables = [];
  try {
    arrayVariables = snippetCode.match(/\w+\[\w+\]\.\w+(?=})/gm)
      .map(el => el.replace(/\[(.+?)]/gm, ''));
  } catch (e) {}

  const variables = [
      ...listVariables,
    simpleVariables,
    arrayVariables
  ].flat();

  markVariableAsUsed(variables);
});


const markVariableAsUsed = (variables) => {
  document.querySelectorAll(`[group] > span`).forEach(node => node.style.display = 'none');

  if (variables.length) {
    variables.forEach((variable) => {
      try {
        document.querySelector(`[group="${variable}"] > span`).style.display = 'block';
      } catch (e) {}
    })
  }
}
