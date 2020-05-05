const dictionary = [
    'assign',
    'attempt',
    'break',
    'case',
    'compress',
    'default',
    'else',
    'elseif',
    'escape',
    'fallback',
    'flush',
    'ftl',
    'function',
    'global',
    'if',
    'import',
    'include',
    'list',
    'local',
    'lt',
    'macro',
    'nested',
    'noescape',
    'nt',
    'recover',
    'recurse',
    'return',
    'rt',
    'setting',
    'stop',
    'switch',
    't',
    'visit',
];

// Register our custom Codemirror hint plugin.
CodeMirror.registerHelper("hint", "freemarker", function (editor) {
    const cur = editor.getCursor();
    const curLine = editor.getLine(cur.line);
    let start = cur.ch;
    let end = start;
    while (end < curLine.length && /[\w$]/.test(curLine.charAt(end))) ++end;
    while (start && /[\w$]/.test(curLine.charAt(start - 1))) --start;
    const curWord = start !== end && curLine.slice(start, end);
    const regex = new RegExp('^' + curWord, 'i');
    return {
        list: (!curWord ? [] : dictionary.filter(function (item) {
            return item.match(regex);
        })).sort(),
        from: CodeMirror.Pos(cur.line, start),
        to: CodeMirror.Pos(cur.line, end)
    }
});

CodeMirror.commands.autocomplete = function(cm) {
    cm.showHint({hint: CodeMirror.hint.anyword});
}

editor.on("keyup", function (cm, event) {
    if (!cm.state.completionActive && // Enables keyboard navigation in autocomplete list
        event.keyCode !== 13) {       // Enter - do not open autocomplete list just after item has been selected in it
        cm.showHint({completeSingle: false});
    }
});