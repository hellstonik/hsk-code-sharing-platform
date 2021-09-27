package platform.presentation.view;

import platform.businesslayer.Code;

import java.util.List;

public class JsonView implements View {
    private String template;
    private static final String JSON_TEMPLATE = "{\n" +
            "    \"code\": \"%s\",\n" +
            "    \"date\": \"%s\",\n" +
            "    \"time\": %s,\n" +
            "    \"views\": %s\n" +
            "}";

    @Override
    public String getView() {
        return template;
    }

    @Override
    public void fillViewWithModel(Code model) {
        this.template = removeLastChar(fillJsonTemplateWithCode(model));
    }


    @Override
    public void fillViewWithSeveralModels(List<Code> codeSnippets) {
        String result = "";

        for (int i = 0; i < codeSnippets.size(); i++) {
            Code code = codeSnippets.get(i);
            result += fillJsonTemplateWithCode(code);
        }

        result = removeLastChar(result);
        result = "[" + result + "]";

        template = result;
    }

    public static String removeLastChar(String s) {
        return (s == null || s.length() == 0)
                ? null
                : (s.substring(0, s.length() - 1));
    }

    private String fillJsonTemplateWithCode(Code code) {
        String result = String.format(JSON_TEMPLATE + ",",
                code.getCode(), code.getLocalDateTime(), code.getTimeLeft(), code.getViewsLeft());
        return result;
    }

}