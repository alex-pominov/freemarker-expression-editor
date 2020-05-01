const parameters = document.getElementById("parameters");
let methods = [];
let template = '';

window.onload = function () {
    fetch("http://localhost:8080/methods")
        .then(responce => responce.json())
        .then(json => methods = json);
};


function openMethodModalHelper(methodName) {
    parameters.innerHTML = "";
    console.log(methodName);
    const method = methods.find(method => method.name === methodName);

    if (method.length !== 0) {
        template = method.template;
        document.getElementById("modalHelperTitle").innerHTML = method.name;
        document.getElementById("description").innerHTML = method.documentation;
        document.getElementById("example").innerHTML = method.example;

        if (method.parameters) {
            const html = `
            <h4>Parameters:</h4>
            <code>${method.parameters}</code>`;
            parameters.insertAdjacentHTML('afterbegin', html);
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
