const editorArea = document.getElementById("editorArea");
const formatType = document.getElementById("formatType")
const resultType = document.getElementById("resultType");
const performEvaluation = document.getElementById("performEvaluation");
const code = document.getElementById("code").contentWindow.document;
const wysiswygCheckbox = document.getElementById("wysiswyg");
const autoShow = document.getElementById('autoShowCheckbox');

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
});

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

async function evaluateTemplate() {
    const form = document.getElementById("evalForm");

    if (performEvaluation.checked) {

        const data = {
            snippetText: editor.getValue(),
            formatType: formatType.value,
            resultType: resultType.value
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
                form.submit();
            } else {
                res.json().then(error => {
                    document.getElementById("errorArea").innerHTML = error.message;
                });
            }
        });
    } else {
        form.submit();
    }
}

autoShow.addEventListener('click', function () {
    if (autoShow.checked) {
        evaluateTemplate();
    }
});

// Debounce function
editor.on('change', function () {
    if (autoShow.checked) {
        evaluateOnChange();
    }
});

const evaluateOnChange = debounce(function () {
    evaluateTemplate();
}, 1000);

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

/*
let result;
function onResultTypeChange(obj) {
    if (obj.value === 'html') {
        // document.getElementById("code").srcdoc = result[0].result;
    }
    if (obj.value === 'text') {
        document.getElementById("code").src = "data:text/css;charset=utf-8," + escape(result[0].result);
    }
    if (obj.value === 'json') {
        const iframedoc = document.getElementById('code').contentDocument || document.getElementById('code').contentWindow.document;
        iframedoc.open();
        iframedoc.writeln(`<pre>${JSON.stringify({
            "id": 1,
            "first_name": "Bertie",
            "last_name": "Charity",
            "email": "bcharity0@nymag.com",
            "gender": "Female",
            "ip_address": "56.70.92.98"
        }, null, 4)}</pre>`);
        iframedoc.close();
    }
    if (obj.value === 'xml') {
        // document.getElementById("code").src = "data:text/xml;charset=utf-8," + escape(result[0].result);
        const iframedoc = document.getElementById('code').contentDocument || document.getElementById('code').contentWindow.document;
        iframedoc.open('<xsl:output method="xml" doctype-system="http://www.w3.org/TR/html4/strict.dtd" doctype-public="-//W3C//DTD HTML 4.01//EN" indent="yes" /> ');
        iframedoc.writeln("<start><h1>Hello</h1><h2>End2</h2></start>");
        iframedoc.close();
    }
}
*/