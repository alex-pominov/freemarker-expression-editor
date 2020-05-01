const editorArea = document.getElementById("editorArea");
const formatType = document.getElementById("formatType");
const code = document.getElementById("code").contentWindow;

document.addEventListener("DOMContentLoaded", function (event) {
    onFormatTypeChange(formatType);
});

// Editor
const editor = CodeMirror.fromTextArea(editorArea, {
    lineNumbers: true,
    viewportMargin: Infinity,
    mode: "freemarker",
    styleActiveLine: true,
    indentWithTabs: true,
    gutters: ["CodeMirror-lint-markers"],
    lint: true
});

const wysiswygCheckbox = document.getElementById("wysiswyg");

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
            wysiswygCheckbox.style.display = 'flex';
            break;
        default:
            wysiswygCheckbox.style.display = 'none';
            break;
    }

    editor.refresh();
}


function evaluateTemplate() {
    const data = {
        snippetText: editor.getValue(),
        formatType: "freemarker",
        resultType: "text"
    };

    // Check Status
    fetch('http://localhost:8080/checkTemplate', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(res => {
        if (res.ok) {
            document.getElementById("errorArea").innerHTML = '';
            document.getElementById("evalForm").submit();
        } else {
            res.json().then(error => {
                document.getElementById("errorArea").innerHTML = error.message;
                ;
            });
        }
    });
}

editor.on('change', function () {
    if (document.getElementById('autoShowCheckbox').checked) {
        console.log(123)
        debounce(function () {
            evaluateTemplate();
        }, 500);
    }
});

function debounce(func, wait, immediate) {
    let timeout;
    return function () {
        const context = this, args = arguments;
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