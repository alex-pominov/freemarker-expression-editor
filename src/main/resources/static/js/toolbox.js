// Modal selectors
const referenceParameters = document.getElementById("referenceParameters");
const referenceDescription = document.getElementById("referenceDescription");
const referenceOutput = document.getElementById("referenceOutput");


// Fetch FM References when page loaded
let freemarkerReferences = [];
window.onload = function () {
    fetch("http://localhost:8080/freemarkerReferences")
        .then(responce => responce.json())
        .then(json => freemarkerReferences = json);
};


// Open modal with chosen reference
let template = '';
function openModalWithReference(methodName) {
    referenceDescription.innerHTML = "";
    referenceParameters.innerHTML = "";
    referenceOutput.innerHTML = "";

    const reference = freemarkerReferences.find(method => method.name === methodName);

    if (!reference.length) {
        template = reference.template;
        document.getElementById("referenceName").innerHTML = reference.name;
        document.getElementById("referenceExample").innerHTML = reference.example;

        if (reference.documentation) {
            const html = `<h4>Description:</h4><p>${reference.documentation}</p>`;
            referenceDescription.insertAdjacentHTML('afterbegin', html);
        }

        if (reference.parameters) {
            const html = `<h4>Parameters: <span>${reference.parameters}</span></h4>`;
            referenceParameters.insertAdjacentHTML('afterbegin', html);
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


// Add reference from modal to editor area
function addReferenceToEditor() {
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
