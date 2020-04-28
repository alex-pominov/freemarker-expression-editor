package com.editor.expression.freemarkerexpressioneditor.controller;

import com.editor.expression.freemarkerexpressioneditor.domain.Snippet;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.StringWriter;

@Controller
public class IndexController {

    @GetMapping("/")
    public String codemirrorEditor(Model model) {
        model.addAttribute("snippet", new Snippet());
        return "index2";
    }

    @PostMapping("/result")
    public String evaluateSubmit(@ModelAttribute Snippet snippet, Model model) throws IOException, TemplateException {
        String result = "";

        if(snippet.getResultType().equals("text")) {
            String freemarkerResult = evaluateFreemarkerTemplate(snippet.getFreemarkerText());
            String markdownResult = evaluateMarkdownTemplate(snippet.getMarkdownText());
            result = freemarkerResult + "\n" + markdownResult;
        }

        if(snippet.getResultType().equals("html")) {
            String freemarkerResult = evaluateFreemarkerTemplate(snippet.getFreemarkerText());
            String markdownResult = evaluateMarkdownTemplate(snippet.getMarkdownText());
            result = freemarkerResult + "\n" + markdownResult;
        }

        if(snippet.getResultType().equals("json")) {
            result = evaluateFreemarkerTemplate(snippet.getFreemarkerText());
            model.addAttribute("myCode", "document.getElementById(\"json\").textContent = JSON.stringify("+ result +", undefined, 2);\n");
        }

        // TODO : implement CSV output for WYSISWYG
        if(snippet.getResultType() == "csv") {

        }

        System.out.println(result);

        model.addAttribute("result", result);

        return "result";
    }

    @PostMapping("/results")
    public String evaluateOnSubmit(@ModelAttribute Snippet snippet, Model model) throws IOException, TemplateException {

        String result = "";

        if(snippet.getResultType().equals("text")) {
            String freemarkerResult = evaluateFreemarkerTemplate(snippet.getFreemarkerText());
            String markdownResult = evaluateMarkdownTemplate(snippet.getMarkdownText());
            result = freemarkerResult + "\n" + markdownResult;
        }

        model.addAttribute("result", result);

        return "result";
    }











    private String evaluateFreemarkerTemplate(String stringTemplate) throws IOException, TemplateException {
        StringWriter out = new StringWriter();
        Configuration configuration = new Configuration();
        Template template = new Template(null, stringTemplate, configuration);
        template.process(null, out);

        return out + "";
    }

    private String evaluateMarkdownTemplate(String stringTemplate) throws IOException, TemplateException {
        MutableDataSet options = new MutableDataSet();
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        Node document = parser.parse(stringTemplate);

        return renderer.render(document);
    }
}


