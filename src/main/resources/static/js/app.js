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
}

// Function to find all variables was used in editor via regex
const findUsedVariables = () => {
  let listVariables = [];
  let curLine = 0;
  const numOfLines = editor.lineCount();

  // Iterate over lines to find Lists and Variables
  while (curLine < numOfLines) {
    const ch = editor.doc.getLine(curLine).ch;
    const stringToTest = editor.doc.getLine(curLine).substr(0, ch);
    const lineHasListIterator = /<#list/gm.test(stringToTest);
    const hasInlineVariable = /[\w\s\[\].]+(?=})/gm.test(stringToTest);

    // If line has <#list ...> iterator than map over list
    // to get all used variables from this collection
    if (lineHasListIterator) {
      const collectionName = stringToTest.match(/[A-z]+?(?=\sas)/g)[0];
      const iteratorName = stringToTest.match(/\w+(?=>)/g)[0];
      const collectionVariables = findCollectionVariables(collectionName, curLine, iteratorName);
      listVariables = listVariables.concat(collectionVariables.variables);

      // Mapping over variables assigned via brackets - ${...}
    } else if (hasInlineVariable) {
      let variable = stringToTest.match(/[\w\[\].]+/g)[0];
      // Split a row for 4 group
      // eg: classifications[1].classificationGroups[1].classGroupId
      // 1. classificationGroups[1].classGroupId
      // 2. classificationGroups
      // 3. [1]
      // 4. classGroupId
      variable = /(\w+)(\[\w+]|).(\w+$)/gm.exec(variable);
      // combine 1 and 3 group and push to variables
      // eg. classificationGroups.classGroupId
      listVariables.push(variable[1] + "." + variable[3]);
    }

    curLine++;
  }

  const variables = [
      ...listVariables,
  ].flat();

  markVariableAsUsed(variables);
};

editor.on('change', findUsedVariables);


// Find Variables in List collections <#list ...> ... </#list>
// eg. <#list attributes as item> ${item.name} </#list>
//     should parse attributes.name from example below
const findCollectionVariables = (collectionName, curLine, iteratorName) => {
  let variables = [];
  let line = curLine + 1;
  const re = new RegExp(`{${iteratorName}`,"gm");

  while (true) {
    const lineStr = editor.doc.getLine(line).ch;
    const stringToTest = editor.doc.getLine(line).substr(0, lineStr);
    const lineHasNestedIterator = /<#list/gm.test(stringToTest);
    const hasCloseTag = /<\/#list/gm.test(stringToTest);

    if (re.test(stringToTest)) {
      variables.push(collectionName + "." + stringToTest.match(/\b\w+(?=})\b/gm));
      re.test(stringToTest);
    }

    if (hasCloseTag) {
      return {variables: variables, line: line};

      // If List has nested Lists they also could use variables of parent List
      // we call recursive function to find variables inside nested Lists
    } else if (lineHasNestedIterator) {
      const collectionVariables = findCollectionVariables(collectionName, line, iteratorName);
      variables = variables.concat(collectionVariables.variables);
      // Skip nested List by assigning line variable to continue mapping
      // Sometimes variables could be assigned after nested Lists
      line = collectionVariables.line;
    }
  line++;
  }
}

// Iterating over attributes and mark variables as USED
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
