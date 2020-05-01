package com.editor.expression.freemarkerexpressioneditor.service;

import com.editor.expression.freemarkerexpressioneditor.domain.Snippet;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.ConditionalTemplateConfigurationFactory;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.PathGlobMatcher;
import freemarker.core.HTMLOutputFormat;
import freemarker.core.TemplateConfiguration;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SnippetService {

    public String processTemplate(Snippet snippet, Model model) throws IOException, TemplateException, ParserConfigurationException, SAXException {
        String result = "";

        // Perform evaluate FM expression if checkbox checked
        if (snippet.isPerformEvaluation()) {
            result = evaluateFreemarkerTemplate(snippet.getSnippetText());
        } else {
            // Just get template text without processing
            result = snippet.getSnippetText();
        }

        // Evaluate markdown template if is it format type
        if (snippet.getFormatType().equals("markdown")) {
            result = evaluateMarkdownTemplate(result);
        }

        switch (snippet.getResultType()) {
            case "text":
                break;
            case "html":
//                StringWriter out = new StringWriter();
//                Configuration cfg = buildConfiguration("/templates/ResultType");
//
//                Template htmlTemplate = cfg.getTemplate("html.ftlh");
//                Map<String, Object> dataModel = new HashMap<>();
//                dataModel.put("snippetText", result);
//
//                htmlTemplate.process(dataModel, out);

                model.addAttribute("result", result);

//                System.out.println(out);
//                result = out + "";

                return "example";
            default:
                break;
        }

        model.addAttribute("result", result);

        return "result";
    }

    public Object checkTemplateForError(Snippet snippet) {
        try {
            evaluateFreemarkerTemplate(snippet.getSnippetText());
            evaluateMarkdownTemplate(snippet.getSnippetText());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException | TemplateException e) {
            String error = e.toString();
            Pattern p = Pattern.compile("(Failed.*(?=,))|(Syntax.*(?=,))");
            Matcher matcher = p.matcher(error);
            matcher.find();
            String templateError = matcher.group()
                    .replaceAll("Failed", "Error")
                    .replaceAll("nameless", "")
                    .replaceAll("[\\[\\]]", "");

            throw new IllegalStateException(templateError);
        }
    }

    // Build Freemarker configuration
    private Configuration buildConfiguration(String directory) throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        Resource path = new DefaultResourceLoader().getResource(directory);
        cfg.setDirectoryForTemplateLoading(path.getFile());
        return cfg;
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