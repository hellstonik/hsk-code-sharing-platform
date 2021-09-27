package platform.presentation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import platform.businesslayer.CodeService;
import platform.businesslayer.Code;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Controller
public class AppControllerOther {
    @Autowired
    private CodeService codeService;

    @GetMapping(path = {"/code/{id}"})
    public ModelAndView getSnippetById(@PathVariable String id) {
        ModelAndView mv = new ModelAndView();
        Code code = codeService.findCodeById(id);

        if (code == null) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }

        if (codeService.isExpired(code)) {
            codeService.deleteById(id);
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }

        if (code.getViews() > 0) {
            code.setViewsLeft(code.getViewsLeft() - 1);
            codeService.updateCode(code);
        }

        mv.addObject("code", code.getCode());
        mv.addObject("localDateTime", code.getLocalDateTime());
        if (code.getTime() > 0) {
            mv.addObject("time_restriction", code.getTime() <= 0 ? null : code.getTimeLeft());
        }

        if (code.getViews() > 0) {
            mv.addObject("views_restriction", code.getViews() <= 0 ? null : code.getViewsLeft());
        }

        mv.setViewName("code_snippet");

        return mv;
    }

    @GetMapping(path = {"/code/latest"}, produces = MediaType.TEXT_HTML_VALUE)
    public String getLatestSnippetsHtml(Model model) {
        model.addAttribute("snippets", codeService.getLastTenCodeSnippets());
        return "code_snippets";
    }

}
