// Modal selectors
const referenceParameters = document.getElementById("referenceParameters");
const referenceDescription = document.getElementById("referenceDescription");
const referenceExample = document.getElementById("referenceExample");
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
    referenceExample.innerHTML = "";
    referenceOutput.innerHTML = "";

    const reference = freemarkerReferences.find(method => method.name === methodName);

    if (!reference.length) {
        template = reference.template;
        document.getElementById("referenceName").innerHTML = reference.name;

        if (reference.documentation) {
            const html = `<h4>Description:</h4><p>${reference.documentation}</p>`;
            referenceDescription.insertAdjacentHTML('afterbegin', html);
        }

        if (reference.parameters) {
            const html = `<h4>Parameters: <span>${reference.parameters}</span></h4>`;
            referenceParameters.insertAdjacentHTML('afterbegin', html);
        }

        if (reference.example) {
            const html = `
                <h4>Example:</h4>
                <pre>${reference.example}</pre>`;
            referenceExample.insertAdjacentHTML('afterbegin', html);

            if (reference.output) {
                const html = `
                <h4>Output:</h4>
                <pre>${reference.output}</pre>`;
                referenceOutput.insertAdjacentHTML('afterbegin', html);
            }
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
