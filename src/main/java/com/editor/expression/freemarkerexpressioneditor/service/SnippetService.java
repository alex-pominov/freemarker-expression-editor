package com.editor.expression.freemarkerexpressioneditor.service;

import com.editor.expression.freemarkerexpressioneditor.domain.Product;
import com.editor.expression.freemarkerexpressioneditor.domain.Snippet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SnippetService {

    public ResponseEntity<String> processTemplate(Snippet snippet, Map<String, Object> dataModel) throws IOException, TemplateException {
        String result = "";

        // Set headers to required content-type
        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.valueOf(snippet.getResultType()));

        try {
            // Perform evaluate FM expression if checkbox checked
            if (snippet.isPerformEvaluation()) {
                result = evaluateFreemarkerTemplate(snippet.getSnippetText(), dataModel);
            } else {
                // Just get template text without processing
                result = snippet.getSnippetText();
            }

            // Evaluate markdown template if is it format type
            if (snippet.getFormatType().equals("markdown") && !snippet.getResultType().equals("text/plain")) {
                result = evaluateMarkdownTemplate(result);
            }

            // Evaluate html fragment if content-type is html
            if (snippet.getResultType().equals("text/html")) {
                Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
                Resource path = new DefaultResourceLoader().getResource("/templates/ResultType");
                cfg.setDirectoryForTemplateLoading(path.getFile());
                Template htmlTemplate = cfg.getTemplate("html.ftlh");

                Map<String, Object> model = new HashMap<>();
                model.put("snippetText", result.split("\n"));

                StringWriter out = new StringWriter();
                htmlTemplate.process(model, out);
                result = out + "";

                return new ResponseEntity<String>(result, httpHeaders, HttpStatus.OK);
            }

            return new ResponseEntity<String>(result, httpHeaders, HttpStatus.OK);

        } catch (IOException | TemplateException e) {
            String error = e.toString();
            throw new IllegalStateException(error);
        }
    }

    // Evaluate Freemarker expressions
    private String evaluateFreemarkerTemplate(String stringTemplate, Object dataModel) throws IOException, TemplateException {
        StringWriter out = new StringWriter();
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        Template template = new Template(null, stringTemplate, configuration);
        template.process(dataModel, out);

        return out + "";
    }

    // Evaluate Markdown expressions
    private String evaluateMarkdownTemplate(String stringTemplate)  {
        MutableDataSet options = new MutableDataSet();
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        Node document = parser.parse(stringTemplate);

        return renderer.render(document);
    }
}