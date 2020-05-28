package com.editor.expression.freemarkerexpressioneditor.service;

import com.editor.expression.freemarkerexpressioneditor.domain.Editor;
import com.editor.expression.freemarkerexpressioneditor.domain.Variable;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EditorService {

    public ResponseEntity<String> processTemplate(Editor editor, Map<String, Object> dataModel) {
        String result = "";

        // Set headers to required content-type
        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.valueOf(editor.getResultType()));

        try {
            // Perform evaluate FM expression if checkbox checked
            if (editor.isPerformEvaluation()) {
                result = evaluateFreemarkerTemplate(editor.getSnippetText(), dataModel);
            } else {
                // Just get template text without processing
                result = editor.getSnippetText();
            }

            // Evaluate markdown template if is it format type
            if (editor.getFormatType().equals("markdown") && !editor.getResultType().equals("text/plain")) {
                result = evaluateMarkdownTemplate(result);
            }

            // Evaluate html fragment if content-type is html
            if (editor.getResultType().equals("text/html")) {
                Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
                Resource path = new DefaultResourceLoader().getResource("/templates/WrapperTemplates");
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

    public ResponseEntity<Object> getEditorReferences(List<Variable> variables) {
        Resource filters = new ClassPathResource("/json/Filters.json");
        Resource controlRefs = new ClassPathResource("/json/ControlAndLoops.json");
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode filtersJSON = mapper.readTree(filters.getInputStream());
            JsonNode controlRefsJSON = mapper.readTree(controlRefs.getInputStream());
            JsonNode variablesNode = mapper.valueToTree(variables);

            ArrayNode arrayNode = mapper.createArrayNode()
                    .add(filtersJSON)
                    .add(controlRefsJSON)
                    .add(variablesNode);

            return new ResponseEntity<>(arrayNode, HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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