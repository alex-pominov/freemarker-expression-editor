const parameters = document.getElementById("parameters");
const referenceOutput = document.getElementById("referenceOutput");
let freemarkerReferences = [];
let template = '';

window.onload = function () {
    fetch("http://localhost:8080/freemarkerReferences")
        .then(responce => responce.json())
        .then(json => freemarkerReferences = json);
};


function openReferenceModalHelper(methodName) {
    parameters.innerHTML = "";
    referenceOutput.innerHTML = "";
    const reference = freemarkerReferences.find(method => method.name === methodName);

    if (reference.length !== 0) {
        template = reference.template;
        document.getElementById("modalHelperTitle").innerHTML = reference.name;
        document.getElementById("description").innerHTML = reference.documentation;
        document.getElementById("example").innerHTML = reference.example;

        if (reference.parameters) {
            const html = `<h4>Parameters: <span>${reference.parameters}</span></h4>`;
            parameters.insertAdjacentHTML('afterbegin', html);
        }

        if (reference.output) {
            const html = `
                <h4>Output:</h4>
                <pre>${reference.output}</pre>`;
            referenceOutput.insertAdjacentHTML('afterbegin', html);
        }
    }
    $('#modalFilterHelper').modal();
}

function onAddExpressionToEditor() {
    const statement = template;
    const selection = editor.getSelection();

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
    template = '';
}
