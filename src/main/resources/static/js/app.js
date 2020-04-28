const freemarkerFrame = document.getElementById("freemarkerArea");
const markDownArea = document.getElementById("markDownArea");
const code = document.getElementById("code").contentWindow.document;

// // Freemarker Editor
const freemarkerEditor = CodeMirror.fromTextArea(freemarkerFrame, {
    lineNumbers: true,
    viewportMargin: Infinity,
    mode: "freemarker",
    styleActiveLine: true,
});
const hideFreemarkerEditor = () => (freemarkerEditor.getWrapperElement()).style.display = 'none';
const showFreemarkerEditor = () => (freemarkerEditor.getWrapperElement()).style.display = 'block';

// MarkDown Editor
const markDownEditor = CodeMirror.fromTextArea(markDownArea, {
    lineNumbers: true,
    viewportMargin: Infinity,
    mode: "gfm",
    styleActiveLine: true,
    lineWrapping: true
});
const hideMarkDownEditor = () => (markDownEditor.getWrapperElement()).style.display = 'none';
const showMarkDownEditor = () => (markDownEditor.getWrapperElement()).style.display = 'block';

// Manage Editors
function editorChangeHandler(obj) {
    switch (obj.value) {
        case "freemarker":
            hideMarkDownEditor();
            showFreemarkerEditor();
            freemarkerEditor.refresh();
            break;
        case "markdown":
            hideFreemarkerEditor();
            showMarkDownEditor();
            markDownEditor.refresh();
            break;
        default:
            break;
    }
}

window.addEventListener('load', (event) => {
    const editorType = document.getElementById("editorType");
    editorChangeHandler(editorType);
});


async function evaluateTemplate() {
    const data = {
        freemarkerText: freemarkerEditor.getValue(),
        markdownText: null,
        editorType: "freemarker",
        resultType: "text"
    };

    const response = await fetch('http://localhost:8080/results', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });

    console.log(response);
}















