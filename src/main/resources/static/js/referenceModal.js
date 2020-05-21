// Modal selectors
const referenceParameters = document.getElementById("referenceParameters");
const referenceDescription = document.getElementById("referenceDescription");
const referenceExample = document.getElementById("referenceExample");
const referenceOutput = document.getElementById("referenceOutput");
const templateAsListBtn = document.getElementById("templateAsListBtn");

// Open modal with chosen reference
let template = '';
let listTemplate = '';

function openModalWithReference(reference) {
  referenceDescription.innerHTML = "";
  referenceParameters.innerHTML = "";
  referenceExample.innerHTML = "";
  referenceOutput.innerHTML = "";
  templateAsListBtn.style.display = "none";

  if (!reference.length) {
    template = reference.template;
    const name = reference.parentPath ? reference.parentPath + ": " + reference.name : reference.name;
    document.getElementById("referenceName").innerHTML = name;

    if (reference.documentation) {
      const html = `<h4>Description:</h4><p>${reference.documentation}</p>`;
      referenceDescription.insertAdjacentHTML('afterbegin', html);
    }

    if (reference.parameters) {
      const html = `<h4>Parameters: <span>${reference.parameters}</span></h4>`;
      referenceParameters.insertAdjacentHTML('afterbegin', html);
    }

    if (reference.example) {
      const example = reference.example.replace(/<\/#/, "&lt;/#");
      const html = `
                <h4>Example:</h4>
                <pre>${example}</pre>`;
      referenceExample.insertAdjacentHTML('afterbegin', html);

      if (reference.output) {
        const html = `
                <h4>Output:</h4>
                <pre>${reference.output}</pre>`;
        referenceOutput.insertAdjacentHTML('afterbegin', html);
      }
    }

    if (reference.listTemplate) {
      templateAsListBtn.style.display = "block";
      listTemplate = reference.listTemplate;
    }
  }
  $('#modalFilterHelper').modal();
}

// Add reference from modal to editor area
function addReferenceToEditor(isAsList) {
  let statement = isAsList ? listTemplate : template;
  const selection = editor.getSelection();

  // If variable inserted inside the list - then check is it a list variable
  // And if yes, then insert it as a list variable
  if (!isAsList) {
    let line = editor.doc.getCursor().line - 1;
    while (line >= 0) {
      const lineStr = editor.doc.getLine(line).ch;
      const stringToTest = editor.doc.getLine(line).substr(0, lineStr);
      const hasCloseTag = /<\/#list/gm.test(stringToTest);

      // If find nested list iterator then skip this block of code
      if (hasCloseTag) {
        line = skipListIterator(line);
      } else {
        // Check List collection name and variable name to belong same collection
        const collectionName = stringToTest.match(/[A-z]+?(?=\sas)/gm);
        const statementName = statement.match(/\w+(?=\[\w+\].\w+})/gm);
        // if List collection equals variable collection then use it as a list variable
        try {
          if (collectionName.toString() === statementName.toString()) {
            statement = statement.replace(/[\w\.\[\]]+(?=\.\w)/g, stringToTest.match(/\b\w+(?=>)\b/gm));
            break;
          }
        } catch (e) {}
      }
      line--;
    }
  }

  if (selection.length > 0) {
    editor.replaceSelection(statement);
  } else {
    const doc = editor.getDoc();
    const cursor = doc.getCursor();
    const pos = {
      line: cursor.line,
      ch: cursor.ch
    }
    doc.replaceRange(statement, pos);
  }
}

// function to skip nested list iterators
// if face </#list> tag, the skip all the iterator code block
// ex. <#list ... as ...> ... </#list> - would be skipped
const skipListIterator = (curLine) => {
  let line = curLine - 1;
  while (true) {
    const lineStr = editor.doc.getLine(line).ch;
    const stringToTest = editor.doc.getLine(line).substr(0, lineStr);
    const lineHasListIterator = /<#list/gm.test(stringToTest);
    const hasCloseTag = /<\/#list/gm.test(stringToTest);
    line--;
    if (hasCloseTag) {
      line = skipListIterator(line);
    } else if (lineHasListIterator) {
        return line;
    }
  }
}
